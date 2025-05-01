package com.bank.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    //this configuration avoid data loss, duplicates and maintain strict ordering of msgs.
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        //extactly once semantics configuration
        //ACKS_CONFIG = all, to ensure no data loss only
        configProps.put(ProducerConfig.ACKS_CONFIG, "all");
        //ENABLE_IDEMPOTENCE_CONFIG is used to avoid duplicates, default = true
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        // MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1 is used for strict ordering of msgs, default=5
        configProps.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        configProps.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "zstd");

        // configProps.put(ProducerConfig.RETRIES_CONFIG, Integer.MAX_VALUE);
        //RETRIES_CONFIG = 3,to avoid data loss.  default value for RETRIES_CONFIG = 5.
        configProps.put(ProducerConfig.RETRIES_CONFIG, 3);

        //TRANSACTIONAL_ID_CONFIG to avoid duplicates, users trancation id to verify if msg exists already in the topic
        configProps.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "credit-tx-id-");

        DefaultKafkaProducerFactory<String, Object> producerFactory = new DefaultKafkaProducerFactory<>(configProps);

        KafkaTemplate kafkaTemplate = new KafkaTemplate<>(producerFactory);
        return kafkaTemplate;
    }


}
