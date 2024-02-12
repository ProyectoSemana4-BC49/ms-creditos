package com.nttdatabc.mscreditos.config.kafka;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * config topics kafka.
 */
@Configuration
public class KafkaTopicConfig {
  @Bean
  public NewTopic generateTopic() {
    Map<String, String> configuration = new HashMap<>();
    configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
    configuration.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
    configuration.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
    configuration.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");
    return TopicBuilder.name("response-get-credits-bycustomer")
        .configs(configuration)
        .build();

  }

  @Bean
  public NewTopic generateTopicResponseHasDebt() {
    Map<String, String> configuration = new HashMap<>();
    configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
    configuration.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
    configuration.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
    configuration.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");
    return TopicBuilder.name("response-has-debt-credit")
        .configs(configuration)
        .build();

  }

  @Bean
  public NewTopic generateTopicResponseHasCardDeb() {
    Map<String, String> configuration = new HashMap<>();
    configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
    configuration.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
    configuration.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
    configuration.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");
    return TopicBuilder.name("response-has-card-debt")
        .configs(configuration)
        .build();

  }

  @Bean
  public NewTopic generateTopicCustomerExistCredit() {
    Map<String, String> configuration = new HashMap<>();
    configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
    configuration.put(TopicConfig.RETENTION_MS_CONFIG, "86400000");
    configuration.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824");
    configuration.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012");
    return TopicBuilder.name("response-verify-customer-exist-credit")
        .configs(configuration)
        .build();

  }
}
