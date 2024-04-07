package com.securetrustbank.onlinebank.service;
import com.securetrustbank.onlinebank.dto.AmountDetails;
import com.securetrustbank.onlinebank.dto.TransactionResponse;
import com.securetrustbank.onlinebank.exceptions.AccountNumberNotFoundException;
import com.securetrustbank.onlinebank.exceptions.GreaterThanCurrentBalanceException;
import com.securetrustbank.onlinebank.exceptions.NegativeAmountException;
public interface TransactionService {
    TransactionResponse doTransaction(String userId, AmountDetails amountDetails) throws
            AccountNumberNotFoundException, NegativeAmountException,
            GreaterThanCurrentBalanceException;
}
