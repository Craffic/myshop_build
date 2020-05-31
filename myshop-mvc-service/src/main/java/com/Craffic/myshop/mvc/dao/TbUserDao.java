package com.Craffic.myshop.mvc.dao;

import com.Craffic.myshop.mvc.domain.vo.M_TbUserVo;
import org.apache.ibatis.annotations.Param;

public interface TbUserDao {
    M_TbUserVo selectById(@Param("id") Long id);
}
