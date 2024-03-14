package com.securetrustbank.registration.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("role")
@Data
public class RoleConfigurations {
    private String customer;
    private String merchant;
}
