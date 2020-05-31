package com.Craffic.myshop.jersey.action;

import org.springframework.stereotype.Component;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/user")
public class UserAction {

    //http://localhost:8080/services/v1/user/detail/1
    @GET
    @Path("detail/{userId}")
    @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
    public String queryUserById(@PathParam("userId") String userId) {
        return "query User Detail";
    }

}
