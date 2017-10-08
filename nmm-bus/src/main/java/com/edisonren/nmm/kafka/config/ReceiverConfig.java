package com.edisonren.nmm.kafka.config;

import com.edisonren.nmm.kafka.Receiver;
import com.edisonren.nmm.v1.NmmModel;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Basically, copy and past. Pretty standard stuff. Not so much to customize.
 *
 * Created by edison on 10/3/17.
 */
@Configuration
@EnableKafka
public class ReceiverConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${KAFKA_SERVER}")
    private String serverAddress;

    @Value("${KAFKA_GROUP_ID}")
    private String groupId;

    @Bean
    public Map<String, Object> consumerConfigs() {
        logger.info("Kafka receiver is being configured on {}:{}", serverAddress, groupId);

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, serverAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return props;
    }

    @Bean
    public ConsumerFactory<String, NmmModel> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(NmmModel.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NmmModel> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, NmmModel> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        return factory;
    }

    @Bean
    public Receiver receiver() {
        return new Receiver();
    }
}
