package com.Craffic.myshop.mvc.dao;

import com.Craffic.myshop.domain.model.TbUser;
import org.apache.ibatis.annotations.Param;

public interface TbUserDao {
    TbUser selectById(@Param("id") Long id);
}
