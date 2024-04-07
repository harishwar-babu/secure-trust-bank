package com.securetrustbank.registration.apidefinitions;
import com.securetrustbank.registration.dto.BankAccountCreationResponse;
import com.securetrustbank.registration.entity.AccountDetailsEntity;
import com.securetrustbank.registration.exceptions.NotValidServiceException;
import com.securetrustbank.registration.exceptions.UserDetailsAlreadyExistsException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
@Tag(name = "Registration Service",description = "Registration-For-Online-Banking")
public interface RegistrationApiDefinition {
    @Operation(summary = "Registration-For-Online-Banking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Registration Done SuccessFully"),
            @ApiResponse(responseCode = "409",description = "User Already Exists or Not a Valid Service or " +
                    "Request Param is missing")

    })
    ResponseEntity<BankAccountCreationResponse> applyForOnlineBanking(@RequestParam String type, @RequestBody @Valid
    AccountDetailsEntity userRegistrationDetails)throws NotValidServiceException, UserDetailsAlreadyExistsException;
}
