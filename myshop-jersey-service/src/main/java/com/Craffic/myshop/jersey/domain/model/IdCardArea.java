package com.Craffic.myshop.jersey.domain.model;

import com.Craffic.myshop.jersey.Utils.RegexpUtils;
import com.Craffic.myshop.jersey.domain.baseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(callSuper = false)
public class IdCardArea extends BaseEntity {
    /**
     * 地区编码
     */
    @Length(min = 6, max = 6, message = "地区编码必须为6位")
    private int areaCode;
    /**
     * 地区名称
     */
    @NotBlank
    private String areaName;
}
