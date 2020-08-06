package com.Craffic.myshop.jersey.action;

import com.Craffic.myshop.jersey.Utils.RSAUtils;
import com.Craffic.myshop.jersey.domain.common.ListVo;
import com.Craffic.myshop.jersey.domain.common.ResponseBody;
import com.Craffic.myshop.jersey.domain.model.TbUser;
import com.Craffic.myshop.jersey.domain.pojo.TbUserReq;
import com.Craffic.myshop.jersey.domain.vo.TbUserVo;
import com.Craffic.myshop.jersey.exception.ServerStatusCode;
import com.Craffic.myshop.jersey.service.UserService;
import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.rmi.ServerException;
import java.util.List;

@Component
@Path("/temp")
public class TempAction {

    private String publicKeyStr = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIA17erMNEa2i428pWDOpzGCtH87IGIMbHWGsQ3I4N2Xr4HrkEzq5rRjqORRW9sK6Jb25OaAD+Kl8E8HmoW0cJ8CAwEAAQ==";
    private String privateKeyStr =
            "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAgDXt6sw0RraLjbylYM6nMYK0fzsgYgxsdYaxDcjg3ZevgeuQTOrmtGOo5FFb2wrolvbk5oAP4qXwTweahbRwnwI" +
            "DAQABAkBlzm0FrPE11W4rNkj7/hy1K9y3yvOD5+mXyUR+daxZSCC3ruhKUNODILs/afa1gSVFUOSTZIkQ9OS1kYAPT2rBAiEAxdU/f1jfjEJS1rpwo68ObB+M+Gakn5clH41AYJcHxR0CIQCl6EGm904l937TB1aiX0QEzCcXQav+puTdnRvZjK9L6wIgZkAm9p" +
            "onyOXKzSNF7sQfDzFrekQLGFVVoa4vNP06zd0CIBZDFhwrbycVEMWMZImhMuVZ55ztSQEAsBE0tbn5cMRFAiEAgjbiyJWqkb8cBNLbxU8hK/Y/iOj5mySxd62vWOkzuKs=";

    @Autowired
    private UserService userService;

    //http://localhost:8080/services/v1/user/detail/1
    @POST
    @Path("rsa/encrypt")
    @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
    public ResponseBody<String> rsaEncrypt(String str) throws Exception {
        JSONObject obj = JSONObject.parseObject(str);
        String str1 = obj.get("str").toString();
        String encrypt = RSAUtils.encrypt(str1, publicKeyStr);
        return ResponseBody.success(encrypt);
    }

    @POST
    @Path("rsa/decrypt")
    @Produces({MediaType.TEXT_HTML,MediaType.APPLICATION_JSON})
    public ResponseBody<String> rsaDecrypt(String str) throws Exception {
        JSONObject obj = JSONObject.parseObject(str);
        String str1 = obj.get("str").toString();
        String encrypt = RSAUtils.decrypt(str1, privateKeyStr);
        return ResponseBody.success(encrypt);
    }
}
