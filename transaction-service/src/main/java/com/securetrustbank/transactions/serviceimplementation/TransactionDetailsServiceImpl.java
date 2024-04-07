package com.securetrustbank.transactions.serviceimplementation;
import com.securetrustbank.transactions.dto.TransactionDetailsResponse;
import com.securetrustbank.transactions.entity.AccountDetailsEntity;
import com.securetrustbank.transactions.repositiory.AccountDetailsRepository;
import com.securetrustbank.transactions.service.TransactionDetailsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionDetailsServiceImpl implements TransactionDetailsService {
    private final AccountDetailsRepository accountDetailsRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<TransactionDetailsResponse> getAllTransactions(String userId) {
        String accountNumber = accountDetailsRepository.getAccountNumber(userId);
        List<AccountDetailsEntity> accountDetailsEntities = accountDetailsRepository.findAllByAccountNumber(accountNumber)
                .orElse(new ArrayList<>());
        List<TransactionDetailsResponse> transactionDetails = new ArrayList<>();
        accountDetailsEntities.forEach(accountDetailsEntity -> transactionDetails.addAll(accountDetailsEntity.
                getTransactionDetails().stream().map(val->modelMapper.map(val, TransactionDetailsResponse.class)).toList()));
        return transactionDetails;
    }

    @Override
    public List<TransactionDetailsResponse> getAllAccountTransactions() {
        List<AccountDetailsEntity> accountDetailsEntities = accountDetailsRepository.findAll();
        List<TransactionDetailsResponse> transactionDetails = new ArrayList<>();
        accountDetailsEntities.forEach(accountDetailsEntity -> transactionDetails.addAll(accountDetailsEntity.
                getTransactionDetails().stream().map(val->modelMapper.map(val, TransactionDetailsResponse.class)).toList()));
        return transactionDetails;
    }
}
