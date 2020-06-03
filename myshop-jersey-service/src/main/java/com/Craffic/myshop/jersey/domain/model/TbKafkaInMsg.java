package com.Craffic.myshop.jersey.domain.model;

import com.Craffic.myshop.jersey.domain.baseEntity.BaseEntity;
import lombok.Data;

@Data
public class TbKafkaInMsg extends BaseEntity {
    /**
     * 档案号
     */
    private String documentNo;
    /**
     * 处理标记
     */
    private String handleFlag;
    /**
     * 服务标志
     */
    private Long serverId;
}
