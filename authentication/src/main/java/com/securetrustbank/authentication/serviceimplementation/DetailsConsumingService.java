package com.securetrustbank.authentication.serviceimplementation;
import com.securetrustbank.authentication.dto.AuthRequestDto;
import com.securetrustbank.authentication.entity.AuthenticationEntity;
import com.securetrustbank.authentication.repository.AuthenticationRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DetailsConsumingService {
    private final AuthenticationRepository authenticationRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    @PostConstruct
    private void managerCredentials(){
        AuthenticationEntity authenticationEntity = new AuthenticationEntity();
        authenticationEntity.setUserId("manager");
        authenticationEntity.setEmailId("manager@gmail.com");
        authenticationEntity.setRole("manager");
        authenticationEntity.setPassword(passwordEncoder.encode("manager"));
        if(!authenticationRepository.existsByUserId(authenticationEntity.getUserId())){
            authenticationRepository.save(authenticationEntity);
        }
    }
    @KafkaListener(topics = "online-banking-topic",groupId = "authentication")
    private void consumer(AuthRequestDto authRequestDto){
        if(authRequestDto!=null){
            AuthenticationEntity authenticationEntity = modelMapper.map(authRequestDto,AuthenticationEntity.class);
            authenticationEntity.setBankService(authRequestDto.getTypeOfService());
            authenticationEntity.setEmailId(authRequestDto.getEmailId());
            if(!authenticationRepository.existsByUserId(authenticationEntity.getUserId())){
                authenticationRepository.save(authenticationEntity);
            }
            AuthenticationEntity oldDetails = authenticationRepository.findByUserId(authenticationEntity.getUserId()).orElse(new AuthenticationEntity());
            oldDetails.setPassword(oldDetails.getPassword());
            oldDetails.setEmailId(oldDetails.getEmailId());
            authenticationRepository.save(oldDetails);
        }
    }
}
