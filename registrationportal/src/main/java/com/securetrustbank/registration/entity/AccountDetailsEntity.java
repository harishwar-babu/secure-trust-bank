package com.securetrustbank.registration.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "accountNumber")
    @GenericGenerator(name = "accountNumber",type = AccountNumberGenerator.class)
    private String accountNumber;
    private String userId;
    @NotBlank(message = "firstName is required")
    private String firstName;
    @NotBlank(message = "lastName is required")
    private String lastName;
    @NotBlank(message = "Aadhar card is required")
    @Pattern(regexp = "[0-9]{12}",message = "enter a valid Aadhar number")
    private String aadharNumber;
    @NotBlank(message = "PAN number is required")
    @Pattern(regexp = "[a-zA-Z]{5}+[0-9]{4}+[a-zA-Z]{1}",message = "enter a valid PAN number")
    private String panNumber;
    @NotBlank(message = "mobile Number is required")
    @Pattern(regexp = "[0-9]{10}",message = "enter a valid mobile number")
    private String mobileNumber;
    @Email(regexp = "[a-z0-9_.e+%#]+@[a-z]+.[a-z]{2,3}",flags = Pattern.Flag.CASE_INSENSITIVE,message = "enter a valid email")
    private String email;
    private LocalDate datOfBirth;
    private String typeOfService; //(online-banking,credit-card -->(details will be fetched automatically when the user apply credit card))
    @NotBlank(message = "password is required")
    private String password;
    private String role;
    @Transient
    @NotBlank(message = "confirm password is needed")
    private String confirmPassword;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
