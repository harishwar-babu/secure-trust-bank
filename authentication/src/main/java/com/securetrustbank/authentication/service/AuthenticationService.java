package com.securetrustbank.authentication.service;
import com.securetrustbank.authentication.dto.LoginDetails;
import com.securetrustbank.authentication.exceptions.NoAccessAvailableException;
import com.securetrustbank.authentication.exceptions.NotValidServiceException;

import javax.security.auth.login.AccountNotFoundException;

public interface AuthenticationService {
    String authenticate(LoginDetails loginDetails,String type) throws NotValidServiceException, AccountNotFoundException, NoAccessAvailableException;
}
