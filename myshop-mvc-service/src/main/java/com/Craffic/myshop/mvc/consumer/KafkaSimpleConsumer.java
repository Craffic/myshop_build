package com.Craffic.myshop.mvc.consumer;

import com.Craffic.myshop.jersey.constant.TopicConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
  * 简单消费者
  */
@Slf4j
@Component
public class KafkaSimpleConsumer {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    // 简单消费者
    @KafkaListener(groupId = "simpleGroup", topics = TopicConstant.SIMPLE)
    public void consumer1_1(ConsumerRecord<String, Object> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, Consumer consumer, Acknowledgment ack) {
        logger.info("单独消费者消费消息,topic= {} ,content = {}",topic,record.value());
        logger.info("consumer content = {}",consumer);
        ack.acknowledge();

        /*
         * 如果需要手工提交异步 consumer.commitSync();
         * 手工同步提交 consumer.commitAsync()
         */
    }
}
