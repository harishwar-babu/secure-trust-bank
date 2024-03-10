package com.securetrustbank.registration.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("registration")
@Component
@Data
public class RegistrationConfigurations {
    private String role;
    private String bankCode;
    private int accountNumberLength;
}
