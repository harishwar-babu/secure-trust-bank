package com.securetrustbank.registration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
@SpringBootApplication
@RefreshScope
public class RegistrationPortalApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegistrationPortalApplication.class, args);
	}

}
