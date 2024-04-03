package com.securetrustbank.transactions.repositiory;
import com.securetrustbank.transactions.entity.AccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
public interface AccountDetailsRepository extends JpaRepository<AccountDetailsEntity,String> {
    Optional<List<AccountDetailsEntity>> findAllByAccountNumber(String accountNumber);
    @Query("select accountNumber from AccountDetailsEntity account where account.userId=:userId")
    String getAccountNumber(@Param("userId") String userId);
}
