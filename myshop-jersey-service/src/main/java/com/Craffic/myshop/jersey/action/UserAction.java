package com.Craffic.myshop.jersey.action;

import com.Craffic.myshop.jersey.service.UserService;
import com.Craffic.myshop.jersey.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.Craffic.myshop.domain.model.TbUser;

@Component
@Path("/user")
public class UserAction {

    @Autowired
    UserServiceImpl userService;

    //http://localhost:8080/services/v1/user/detail/1
    @GET
    @Path("detail/{userId}")
    @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
    public String queryUserById(@PathParam("userId") String userId) {

        TbUser userDetail = userService.getTbUserDetail(userId);
        if (userDetail == null){
            return "请输入有效的用户ID！";
        }
        return userDetail.toString();
    }

}
