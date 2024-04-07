package com.securetrustbank.transactions.serviceimplementation;

import com.securetrustbank.transactions.dto.TransactionResponse;
import com.securetrustbank.transactions.entity.TransactionDetailsEntity;
import com.securetrustbank.transactions.repositiory.TransactionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
@AllArgsConstructor
public class RecordConsumingService {
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;
    @KafkaListener(topics = "online-banking-transaction",groupId = "test")
    private void consume(TransactionResponse transactionResponse){
        TransactionDetailsEntity transactionDetailsEntity = modelMapper.map(transactionResponse, TransactionDetailsEntity.class);
        transactionRepository.save(transactionDetailsEntity);


    }
}
