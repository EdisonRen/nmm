package com.edisonren.nmm;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

/**
 * Created by edison on 10/5/17.
 */
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    // topic should be service name, determined on client side
    @KafkaListener(topics = "${KAFKA_TOPIC}")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        // TODO: trim this log
        LOGGER.info("received {}", consumerRecord.toString());
        latch.countDown();
    }
}
