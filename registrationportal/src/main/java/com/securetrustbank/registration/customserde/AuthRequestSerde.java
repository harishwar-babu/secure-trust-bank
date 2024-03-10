package com.securetrustbank.registration.customserde;
import com.securetrustbank.registration.dto.AuthRequestDto;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.stereotype.Component;
import java.util.Map;
@Component
@AllArgsConstructor
public class AuthRequestSerde implements Serde<AuthRequestDto> {
    private final CustomSerializer customSerializer;
    private final CustomDeserializer customDeserializer;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serde.super.configure(configs, isKey);
    }

    @Override
    public void close() {
        Serde.super.close();
    }

    @Override
    public Serializer<AuthRequestDto> serializer() {
        return customSerializer;
    }

    @Override
    public Deserializer<AuthRequestDto> deserializer() {
        return customDeserializer;
    }
}
