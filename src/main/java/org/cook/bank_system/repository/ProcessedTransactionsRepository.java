package org.cook.bank_system_consumer.repository;

import org.cook.bank_system_consumer.entity.ProcessedTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedTransactionsRepository extends JpaRepository<ProcessedTransactionEntity, Long> {

}
