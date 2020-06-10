package com.Craffic.myshop.jersey.action;

import com.Craffic.myshop.jersey.service.impl.ThreadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/user")
public class ThreadAction {

    @Autowired
    ThreadServiceImpl threadService;

    @GET
    @Path("test")
    @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
    public int queryUserById() {
        return threadService.threadTest();
    }
}
