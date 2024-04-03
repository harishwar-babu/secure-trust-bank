package com.securetrustbank.transactions.apidefinitions;
import com.securetrustbank.transactions.dto.TransactionDetailsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import java.util.List;
@Tag(name = "online-banking-Transaction",description = "Online Banking Transaction Details Functionality")
public interface TransactionDetailsApiDefinition {
    @Operation(summary = "Transaction Details For an account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "displays a list of transactions for a single account"),
            @ApiResponse(responseCode = "401",description = "authorization exceptions")
    })
    ResponseEntity<List<TransactionDetailsResponse>> getAllTransactions();
    @Operation(summary = "Transaction Details For All Account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "displays a list of transactions for all account"),
            @ApiResponse(responseCode = "401",description = "authorization exceptions")
    })
    ResponseEntity<List<TransactionDetailsResponse>> getAllAccountTransactions();
}
