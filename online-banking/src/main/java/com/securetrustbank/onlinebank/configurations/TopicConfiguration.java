package com.securetrustbank.onlinebank.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfiguration {
    @Bean
    public NewTopic onlineBankingTransactionTopic(){
        return TopicBuilder.name("online-banking-transaction")
                .build();
    }
}
