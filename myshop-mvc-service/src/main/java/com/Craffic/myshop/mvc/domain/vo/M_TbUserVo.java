package com.Craffic.myshop.mvc.domain.vo;

import com.Craffic.myshop.mvc.domain.BaseEntity.M_BaseEntity;
import lombok.Data;
import java.io.Serializable;

@Data
public class M_TbUserVo extends M_BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userName;
    private String phone;
    private String email;
}