package com.securetrustbank.transactions.controller;

import com.securetrustbank.transactions.apidefinitions.TransactionDetailsApiDefinition;
import com.securetrustbank.transactions.dto.TransactionDetailsResponse;
import com.securetrustbank.transactions.service.TransactionDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@AllArgsConstructor
public class TransactionDetailsController implements TransactionDetailsApiDefinition {
    private final TransactionDetailsService transactionDetailsService;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('customer')")
    public ResponseEntity<List<TransactionDetailsResponse>> getAllTransactions(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = ((UserDetails)principal).getUsername();
        return ResponseEntity.ok(transactionDetailsService.getAllTransactions(userId));
    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('manager')")
    public ResponseEntity<List<TransactionDetailsResponse>> getAllAccountTransactions(){
        return ResponseEntity.ok(transactionDetailsService.getAllAccountTransactions());
    }
    @GetMapping("/userTransaction")
    @PreAuthorize("hasAuthority('manager')")
    public ResponseEntity<List<TransactionDetailsResponse>> getAllCustomerTransactions(@PathVariable String userId){
        return ResponseEntity.ok(transactionDetailsService.getAllTransactions(userId));
    }
}
