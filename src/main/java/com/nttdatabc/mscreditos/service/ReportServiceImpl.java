package com.nttdatabc.mscreditos.service;

import static com.nttdatabc.mscreditos.utils.Constantes.EX_ERROR_NOT_CARD_CREDIT;

import com.nttdatabc.mscreditos.config.kafka.KafkaConsumerListener;
import com.nttdatabc.mscreditos.model.MovementCredit;
import com.nttdatabc.mscreditos.model.enums.TypeCredit;
import com.nttdatabc.mscreditos.model.helpers.BalanceAccounts;
import com.nttdatabc.mscreditos.model.helpers.SummaryAccountBalance;
import com.nttdatabc.mscreditos.repository.CreditRepository;
import com.nttdatabc.mscreditos.repository.MovementRepository;
import com.nttdatabc.mscreditos.service.interfaces.ReportService;
import com.nttdatabc.mscreditos.utils.CreditValidator;
import com.nttdatabc.mscreditos.utils.exceptions.errors.ErrorResponseException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service de reporte.
 */
@Service
public class ReportServiceImpl implements ReportService {

  @Autowired
  private CreditRepository creditRepository;
  @Autowired
  private MovementRepository movementRepository;
  @Autowired
  private CreditServiceImpl creditServiceImpl;
  @Autowired
  private CreditValidator creditValidator;
  @Autowired
  private KafkaConsumerListener kafkaConsumerListener;

  @Override
  public Mono<BalanceAccounts> getBalanceAverageService(String customerId) {
    return creditValidator.verifyCustomerExists(customerId, kafkaConsumerListener)
        .flatMap(customerFound -> {
          BalanceAccounts balanceAccounts = new BalanceAccounts();
          balanceAccounts.setCustomerId(customerId);

          LocalDate currentDate = LocalDate.now();
          int daysInMonth = currentDate.lengthOfMonth();
          int year = currentDate.getYear();
          int month = currentDate.getMonthValue();
          String dateFilter = String.format("%d-%s", year, String.valueOf(month).length() == 1 ? "0" + month : month);

          return creditRepository.findByCustomerId(customerId)
              .flatMap(credit -> movementRepository.findByCreditId(credit.getId())
                  .collectList()
                  .map(movements -> {
                    List<MovementCredit> movementsInCurrentMonth = movements.stream()
                        .filter(movement -> movement.getDayCreated().contains(dateFilter))
                        .collect(Collectors.toList());

                    double totalBalance = movementsInCurrentMonth.stream()
                        .mapToDouble(movement -> movement.getAmount().doubleValue())
                        .sum();
                    BigDecimal averageDailyBalance = BigDecimal.valueOf(totalBalance / daysInMonth);

                    SummaryAccountBalance summaryAccountBalance = new SummaryAccountBalance();
                    summaryAccountBalance.setAccountId(credit.getId());
                    summaryAccountBalance.setBalanceAvg(averageDailyBalance);

                    return summaryAccountBalance;
                  }))
              .collectList()
              .doOnNext(summaryAccountBalances -> balanceAccounts.setSummaryAccounts(summaryAccountBalances))
              .thenReturn(balanceAccounts);
        });
  }

  @Override
  public Flux<MovementCredit> getLastMovementsCardCreditService(String creditId) {
    return creditServiceImpl.getCreditByIdService(creditId)
        .filter(credit -> credit.getTypeCredit().equalsIgnoreCase(TypeCredit.TARJETA.toString()))
        .switchIfEmpty(Mono.error(new ErrorResponseException(EX_ERROR_NOT_CARD_CREDIT, HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT)))
        .thenMany(movementRepository.findTop10ByOrderByDayCreatedDesc(creditId));

  }
}
