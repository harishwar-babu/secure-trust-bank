package com.securetrustbank.admin.feignresponse;

import com.securetrustbank.admin.dto.TransactionDetailsResponse;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.List;
@FeignClient(name = "transaction-service",url = "/securebnk/transactions")
public interface TransactionDetailsFeignResponse {
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('manager')")
    @CircuitBreaker(name = "transaction-service",fallbackMethod = "transactionDetailsFallBack")
    ResponseEntity<List<TransactionDetailsResponse>> getAllAccountTransactions();

    @GetMapping("/userTransaction")
    @PreAuthorize("hasAuthority('manager')")
    @CircuitBreaker(name = "transaction-service",fallbackMethod = "transactionDetailsFallBack")
    ResponseEntity<List<TransactionDetailsResponse>> getAllCustomerTransactions(@PathVariable String userId);

    default ResponseEntity<List<TransactionDetailsResponse>> transactionDetailsFallBack(CallNotPermittedException exception){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new ArrayList<>());
    }
}
