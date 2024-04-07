package com.securetrustbank.registration.service;
import com.securetrustbank.registration.dto.BankAccountCreationResponse;
import com.securetrustbank.registration.entity.AccountDetailsEntity;
import com.securetrustbank.registration.exceptions.CreditCardAlreadyAppliedException;
import com.securetrustbank.registration.exceptions.NoDetailsAvailableException;
import com.securetrustbank.registration.exceptions.NotValidServiceException;
import com.securetrustbank.registration.exceptions.UserDetailsAlreadyExistsException;

public interface RegistrationService {
    BankAccountCreationResponse applyForBankAccount(String type, AccountDetailsEntity userRegistrationDetails) throws UserDetailsAlreadyExistsException, NotValidServiceException;
    String applyForCreditCard(String userId,String type) throws NotValidServiceException, CreditCardAlreadyAppliedException, NoDetailsAvailableException;
}
