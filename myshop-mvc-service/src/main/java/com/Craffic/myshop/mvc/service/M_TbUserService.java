package com.Craffic.myshop.mvc.service;

import com.Craffic.myshop.mvc.domain.model.M_TbUser;
import com.Craffic.myshop.mvc.domain.vo.M_TbUserVo;

public interface M_TbUserService {
    M_TbUserVo getTbUserDetail(String userId);
}
