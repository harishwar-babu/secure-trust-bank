package com.securetrustbank.registration.serviceimplementation;
import com.securetrustbank.registration.customserde.AuthRequestSerde;
import com.securetrustbank.registration.dto.AuthRequestDto;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.stereotype.Component;
@Component
@AllArgsConstructor
public class RecordProcessingService {
    private final StreamsBuilder streamsBuilder;
    private final AuthRequestSerde authRequestSerde;
    @PostConstruct
    private void buildTopology(){
        KStream<String,AuthRequestDto> inputTopicRecord = streamsBuilder.stream("user-details-topic",
                Consumed.with(Serdes.String(),authRequestSerde));
        inputTopicRecord.filter(((key, value) -> value!=null && value.getTypeOfService().equals("Online_Banking"))).
                to("online-banking-topic", Produced.with(Serdes.String(),authRequestSerde));
        inputTopicRecord.filter(((key, value) -> value!=null && value.getTypeOfService().equals("Credit_Card"))).
                to("credit-card-topic", Produced.with(Serdes.String(),authRequestSerde));

    }
}
