package org.cook.bank_system.repository;

import org.cook.bank_system.entity.ProcessedTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedTransactionsRepository extends JpaRepository<ProcessedTransactionEntity, Long> {

}
