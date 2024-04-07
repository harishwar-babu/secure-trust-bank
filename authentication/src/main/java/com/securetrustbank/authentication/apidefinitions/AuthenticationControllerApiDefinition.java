package com.securetrustbank.authentication.apidefinitions;

import com.securetrustbank.authentication.dto.LoginDetails;
import com.securetrustbank.authentication.exceptions.NoAccessAvailableException;
import com.securetrustbank.authentication.exceptions.NotValidServiceException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import javax.security.auth.login.AccountNotFoundException;

@Tag(name = "authentication",description = "Login functionality for the customers as well as the managers")
public interface AuthenticationControllerApiDefinition {
    @Operation(summary = "user authentication")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "authentication success"),
            @ApiResponse(responseCode = "401",description = "credentials are not correct"),
            @ApiResponse(responseCode = "406",description = "Validation errors"),
            @ApiResponse(responseCode = "409",description = "requestParameters missing"),
            @ApiResponse(responseCode = "403",description = "access level exception")
    })
    ResponseEntity<String> login(@Valid @RequestBody LoginDetails loginDetails,@RequestParam String type)
            throws NotValidServiceException, AccountNotFoundException, NoAccessAvailableException;
}
