package com.securetrustbank.transactions.service;
import com.securetrustbank.transactions.dto.TransactionDetailsResponse;
import java.util.List;

public interface TransactionDetailsService {
    List<TransactionDetailsResponse> getAllTransactions(String userId);
    List<TransactionDetailsResponse> getAllAccountTransactions();
}
