package com.edisonren.nmm.kafka;

import com.edisonren.nmm.v1.NmmMessage;
import com.edisonren.nmm.v1.NmmModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Nonnull;

/**
 * Created by edison on 10/5/17.
 */
public class Sender {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    private KafkaTemplate<String, NmmMessage> kafkaTemplate;

    public Sender(KafkaTemplate<String, NmmMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // ServiceName is used as topic
    public void send(@Nonnull NmmMessage message) {
        NmmModel model = message.getModel();

        LOGGER.info("sending model {} to {}",
                model.getScenarioInfo().getScenarioId(), message.getServiceName());

        kafkaTemplate.send(message.getServiceName(), message);
    }
}
