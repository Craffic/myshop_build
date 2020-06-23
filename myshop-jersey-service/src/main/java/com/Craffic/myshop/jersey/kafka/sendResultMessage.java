package com.Craffic.myshop.jersey.kafka;

import com.Craffic.myshop.jersey.Utils.IdGenerator;
import com.Craffic.myshop.jersey.constant.TopicConstant;
import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;

@Slf4j
public class sendResultMessage {

    private KafkaTemplate<String, String> kafkaTemplate;

    private String topic;

    public void sendMessage(String message){
        // 消息唯一ID
        String msgId = IdGenerator.getUUID();
        ListenableFuture listenableFuture = kafkaTemplate.send(TopicConstant.SIMPLE,message);
        listenableFuture.addCallback(
                o -> log.info("发送消息成功：msgId={}, content:{}", msgId, message),
                throwable -> log.info("消息发送失败,{}" + throwable.getMessage())
        );
    }
    
}
