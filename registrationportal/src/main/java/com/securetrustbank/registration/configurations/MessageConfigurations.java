package com.securetrustbank.registration.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("message")
@Data
public class MessageConfigurations {
    private String success;
    private String failure;
}
