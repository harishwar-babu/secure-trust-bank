package com.securetrustbank.registration.dto;

import lombok.Data;

@Data
public class BankAccountCreationResponse {
    private String status;
    private String accountNumber;
    private String userId;
}
