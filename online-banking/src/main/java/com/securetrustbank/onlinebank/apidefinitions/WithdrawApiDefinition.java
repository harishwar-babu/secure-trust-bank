package com.securetrustbank.onlinebank.apidefinitions;

import com.securetrustbank.onlinebank.dto.AmountDetails;
import com.securetrustbank.onlinebank.dto.TransactionResponse;
import com.securetrustbank.onlinebank.exceptions.AccountNumberNotFoundException;
import com.securetrustbank.onlinebank.exceptions.GreaterThanCurrentBalanceException;
import com.securetrustbank.onlinebank.exceptions.NegativeAmountException;
import com.securetrustbank.onlinebank.exceptions.NotValidTransactionException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "online-banking-withdraw",description = "Online Banking Withdraw Functionality")
public interface WithdrawApiDefinition {
    @Operation(summary = "withdraw functionality")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Withdrawn"),
            @ApiResponse(responseCode = "409",description = "Negative amount exception,Greater than current balance exception"),
            @ApiResponse(responseCode = "406",description = "Validation errors"),
            @ApiResponse(responseCode = "409",description = "requestParameters missing,Negative amount exception"),
            @ApiResponse(responseCode = "403",description = "access level exception,token exceptions")
    })
    ResponseEntity<TransactionResponse> doWithdraw(@RequestBody @Valid AmountDetails amountDetails, @RequestParam
    String transactionType) throws AccountNumberNotFoundException, NegativeAmountException,
            GreaterThanCurrentBalanceException, NotValidTransactionException;
}