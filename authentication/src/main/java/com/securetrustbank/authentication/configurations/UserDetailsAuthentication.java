package com.securetrustbank.authentication.configurations;

import com.securetrustbank.authentication.entity.AuthenticationEntity;
import com.securetrustbank.authentication.repository.AuthenticationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;


public class UserDetailsAuthentication implements UserDetailsService {
    @Autowired
    private AuthenticationRepository authenticationRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<AuthenticationEntity> userInformation;
        userInformation=authenticationRepository.findByUserId(userName);
        if(userInformation.isEmpty()){
            userInformation = authenticationRepository.findByEmailId(userName);
        }
        return userInformation.map(UserInfoToUserService::new).orElseThrow(()->
                new UsernameNotFoundException("user does not exists"));
    }
}
