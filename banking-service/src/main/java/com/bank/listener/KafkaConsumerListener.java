package com.bank.listener;


import com.bank.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumerListener {
    // DLT - Dead Letter Topic - final end place
    @RetryableTopic(
            attempts = "2",
            backoff = @Backoff(delay = 10000),
            retryTopicSuffix = ".retry",
            dltTopicSuffix = ".dlt",
            kafkaTemplate = "kafkaRetryDltTemplate"
    )
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

        if(true){
            throw  new RuntimeException("Test Exception");
        }


        acknowledgment.acknowledge();

    }

    @DltHandler
    public void dltHandler(ConsumerRecord<String, Account> record){
        log.info("Received Message from DLT = {}", record.value());

    }

}


