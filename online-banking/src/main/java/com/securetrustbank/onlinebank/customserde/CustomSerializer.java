package com.securetrustbank.onlinebank.customserde;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.securetrustbank.onlinebank.dto.TransactionResponse;
import com.securetrustbank.onlinebank.exceptions.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class CustomSerializer implements Serializer<TransactionResponse> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, TransactionResponse data) {
       try {
           return objectMapper.writeValueAsBytes(data);
       }
       catch (JsonProcessingException exception){
           throw new SerializationException("cannot serialize data");
       }
    }

    @Override
    public byte[] serialize(String topic, Headers headers, TransactionResponse data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        }
        catch (JsonProcessingException exception){
            throw new SerializationException("cannot serialize data");
        }
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
