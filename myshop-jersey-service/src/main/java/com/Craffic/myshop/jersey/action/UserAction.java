package com.Craffic.myshop.jersey.action;

import com.Craffic.myshop.jersey.model.User;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/user")
public class UserAction {

    //http://localhost:8080/services/v1/user/xml?userName=lisi&age=23
    @GET
    @Path("/xml")
    @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
    public String queryUserName(@QueryParam("userName") String userName,
                                @QueryParam("age") int age) {

        return userName+"   "+age;
    }

    @POST
    @Path("/beanParam")
    @Produces({MediaType.APPLICATION_JSON+"; charset=UTF-8"})
    public String testBeanParam(@BeanParam User user){

        return "{\"name\":\"" + user.toString() + "\"}";
    }

}
