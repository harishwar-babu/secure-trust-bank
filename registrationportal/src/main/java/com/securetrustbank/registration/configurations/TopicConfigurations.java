package com.securetrustbank.registration.configurations;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
@Configuration
public class TopicConfigurations {
    @Bean
    public NewTopic userDetailsTopic(){
        return TopicBuilder.name("user-details-topic")
                .build();
    }
    @Bean
    public NewTopic onlineBankDetailsTopic(){
        return TopicBuilder.name("bank-details-topic")
                .build();
    }
}
