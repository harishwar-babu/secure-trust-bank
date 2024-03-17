package com.securetrustbank.authentication.serviceimplementation;
import com.securetrustbank.authentication.dto.LoginDetails;
import com.securetrustbank.authentication.exceptions.NotValidServiceException;
import com.securetrustbank.authentication.repository.AuthenticationRepository;
import com.securetrustbank.authentication.service.AuthenticationService;
import com.securetrustbank.authentication.tokengeneration.GenerateJwtToken;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import javax.security.auth.login.AccountNotFoundException;
@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final GenerateJwtToken generateJwtToken;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationRepository authenticationRepository;
    @Override
    public String authenticate(LoginDetails loginDetails, String type) throws NotValidServiceException, AccountNotFoundException {
        if(!type.equals("Online_Banking") && !type.equals("Credit_Card")){
            throw new NotValidServiceException("invalid type of service");
        }
        String userName = loginDetails.getEmailId()!=null? loginDetails.getEmailId() : loginDetails.getUserId();
        String password = loginDetails.getPassword();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (userName,password));
        if(!authentication.isAuthenticated()){
            throw new AccountNotFoundException("account does not exists");
        }
        return generateJwtToken.generateToken(userName);
    }
    //still online-banking-access level authentication and credit-card-access level authentication is pending.
}
