package com.securetrustbank.authentication.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationEntity {
    @Id
    private String userId;
    private String emailId;
    private String password;
    private String bankService;
    private String creditCardService;
    private String role;
}
