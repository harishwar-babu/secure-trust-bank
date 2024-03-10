package com.securetrustbank.registration.repository;
import com.securetrustbank.registration.entity.UserRegistrationDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<UserRegistrationDetailsEntity,String> {
    //check for duplicates.
    boolean existsByPanNumberAndAadharNumber(String panNumber,String aadharNumber);
    boolean existsByUserIdAndTypeOfService(String userId,String typeOfService);
    //get the details.for applying credit card.
    Optional<UserRegistrationDetailsEntity> findByUserId(String userId);
}
