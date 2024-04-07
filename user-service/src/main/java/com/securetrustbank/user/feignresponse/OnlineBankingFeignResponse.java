package com.securetrustbank.user.feignresponse;
import com.securetrustbank.user.dto.AmountDetails;
import com.securetrustbank.user.dto.TransactionResponse;
import com.securetrustbank.user.exceptions.AccountNumberNotFoundException;
import com.securetrustbank.user.exceptions.GreaterThanCurrentBalanceException;
import com.securetrustbank.user.exceptions.NegativeAmountException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "online-banking-service",url = "/securebnk/online-banking")
public interface OnlineBankingFeignResponse {
    @PostMapping("/transactionType/deposit")
    @PreAuthorize("hasAuthority('customer')")
    @CircuitBreaker(name="online-banking-service",fallbackMethod = "transactionFallBack")
    ResponseEntity<TransactionResponse> doDeposit(@RequestBody @Valid AmountDetails amountDetails)
            throws AccountNumberNotFoundException, NegativeAmountException, GreaterThanCurrentBalanceException;
    @PostMapping("/transactionType/withDraw")
    @PreAuthorize("hasAuthority('customer')")
    @CircuitBreaker(name="online-banking-service",fallbackMethod = "transactionFallBack")
    ResponseEntity<TransactionResponse> doWithdraw(@RequestBody AmountDetails amountDetails)
            throws AccountNumberNotFoundException, NegativeAmountException, GreaterThanCurrentBalanceException;

    default ResponseEntity<TransactionResponse> transactionFallBack(CallNotPermittedException exception){
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new TransactionResponse());
    }

}
