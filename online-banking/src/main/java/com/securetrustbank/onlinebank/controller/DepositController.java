package com.securetrustbank.onlinebank.controller;

import com.securetrustbank.onlinebank.apidefinitions.DepositApiDefinition;
import com.securetrustbank.onlinebank.dto.AmountDetails;
import com.securetrustbank.onlinebank.dto.TransactionResponse;
import com.securetrustbank.onlinebank.exceptions.AccountNumberNotFoundException;
import com.securetrustbank.onlinebank.exceptions.GreaterThanCurrentBalanceException;
import com.securetrustbank.onlinebank.exceptions.NegativeAmountException;
import com.securetrustbank.onlinebank.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepositController implements DepositApiDefinition {
    private final TransactionService transactionService;
    @Autowired
    public DepositController(@Qualifier("depositServiceImpl")TransactionService transactionService){
        this.transactionService=transactionService;
    }
    @PostMapping("/transactionType/deposit")
    @PreAuthorize("hasAuthority('customer')")
    public ResponseEntity<TransactionResponse> doDeposit(@RequestBody @Valid AmountDetails amountDetails)
            throws AccountNumberNotFoundException, NegativeAmountException, GreaterThanCurrentBalanceException {
         Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         String userId = ((UserDetails)principal).getUsername();
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.doTransaction(userId,amountDetails));
    }

}
