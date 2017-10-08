package com.edisonren.nmm;

import com.edisonren.nmm.v1.NmmMessage;
import com.edisonren.nmm.v1.NmmModel;
import com.edisonren.nmm.v1.NmmOperation;
import com.edisonren.nmm.v1.Scenario;
import com.edisonren.nmm.v1.ScenarioInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.kafka.test.assertj.KafkaConditions.key;

/**
 * Created by edison on 10/7/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class SenderTest {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static String SENDER_TOPIC = "sender.t";

    private KafkaMessageListenerContainer<String, NmmMessage> container;
    private BlockingQueue<ConsumerRecord<String, NmmMessage>> records;

    @Autowired private Sender sender;

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, SENDER_TOPIC);

    @Before
    public void setUp() throws Exception {
        // set up the Kafka consumer properties
        Map<String, Object> consumerProperties =
                KafkaTestUtils.consumerProps("sender", "false", embeddedKafka);

        // create a Kafka consumer factory
        DefaultKafkaConsumerFactory<String, NmmMessage> consumerFactory =
                new DefaultKafkaConsumerFactory<>(consumerProperties);

        // set the topic that needs to be consumed
        ContainerProperties containerProperties = new ContainerProperties(SENDER_TOPIC);

        // create a Kafka MessageListenerContainer
        container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
        // create a thread safe queue to store the received message
        records = new LinkedBlockingQueue<>();

        // setup a Kafka message listener
        container.setupMessageListener(new MessageListener<String, NmmMessage>() {
            @Override
            public void onMessage(ConsumerRecord<String, NmmMessage> record) {
                LOGGER.debug("test-listener received message='{}'", record.toString());
                records.add(record);
            }
        });

        // start the container and underlying message listener
        container.start();

        // wait until the container has the required number of assigned partitions
        ContainerTestUtils.waitForAssignment(container, embeddedKafka.getPartitionsPerTopic());
    }

    @After
    public void tearDown() {
        // stop the container
        container.stop();
    }

    @Ignore
    public void testSend() throws InterruptedException {
        // send the message
        sender.send(createNmmMessage());

        // check that the message was received
        ConsumerRecord<String, NmmMessage> received = records.poll(10, TimeUnit.SECONDS);
        // Hamcrest Matchers to check the value
//        LOGGER.info(received.value().getServiceName());
        //assertThat(received, hasValue(greeting));
        // AssertJ Condition to check the key
        //assertThat(received).has(key(null));
        LOGGER.info("shiiiiit");
    }

    private NmmMessage createNmmMessage() {
        Scenario scenario = new Scenario();
        scenario.setServiceName(SENDER_TOPIC);

        NmmModel model = new NmmModel();
        model.setScenario(scenario);
        model.setScenarioInfo(new ScenarioInfo());

        return new NmmMessage(NmmOperation.CREATE, model);
    }
}