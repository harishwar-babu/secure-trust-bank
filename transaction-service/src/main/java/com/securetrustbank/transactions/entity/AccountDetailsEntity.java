package com.securetrustbank.transactions.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetailsEntity {
    @Id
    private String accountNumber;
    private String userId;
    private Double currentBalance;
    @OneToMany(targetEntity = TransactionDetailsEntity.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "accountNumber",referencedColumnName = "accountNumber")
    private List<TransactionDetailsEntity> transactionDetails;
    @PrePersist
    void setDefaultValue(){
        if(this.currentBalance==null){
            this.currentBalance=0.00;
        }
    }
}
