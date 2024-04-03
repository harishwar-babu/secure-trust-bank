package com.securetrustbank.onlinebank.repository;

import com.securetrustbank.onlinebank.entity.AccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<AccountDetailsEntity,String> {
    boolean existsByUserId(String userId);
    @Query("select accountNumber from AccountDetailsEntity account where account.userId=:userId")
    String getAccountNumber(@Param("userId") String userId);

    Optional<AccountDetailsEntity> findByAccountNumber(String accountNumber);
}
