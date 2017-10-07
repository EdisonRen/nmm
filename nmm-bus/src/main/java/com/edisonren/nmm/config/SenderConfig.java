package com.edisonren.nmm.config;

import com.edisonren.nmm.Sender;
import com.edisonren.nmm.v1.NmmMessage;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by edison on 10/3/17.
 */
@Configuration
public class SenderConfig {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${KAFKA_SERVER}")
    private String serverAddress;

    @Bean
    public Map<String, Object> producerConfigs() {
        logger.info("Kafka receiver is being configured on {}", serverAddress);

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, serverAddress);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // ObjectMapper underlying

        return props;
    }

    @Bean
    public ProducerFactory<String, NmmMessage> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, NmmMessage> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public Sender sender(KafkaTemplate<String, NmmMessage> template) {
        return new Sender(kafkaTemplate());
    }
}
