package com.Craffic.myshop.jersey.dao;

import com.Craffic.myshop.jersey.domain.model.TbKafkaInMsg;
import com.Craffic.myshop.jersey.domain.vo.TbKafkaInMsgVo;
import org.apache.ibatis.annotations.Param;

public interface TbKafkaInMsgDao {
    TbKafkaInMsgVo selectByServerId(@Param("serverId") Long serverId);

    int saveInMassage(TbKafkaInMsg inMsg);
}
