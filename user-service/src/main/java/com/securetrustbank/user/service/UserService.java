package com.securetrustbank.user.service;
import com.securetrustbank.user.dto.*;
import com.securetrustbank.user.exceptions.AccountNumberNotFoundException;
import com.securetrustbank.user.exceptions.GreaterThanCurrentBalanceException;
import com.securetrustbank.user.exceptions.NegativeAmountException;
import com.securetrustbank.user.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import java.util.List;
public interface UserService {
    UserDetailsDto viewProfile(String userId) throws UserNotFoundException;
    List<UserDetailsDto> viewAllUserProfiles();

    UserDetailsDto updateProfile(String userId, UpdateDetailsDto updateDetails) throws UserNotFoundException;

    ResponseEntity<TransactionResponse> doDeposit(AmountDetails amountDetails) throws
            AccountNumberNotFoundException, NegativeAmountException, GreaterThanCurrentBalanceException;

    ResponseEntity<TransactionResponse> doWithdraw(AmountDetails amountDetails) throws
            AccountNumberNotFoundException, NegativeAmountException, GreaterThanCurrentBalanceException;

    ResponseEntity<List<TransactionDetailsResponse>> getAllTransactions();

}
