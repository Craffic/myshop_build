package com.Craffic.myshop.jersey.action;

import com.Craffic.myshop.jersey.Utils.IdcardAreaGenerator;
import com.Craffic.myshop.jersey.domain.common.ListVo;
import com.Craffic.myshop.jersey.domain.common.ResponseBody;
import com.Craffic.myshop.jersey.domain.model.TbUser;
import com.Craffic.myshop.jersey.domain.pojo.TbUserReq;
import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import com.Craffic.myshop.jersey.exception.ServerStatusCode;
import com.Craffic.myshop.jersey.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/user")
public class UserAction {

    @Autowired
    private UserService userService;

    //http://localhost:8080/services/v1/user/detail/1
    @GET
    @Path("detail/{userId}")
    @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
    public ResponseBody<TbUserVo> queryUserById(@PathParam("userId") String userId) {

        TbUser tbUserDetail = userService.getTbUserDetail(userId);
        TbUserVo userVo = new TbUserVo();
        BeanUtils.copyProperties(tbUserDetail, userVo);
        System.out.println(ServerStatusCode.SUCCESSFUL_OK.getCode());
        return ResponseBody.success(userVo);
    }

    @GET
    @Path("list")
    @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
    public ResponseBody<ListVo<TbUserVo>> getUserList() {

        List<TbUserVo> userList = userService.getUserList();
        ListVo listVo = new ListVo(userList, userList.size());
        return ResponseBody.success(listVo);
    }

    @POST
    @Path("list/condition")
    @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
    public ResponseBody<ListVo<TbUserVo>> findUserByCondition (@Valid @NotNull TbUserReq userReq){

        List<TbUserVo> userList = userService.findUserByCondition(userReq);
        ListVo listVo = new ListVo(userList, userList.size());
        return ResponseBody.success(listVo);
    }

}
