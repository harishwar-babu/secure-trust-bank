package com.securetrustbank.user.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateDetailsDto {
    @NotNull(message = "email required")
    @Email(regexp = "[a-z0-9_.e+%#]+@[a-z]+.[a-z]{2,3}",flags = Pattern.Flag.CASE_INSENSITIVE,message = "enter a valid email")
    private String emailId;
    @NotNull(message = "password required")
    private String password;
}
