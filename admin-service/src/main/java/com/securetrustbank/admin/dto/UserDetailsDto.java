package com.securetrustbank.admin.dto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDetailsDto {
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
}
