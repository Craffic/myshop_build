package com.Craffic.myshop.jersey.dao;

import com.Craffic.myshop.jersey.domain.model.IdCardArea;
import com.Craffic.myshop.jersey.domain.model.TbUser;
import com.Craffic.myshop.jersey.domain.vo.IdcardAreaVo;
import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IdcardAreaDao {

    List<IdcardAreaVo> getAreaList();

    int insertArea(IdCardArea area);
}
