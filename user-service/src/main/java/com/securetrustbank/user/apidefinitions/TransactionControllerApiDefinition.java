package com.securetrustbank.user.apidefinitions;
import com.securetrustbank.user.dto.AmountDetails;
import com.securetrustbank.user.dto.TransactionDetailsResponse;
import com.securetrustbank.user.dto.TransactionResponse;
import com.securetrustbank.user.exceptions.GreaterThanCurrentBalanceException;
import com.securetrustbank.user.exceptions.NegativeAmountException;
import com.securetrustbank.user.exceptions.AccountNumberNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "user-transactions",description = "User Transaction Functionality")
public interface TransactionControllerApiDefinition {
    @Operation(summary = "deposit functionality")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Deposited"),
            @ApiResponse(responseCode = "409",description = "Negative amount exception"),
            @ApiResponse(responseCode = "406",description = "Validation errors"),
            @ApiResponse(responseCode = "409",description = "Negative amount exception"),
            @ApiResponse(responseCode = "403",description = "access level exception,token exceptions")
    })
    ResponseEntity<TransactionResponse> doDeposit(@RequestBody @Valid AmountDetails amountDetails) throws AccountNumberNotFoundException,
            NegativeAmountException, GreaterThanCurrentBalanceException;
    @Operation(summary = "withdraw functionality")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "WithDrawn"),
            @ApiResponse(responseCode = "409",description = "Negative amount exception"),
            @ApiResponse(responseCode = "406",description = "Validation errors"),
            @ApiResponse(responseCode = "409",description = "Negative amount exception,Greater Than current balance exception"),
            @ApiResponse(responseCode = "403",description = "access level exception,token exceptions")
    })
    ResponseEntity<TransactionResponse> doWithdraw(@RequestBody @Valid AmountDetails amountDetails) throws AccountNumberNotFoundException,
            NegativeAmountException, GreaterThanCurrentBalanceException;

    @Operation(summary = "View List of Transactions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "view all transactions")
    })
    ResponseEntity<List<TransactionDetailsResponse>> getAllTransactions();

}
