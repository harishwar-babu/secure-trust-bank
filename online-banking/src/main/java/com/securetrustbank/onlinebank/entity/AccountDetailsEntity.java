package com.securetrustbank.onlinebank.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsEntity {
    @Id
    private String accountNumber;
    private String userId;
    private Double currentBalance;
    @PrePersist
    void setDefaultValue(){
        if(this.currentBalance==null){
            this.currentBalance=0.00;
        }
    }
}
