package com.securetrustbank.registration.serviceimplementation;
import com.securetrustbank.registration.configurations.MessageConfigurations;
import com.securetrustbank.registration.configurations.RoleConfigurations;
import com.securetrustbank.registration.dto.AuthRequestDto;
import com.securetrustbank.registration.dto.BankAccountCreationResponse;
import com.securetrustbank.registration.entity.AccountDetailsEntity;
import com.securetrustbank.registration.exceptions.*;
import com.securetrustbank.registration.repository.RegistrationRepository;
import com.securetrustbank.registration.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
@Service
@AllArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final ModelMapper modelMapper;
    private final MessageConfigurations messageConfigurations;
    private final RegistrationRepository registrationRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleConfigurations roleConfigurations;
    private final KafkaTemplate<String, AuthRequestDto> authRequestDtoKafkaTemplate;

    @Override
    public BankAccountCreationResponse applyForBankAccount(String type, AccountDetailsEntity userRegistrationDetails)
            throws UserDetailsAlreadyExistsException, NotValidServiceException {
        if(!type.equals("Online_Banking")){
            throw new NotValidServiceException("invalid type of service");
        }
        if(registrationRepository.existsByPanNumberAndAadharNumber(userRegistrationDetails.getPanNumber(),
                userRegistrationDetails.getAadharNumber())) {
            throw new UserDetailsAlreadyExistsException("user exists");
        }
        if(!userRegistrationDetails.getPassword().equals(userRegistrationDetails.getConfirmPassword())){
            throw new InvalidPasswordException("password and confirmed password is not same");
        }
        userRegistrationDetails.setPassword(passwordEncoder.encode(userRegistrationDetails.getPassword()));
        userRegistrationDetails.setUserId(userRegistrationDetails.getLastName().substring(0,3)+
                userRegistrationDetails.getPanNumber().substring(8,10));
        userRegistrationDetails.setCreatedDate(LocalDateTime.now());
        userRegistrationDetails.setRole(roleConfigurations.getCustomer());
        registrationRepository.save(userRegistrationDetails);
        AuthRequestDto authRequestDto = modelMapper.map(userRegistrationDetails,AuthRequestDto.class);
        BankAccountCreationResponse bankAccountCreationResponse = modelMapper.map(userRegistrationDetails,
                BankAccountCreationResponse.class);
        bankAccountCreationResponse.setStatus(messageConfigurations.getSuccess());
        authRequestDtoKafkaTemplate.send("user-details-topic",userRegistrationDetails.getUserId(),authRequestDto);
        return bankAccountCreationResponse;
    }

    @Override
    public String applyForCreditCard(String userId,String type) throws NotValidServiceException, CreditCardAlreadyAppliedException, NoDetailsAvailableException {
        if(!type.equals("Credit_Card")){
            throw new NotValidServiceException("invalid type of service");
        }
        if(registrationRepository.existsByUserIdAndTypeOfService(userId,type)){
            throw new CreditCardAlreadyAppliedException("this user has already applied for the credit-card");
        }
        AccountDetailsEntity userRegistrationDetails = registrationRepository.findByUserId(userId).
                orElseThrow(()->new NoDetailsAvailableException("no details available"));
        AuthRequestDto authRequestDto = modelMapper.map(userRegistrationDetails,AuthRequestDto.class);
        authRequestDtoKafkaTemplate.send("user-details-topic",userRegistrationDetails.getUserId(),authRequestDto);
        return messageConfigurations.getSuccess()+" "+"waiting for manager approval";
    }
}
