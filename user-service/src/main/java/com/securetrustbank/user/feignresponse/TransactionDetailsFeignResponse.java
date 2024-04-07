package com.securetrustbank.user.feignresponse;
import com.securetrustbank.user.dto.TransactionDetailsResponse;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.ArrayList;
import java.util.List;
@FeignClient(name = "transaction-service",url = "/securebnk/transactions")
public interface TransactionDetailsFeignResponse {
    @GetMapping("/")
    @PreAuthorize("hasAuthority('customer')")
    @CircuitBreaker(name = "transaction-service",fallbackMethod = "transactionDetailsFallBack")
    ResponseEntity<List<TransactionDetailsResponse>> getAllTransactions();

    default ResponseEntity<List<TransactionDetailsResponse>> transactionDetailsFallBack(CallNotPermittedException exception){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new ArrayList<>());
    }
}
