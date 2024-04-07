package com.securetrustbank.authentication.repository;

import com.securetrustbank.authentication.entity.AuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthenticationRepository extends JpaRepository<AuthenticationEntity,String> {
    boolean existsByUserId(String userId);
    //check if the user has the credit card to login
    boolean existsByCreditCardService(String userId);
    Optional<AuthenticationEntity> findByEmailId(String email);
    Optional<AuthenticationEntity> findByUserId(String userId);
}
