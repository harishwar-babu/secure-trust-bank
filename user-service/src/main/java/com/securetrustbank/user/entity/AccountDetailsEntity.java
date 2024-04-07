package com.securetrustbank.user.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsEntity {
    @Id
    private String accountNumber;
    private String userId;
    private String firstName;
    private String lastName;
    private String aadharNumber;
    private String panNumber;
    private String mobileNumber;
    private String email;
    private LocalDate datOfBirth;
    private String password;
    private String role;
    private Double currentBalance;
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
