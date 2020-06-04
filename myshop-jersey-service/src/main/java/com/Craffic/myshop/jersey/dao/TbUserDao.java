package com.Craffic.myshop.jersey.dao;

import com.Craffic.myshop.jersey.domain.model.TbUser;
import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface TbUserDao {
    TbUser selectById(@Param("id") Long id);

    List<TbUserVo> getUserList();
    int insertTbUser(TbUser user);
}
