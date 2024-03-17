package com.securetrustbank.authentication.dto;
import lombok.Data;

@Data
public class AuthRequestDto {
    private String userId;
    private String emailId;
    private String password;
    private String typeOfService;
    private String role;
}
