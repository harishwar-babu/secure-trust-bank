package com.securetrustbank.user.customserde;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.securetrustbank.user.dto.AuthRequestDto;
import com.securetrustbank.user.exceptions.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Map;

public class CustomDeserializer implements Deserializer<AuthRequestDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public AuthRequestDto deserialize(String topic, byte[] data) {
       try {
           return objectMapper.readValue(data, AuthRequestDto.class);
       }
       catch (IOException exception){
           throw new SerializationException("cannot deserialize the data");
       }
    }

    @Override
    public AuthRequestDto deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public AuthRequestDto deserialize(String topic, Headers headers, ByteBuffer data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
