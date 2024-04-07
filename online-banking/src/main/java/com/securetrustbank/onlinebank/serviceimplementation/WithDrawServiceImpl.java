package com.securetrustbank.onlinebank.serviceimplementation;
import com.securetrustbank.onlinebank.configurations.TransactionConfiguration;
import com.securetrustbank.onlinebank.dto.AmountDetails;
import com.securetrustbank.onlinebank.dto.TransactionResponse;
import com.securetrustbank.onlinebank.entity.AccountDetailsEntity;
import com.securetrustbank.onlinebank.exceptions.AccountNumberNotFoundException;
import com.securetrustbank.onlinebank.exceptions.GreaterThanCurrentBalanceException;
import com.securetrustbank.onlinebank.exceptions.NegativeAmountException;
import com.securetrustbank.onlinebank.exceptions.NotValidTransactionException;
import com.securetrustbank.onlinebank.repository.TransactionRepository;
import com.securetrustbank.onlinebank.service.TransactionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
@AllArgsConstructor
@Service
public class WithDrawServiceImpl implements TransactionService {
    private TransactionConfiguration transactionConfiguration;
    private TransactionRepository transactionRepository;
    private ModelMapper modelMapper;
    private KafkaTemplate<String, TransactionResponse> transactionResponseKafkaTemplate;
    @Override
    public TransactionResponse doTransaction(String userId, AmountDetails amountDetails) throws AccountNumberNotFoundException, NegativeAmountException, GreaterThanCurrentBalanceException {
        if(!transactionRepository.existsByUserId(userId)){
            throw new AccountNumberNotFoundException("account does not exists");
        }
        if(amountDetails.getAmount()<=0){
            throw new NegativeAmountException("amount should be greater than zero");
        }
        String accountNumber = transactionRepository.getAccountNumber(userId);
        AccountDetailsEntity accountDetailsEntity = transactionRepository.findByAccountNumber(accountNumber)
                .orElse(new AccountDetailsEntity());
        if(amountDetails.getAmount()>accountDetailsEntity.getCurrentBalance()){
            throw new GreaterThanCurrentBalanceException("can't withdraw since your balance is :"
                    +accountDetailsEntity.getCurrentBalance());
        }
        accountDetailsEntity.setCurrentBalance(accountDetailsEntity.getCurrentBalance()-amountDetails.getAmount());
        transactionRepository.save(accountDetailsEntity);
        TransactionResponse transactionResponse = modelMapper.map(accountDetailsEntity,TransactionResponse.class);
        transactionResponse.setTransactionDate(LocalDateTime.now());
        transactionResponse.setTransactionStatus(transactionConfiguration.getSuccess());
        transactionResponse.setTransactionType(transactionConfiguration.getWithdraw());
        transactionResponse.setWithdrawAmount(amountDetails.getAmount());
        transactionResponse.setCurrentBalance(accountDetailsEntity.getCurrentBalance());
        transactionResponseKafkaTemplate.send("online-banking-transaction",transactionResponse);
        return transactionResponse;
    }
}
