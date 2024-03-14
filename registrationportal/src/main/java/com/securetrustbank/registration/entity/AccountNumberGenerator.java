package com.securetrustbank.registration.entity;

import com.securetrustbank.registration.configurations.RegistrationConfigurations;
import lombok.AllArgsConstructor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AccountNumberGenerator implements IdentifierGenerator {
    private final RegistrationConfigurations registrationConfigurations;
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        StringBuilder accountNumber = new StringBuilder(registrationConfigurations.getAccountNumberLength());
        List<Integer> randomNumbers = new ArrayList<>();
        for(int randomNumber=0;randomNumber<=4;randomNumber++){
            randomNumbers.add(randomNumber);
        }
        randomNumbers.subList(0, randomNumbers.size()).forEach(randomNumber->{
            SecureRandom secureRandom = new SecureRandom();
            accountNumber.append(secureRandom.nextInt(10));
        });
        return registrationConfigurations.getBankCode()+accountNumber.toString();
    }
}
