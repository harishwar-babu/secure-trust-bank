package com.securetrustbank.registration.repository;
import com.securetrustbank.registration.entity.AccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<AccountDetailsEntity,String> {
    //check for duplicates.
    boolean existsByPanNumberAndAadharNumber(String panNumber,String aadharNumber);
    boolean existsByUserIdAndTypeOfService(String userId,String typeOfService);
    //get the details.for applying credit card.
    Optional<AccountDetailsEntity> findByUserId(String userId);
}
