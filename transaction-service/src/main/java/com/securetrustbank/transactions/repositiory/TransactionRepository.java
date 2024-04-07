package com.securetrustbank.transactions.repositiory;
import com.securetrustbank.transactions.entity.TransactionDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionDetailsEntity,Integer> {
}
