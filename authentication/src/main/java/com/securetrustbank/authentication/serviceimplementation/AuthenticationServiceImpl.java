package com.securetrustbank.authentication.serviceimplementation;
import com.securetrustbank.authentication.dto.LoginDetails;
import com.securetrustbank.authentication.entity.AuthenticationEntity;
import com.securetrustbank.authentication.exceptions.NoAccessAvailableException;
import com.securetrustbank.authentication.exceptions.NotValidServiceException;
import com.securetrustbank.authentication.repository.AuthenticationRepository;
import com.securetrustbank.authentication.service.AuthenticationService;
import com.securetrustbank.authentication.tokengeneration.GenerateJwtToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import javax.security.auth.login.AccountNotFoundException;
@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final GenerateJwtToken generateJwtToken;
    private final AuthenticationManager authenticationManager;
    private final AuthenticationRepository authenticationRepository;
    @Override
    public String authenticate(LoginDetails loginDetails, String type) throws NotValidServiceException,
            AccountNotFoundException, NoAccessAvailableException {
        String userName = loginDetails.getEmailId()!=null?loginDetails.getEmailId(): loginDetails.getUserId();
        if(userName.equals("manager") || userName.equals("manager@gmail.com")){
            return generateJwtToken.generateToken(loginDetails.getUserId(),type);
        }
        if(!type.equals("Online_Banking") && !type.equals("Credit_Card")){
            throw new NotValidServiceException("not a valid service");
        }
        String password = loginDetails.getPassword();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (userName, password));
        if (!authentication.isAuthenticated()) {
            throw new AccountNotFoundException("account does not exists");
        }
        String userId;
        userId = authenticationRepository.findByUserId(userName).orElse(new AuthenticationEntity()).getUserId();
        if (userId == null) {
            userId = authenticationRepository.findByEmailId(userName).orElse(new AuthenticationEntity()).getUserId();
        }
        if(type.equals("Credit_Card") && !hasCreditCard(userId)){
            throw new NoAccessAvailableException("authenticated!! but no access.");
        }
        return generateJwtToken.generateToken(userId,type);
    }
    /**
     * If type mismatch ---> except for manager(exception).
     * If authentication success ---> then... create method() --> type==bank and bankmethod() exists()... similar for credit card.
     */
    private boolean hasCreditCard(String userId){
        return authenticationRepository.existsByCreditCardService(userId);
    }
}
