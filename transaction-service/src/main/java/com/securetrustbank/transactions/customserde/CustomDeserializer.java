package com.securetrustbank.transactions.customserde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.securetrustbank.transactions.exceptions.DeserializationException;
import com.securetrustbank.transactions.dto.TransactionResponse;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

public class CustomDeserializer implements Deserializer<TransactionResponse> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public TransactionResponse deserialize(String topic, byte[] data) {
       try {
           return objectMapper.readValue(data, TransactionResponse.class);
       }
       catch (IOException exception){
           throw new DeserializationException("cannot deserialize the data");
       }
    }

    @Override
    public TransactionResponse deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public TransactionResponse deserialize(String topic, Headers headers, ByteBuffer data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
