package com.securetrustbank.onlinebank.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionResponse {
    private String transactionStatus;
    private LocalDateTime transactionDate;
    private String transactionType;
    private Double depositAmount;
    private Double withdrawAmount;
    private Double currentBalance;
}
