package com.Craffic.myshop.jersey.dao;

import com.Craffic.myshop.jersey.domain.model.TbUser;
import com.Craffic.myshop.jersey.domain.pojo.TbUserReq;
import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface TbUserDao {
    TbUser selectById(@Param("id") String id);

    List<TbUserVo> getUserList(RowBounds rowBounds);

    Integer countUser();

    int insertTbUser(TbUser user);

    List<TbUserVo> findUsersByCondition(TbUserReq req, RowBounds rowBounds);

    Integer countUsersByCondition(TbUserReq req, RowBounds rowBounds);
}
