package com.Craffic.myshop.jersey.domain.vo;

import com.Craffic.myshop.jersey.domain.baseEntity.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class IdcardAreaVo extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int areaCode;
    private String areaName;
}