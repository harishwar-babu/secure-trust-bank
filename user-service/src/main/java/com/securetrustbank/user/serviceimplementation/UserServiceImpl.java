package com.securetrustbank.user.serviceimplementation;
import com.securetrustbank.user.dto.*;
import com.securetrustbank.user.entity.AccountDetailsEntity;
import com.securetrustbank.user.exceptions.AccountNumberNotFoundException;
import com.securetrustbank.user.exceptions.GreaterThanCurrentBalanceException;
import com.securetrustbank.user.exceptions.NegativeAmountException;
import com.securetrustbank.user.exceptions.UserNotFoundException;
import com.securetrustbank.user.feignresponse.OnlineBankingFeignResponse;
import com.securetrustbank.user.feignresponse.TransactionDetailsFeignResponse;
import com.securetrustbank.user.repository.UserRepository;
import com.securetrustbank.user.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final KafkaTemplate<String, AuthRequestDto> kafkaTemplate;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final OnlineBankingFeignResponse onlineBankingFeignResponse;
    private final TransactionDetailsFeignResponse transactionDetailsFeignResponse;

    @Override
    public UserDetailsDto viewProfile(String userId) throws UserNotFoundException {
        AccountDetailsEntity accountDetails = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        return modelMapper.map(accountDetails, UserDetailsDto.class);
    }

    @Override
    public List<UserDetailsDto> viewAllUserProfiles() {
        return userRepository.findAll().stream().
                map(value->modelMapper.map(value,UserDetailsDto.class)).toList();
    }

    @Override
    public UserDetailsDto updateProfile(String userId, UpdateDetailsDto updateDetails) throws UserNotFoundException {
        AccountDetailsEntity accountDetails = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
        accountDetails.setEmail(updateDetails.getEmailId());
        accountDetails.setPassword(updateDetails.getPassword());
        AuthRequestDto authRequestDto = modelMapper.map(accountDetails, AuthRequestDto.class);
        kafkaTemplate.send("online-banking-topic",accountDetails.getUserId(),authRequestDto);
        return modelMapper.map(accountDetails, UserDetailsDto.class);
    }

    @Override
    public ResponseEntity<TransactionResponse> doDeposit(AmountDetails amountDetails)
            throws AccountNumberNotFoundException, NegativeAmountException, GreaterThanCurrentBalanceException {
        return onlineBankingFeignResponse.doDeposit(amountDetails);
    }

    @Override
    public ResponseEntity<TransactionResponse> doWithdraw(AmountDetails amountDetails) throws
            AccountNumberNotFoundException, NegativeAmountException, GreaterThanCurrentBalanceException {
        return onlineBankingFeignResponse.doWithdraw(amountDetails);
    }

    @Override
    public ResponseEntity<List<TransactionDetailsResponse>> getAllTransactions() {
        return transactionDetailsFeignResponse.getAllTransactions();
    }
}
