package org.cook.bank_system_consumer.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cook.bank_system_consumer.entity.ProcessedTransactionEntity;
import org.cook.bank_system_consumer.model.TransactionEvent;
import org.cook.bank_system_consumer.repository.ProcessedTransactionsRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionConsumerService {

    private final ProcessedTransactionsRepository repository;

    @KafkaListener(topics = "money-transfers")
    @Transactional
    public void consume(TransactionEvent event){
        log.info("Received transaction: {}", event.getTransactionId());

        if(repository.existsById(event.getTransactionId())){
            log.warn("Duplicate transaction: {}. Skipped", event.getTransactionId());

            return;
        }

        try{
            sendNotification(event);

            ProcessedTransactionEntity processedTransaction = new ProcessedTransactionEntity();
            processedTransaction.setTransactionId(event.getTransactionId());
            processedTransaction.setProcessedAt(LocalDateTime.now());

            repository.save(processedTransaction);
        }catch (Exception e){
            log.error("Error processing transaction {} : {}", event.getTransactionId(), e.getMessage());
        }
    }

    public void sendNotification(TransactionEvent event){
        log.info("User: {} received {} {} from User: {}", event.getToAccountId(), event.getAmount(), event.getCurrency(), event.getFromAccountId());
    }

}
