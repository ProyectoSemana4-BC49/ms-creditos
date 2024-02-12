package com.nttdatabc.mscreditos.config.kafka;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

@Configuration
public class KafkaConsumerListener {
  private MonoSink<String> customerVerificationResponseSink;
  @KafkaListener(topics = {"response-verify-customer-exist-credit"}, groupId = "my-group-id")
  public void listener(String message){
    if (customerVerificationResponseSink != null) {
      customerVerificationResponseSink.success(message);
      customerVerificationResponseSink = null;
    }
  }
  public Mono<String> getCustomerVerificationResponse() {
    return Mono.create(sink -> customerVerificationResponseSink = sink);
  }
}
