package com.Craffic.myshop.jersey.schedule;

import com.Craffic.myshop.jersey.constant.TopicConstant;
import com.Craffic.myshop.jersey.dao.TbKafkaInMsgDao;
import com.Craffic.myshop.jersey.domain.model.TbKafkaInMsg;
import com.Craffic.myshop.jersey.domain.vo.TbKafkaInMsgVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
@EnableScheduling
public class MsgProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private TbKafkaInMsgDao inMsgDao;
    // 日志
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "0 */10 * * * ?")
    public void send() {
        String message = UUID.randomUUID().toString();
        ListenableFuture listenableFuture = kafkaTemplate.send(TopicConstant.SIMPLE,message);
        listenableFuture.addCallback(
                o -> logger.info("消息发送成功,{}", o.toString()),
                throwable -> logger.info("消息发送失败,{}" + throwable.getMessage())
        );
        TbKafkaInMsg inMsg = new TbKafkaInMsg();
        inMsg.setServerId(System.currentTimeMillis());
        inMsg.setId("0");
        inMsg.setDocumentNo("12345");
        inMsg.setCreated(new Date());
        inMsg.setUpdated(new Date());
        TbKafkaInMsgVo tbKafkaInMsgVo = inMsgDao.selectByServerId(0L);
        if (tbKafkaInMsgVo != null){
            int insertFlag = inMsgDao.saveInMassage(inMsg);
            log.info("消息插入结果：{}",insertFlag == 1 ? "成功" : "失败");
        }
    }
}
