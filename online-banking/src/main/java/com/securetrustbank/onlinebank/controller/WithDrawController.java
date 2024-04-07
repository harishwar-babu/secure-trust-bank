package com.securetrustbank.onlinebank.controller;

import com.securetrustbank.onlinebank.apidefinitions.WithdrawApiDefinition;
import com.securetrustbank.onlinebank.dto.AmountDetails;
import com.securetrustbank.onlinebank.dto.TransactionResponse;
import com.securetrustbank.onlinebank.exceptions.AccountNumberNotFoundException;
import com.securetrustbank.onlinebank.exceptions.GreaterThanCurrentBalanceException;
import com.securetrustbank.onlinebank.exceptions.NegativeAmountException;
import com.securetrustbank.onlinebank.service.TransactionService;
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
public class WithDrawController implements WithdrawApiDefinition {
    private final TransactionService transactionService;
    @Autowired
    public WithDrawController(@Qualifier("withDrawServiceImpl")TransactionService transactionService){
        this.transactionService=transactionService;
    }
    @PostMapping("/transactionType/withDraw")
    @PreAuthorize("hasAuthority('customer')")
    public ResponseEntity<TransactionResponse> doWithdraw(@RequestBody AmountDetails amountDetails)
            throws AccountNumberNotFoundException, NegativeAmountException, GreaterThanCurrentBalanceException {
         Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         String userId = ((UserDetails)principal).getUsername();
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.doTransaction(
                userId,amountDetails));
    }
}