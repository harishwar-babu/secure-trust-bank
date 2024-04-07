package com.securetrustbank.user.repository;
import com.securetrustbank.user.entity.AccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<AccountDetailsEntity,String> {
    Optional<AccountDetailsEntity> findByUserId(String userId);

}
