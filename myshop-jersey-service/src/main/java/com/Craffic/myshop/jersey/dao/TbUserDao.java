package com.Craffic.myshop.jersey.dao;

import com.Craffic.myshop.jersey.domain.model.TbUser;
import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface TbUserDao {
    TbUser selectById(@Param("id") String id);

    List<TbUserVo> getUserList();

    Integer countUser();

    int insertTbUser(TbUser user);
}
