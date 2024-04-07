package com.securetrustbank.user.controller;

import com.securetrustbank.user.apidefinitions.TransactionControllerApiDefinition;
import com.securetrustbank.user.dto.AmountDetails;
import com.securetrustbank.user.dto.TransactionDetailsResponse;
import com.securetrustbank.user.dto.TransactionResponse;
import com.securetrustbank.user.exceptions.AccountNumberNotFoundException;
import com.securetrustbank.user.exceptions.GreaterThanCurrentBalanceException;
import com.securetrustbank.user.exceptions.NegativeAmountException;
import com.securetrustbank.user.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@AllArgsConstructor
public class TransactionsController implements TransactionControllerApiDefinition {
    private final UserService userService;
    @PostMapping("/transactionType/deposit")
    @PreAuthorize("hasAuthority('customer')")
    public ResponseEntity<TransactionResponse> doDeposit(@RequestBody @Valid AmountDetails amountDetails)
            throws AccountNumberNotFoundException, NegativeAmountException, GreaterThanCurrentBalanceException{
        return userService.doDeposit(amountDetails);
    }
    @PostMapping("/transactionType/withDraw")
    @PreAuthorize("hasAuthority('customer')")
    public ResponseEntity<TransactionResponse> doWithdraw(@RequestBody AmountDetails amountDetails)
            throws AccountNumberNotFoundException, NegativeAmountException, GreaterThanCurrentBalanceException{
        return userService.doWithdraw(amountDetails);
    }
    @GetMapping("/myTransactions")
    @PreAuthorize("hasAuthority('customer')")
    public ResponseEntity<List<TransactionDetailsResponse>> getAllTransactions(){
        return userService.getAllTransactions();
    }
}
