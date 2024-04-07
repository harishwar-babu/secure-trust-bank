package com.securetrustbank.authentication.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class LoginDetails {
    private String userId;
    private String emailId;
    @NotBlank(message = "password is required")
    private String password;
}
