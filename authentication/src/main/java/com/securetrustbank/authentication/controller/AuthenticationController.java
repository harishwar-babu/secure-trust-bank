package com.securetrustbank.authentication.controller;

import com.securetrustbank.authentication.apidefinitions.AuthenticationControllerApiDefinition;
import com.securetrustbank.authentication.dto.LoginDetails;
import com.securetrustbank.authentication.exceptions.NoAccessAvailableException;
import com.securetrustbank.authentication.exceptions.NotValidServiceException;
import com.securetrustbank.authentication.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.security.auth.login.AccountNotFoundException;

@RestController
@AllArgsConstructor
public class AuthenticationController implements AuthenticationControllerApiDefinition {
    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDetails loginDetails, @RequestParam String type)
            throws NotValidServiceException, AccountNotFoundException, NoAccessAvailableException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationService.authenticate(loginDetails,type));
    }
}
