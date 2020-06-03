package com.Craffic.myshop.jersey.domain.vo;

import com.Craffic.myshop.jersey.domain.baseEntity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

@Data
@EqualsAndHashCode(callSuper = false)
public class TbKafkaInMsgVo extends BaseEntity {
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
