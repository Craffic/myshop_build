package com.Craffic.myshop.jersey.domain.baseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import java.io.Serializable;
import java.util.Date;

@Data
public class BaseRequestEntity implements Serializable {
    private static final long serialVersionUID =  2569550702536598780L;
    /**
     *  请求对象 当前页数   每页大小
     */
    @DefaultValue("1")
    @QueryParam("pageNum")
    private Integer pageNum = 1;
    @DefaultValue("10")
    @QueryParam("pageCount")
    private Integer pageCount = 10;

    private Integer pageStartIndex(){
        return pageCount * (pageNum - 1);
    }

    /**
     * 每个对象共同属性：id， 创建日期， 更新日期
     */
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updated;
}