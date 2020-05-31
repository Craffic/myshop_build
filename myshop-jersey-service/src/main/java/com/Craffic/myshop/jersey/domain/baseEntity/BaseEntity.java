package com.Craffic.myshop.jersey.domain.baseEntity;

import lombok.Data;
import java.util.Date;
@Data
public class BaseEntity {
    /**
     * 每个对象共同属性：id， 创建日期， 更新日期
     */
    private Long id;
    private Date created;
    private Date updated;
}