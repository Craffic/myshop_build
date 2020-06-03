package com.Craffic.myshop.jersey.domain.pojo;

import com.Craffic.myshop.jersey.domain.baseEntity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

@Data
@EqualsAndHashCode(callSuper = false)
public class TbKafkaInMsgReq extends BaseEntity {
    /**
     * 档案号
     */
    @Length(max = 10, message = "档案号不能大于10位")
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
