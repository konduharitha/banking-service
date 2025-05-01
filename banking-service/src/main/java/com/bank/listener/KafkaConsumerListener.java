package com.bank.listener;


import com.bank.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumerListener {

    @KafkaListener(
            topics = "credit.event",
            groupId = "credit.event.consumer.group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenCreditEvents(ConsumerRecord<String, Account> record, Acknowledgment acknowledgment) {

        log.info("Consume Message = {}", record.key());
        log.info("Consume Message = {}", record.value());
        log.info("Consume Message = {}", record.partition());
        log.info("Consume Message = {}", record.offset());
        log.info("Consume Message = {}", record.timestamp());
        log.info("Consume Message = {}", record.topic());



        acknowledgment.acknowledge();

    }

}


