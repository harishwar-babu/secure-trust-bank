package com.securetrustbank.onlinebank.service;
import com.securetrustbank.onlinebank.dto.AmountDetails;
import com.securetrustbank.onlinebank.dto.TransactionResponse;
import com.securetrustbank.onlinebank.exceptions.AccountNumberNotFoundException;
import com.securetrustbank.onlinebank.exceptions.GreaterThanCurrentBalanceException;
import com.securetrustbank.onlinebank.exceptions.NegativeAmountException;
import com.securetrustbank.onlinebank.exceptions.NotValidTransactionException;

public interface TransactionService {
    TransactionResponse doTransaction(String type, String userId, AmountDetails amountDetails) throws
            NotValidTransactionException, AccountNumberNotFoundException, NegativeAmountException,
            GreaterThanCurrentBalanceException;
}
