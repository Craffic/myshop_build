package com.Craffic.myshop.jersey.domain.common;

import com.Craffic.myshop.jersey.domain.baseEntity.BaseVo;
import lombok.Data;

import java.util.List;

@Data
public class ListVo<T> extends BaseVo {

    private static final long serialVersionUID = 2569550702536598780L;

    private List<T> list;

    private Integer totalNum;

    public ListVo(List<T> list, Integer totalNum){
        this.list = list;
        this.totalNum = totalNum;
    }
}
