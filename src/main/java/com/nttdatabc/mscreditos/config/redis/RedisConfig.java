package com.nttdatabc.mscreditos.config.redis;

import com.nttdatabc.mscreditos.model.Credit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
  @Bean("reactiveRedisTemplateCredit")
  public ReactiveRedisTemplate<String, Credit> reactiveRedisTemplateCredit(ReactiveRedisConnectionFactory connectionFactory) {
    RedisSerializationContext<String, Credit> serializationContext = RedisSerializationContext
        .<String, Credit>newSerializationContext(new StringRedisSerializer())
        .key(new StringRedisSerializer())
        .value(new Jackson2JsonRedisSerializer<>(Credit.class))
        .hashKey(new Jackson2JsonRedisSerializer<>(Integer.class))
        .hashValue(new GenericJackson2JsonRedisSerializer())
        .build();
    return new ReactiveRedisTemplate<>(connectionFactory, serializationContext);
  }
}
