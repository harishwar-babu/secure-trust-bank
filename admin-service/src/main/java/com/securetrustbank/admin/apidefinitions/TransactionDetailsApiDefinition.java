package com.securetrustbank.admin.apidefinitions;
import com.securetrustbank.admin.dto.TransactionDetailsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Admin-Service-View Transactions",description = "View all User Transactions Functionality")
public interface TransactionDetailsApiDefinition {
    @Operation(summary = "Transaction Details For All Account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "displays a list of transactions for all account"),
            @ApiResponse(responseCode = "401",description = "authorization exceptions")
    })
    ResponseEntity<List<TransactionDetailsResponse>> getAllAccountTransactions();
    @Operation(summary = "Transaction Details For an Account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "displays a list of transactions for all account"),
            @ApiResponse(responseCode = "401",description = "authorization exceptions")
    })
    ResponseEntity<List<TransactionDetailsResponse>> getAllCustomerTransactions(@PathVariable String userId);
}
