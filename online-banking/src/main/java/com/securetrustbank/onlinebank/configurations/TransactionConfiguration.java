package com.securetrustbank.onlinebank.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("transaction")
@Component
@Data
public class TransactionConfiguration {
    private String deposit;
    private String withdraw;
    private String success;
}
