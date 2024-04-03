package com.securetrustbank.registration.controller;
import com.securetrustbank.registration.dto.BankAccountCreationResponse;
import com.securetrustbank.registration.entity.AccountDetailsEntity;
import com.securetrustbank.registration.exceptions.NotValidServiceException;
import com.securetrustbank.registration.exceptions.UserDetailsAlreadyExistsException;
import com.securetrustbank.registration.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    @PostMapping("/apply-for-online-banking")
    public ResponseEntity<BankAccountCreationResponse> applyForOnlineBanking(@RequestParam String type,@RequestBody @Valid
    AccountDetailsEntity userRegistrationDetails) throws NotValidServiceException,UserDetailsAlreadyExistsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationService.applyForBankAccount(type,userRegistrationDetails));
    }
}
