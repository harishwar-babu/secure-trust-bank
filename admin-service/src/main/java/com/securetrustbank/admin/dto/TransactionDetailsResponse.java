package com.securetrustbank.admin.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionDetailsResponse {
    private Integer transactionId;
    private LocalDateTime transactionDate;
    private String transactionType;
    private Double depositAmount;
    private Double withdrawAmount;
}
