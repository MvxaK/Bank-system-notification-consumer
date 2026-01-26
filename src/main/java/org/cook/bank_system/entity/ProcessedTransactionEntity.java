package org.cook.bank_system_consumer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "processed_transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedTransactionEntity {

    @Id
    private Long transactionId;
    private LocalDateTime processedAt;

}
