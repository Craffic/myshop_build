package com.Craffic.myshop.jersey.domain.common;

import com.Craffic.myshop.jersey.domain.baseEntity.BaseVo;
import com.Craffic.myshop.jersey.exception.ServerStatusCode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
@Getter
@Setter
public class ResponseBody<T> extends BaseVo {
    private static final long serialVersionID  = -4213992025937477652L;


    /**
     * 返回状态码
     */
    private String code;

    /**
     * 返回数据
     */
    private T obj;

    /**
     * 返回描述
     */
    private String message;

    /**
     * 返回是否成功
     */
    private String isSuccess;

    public  ResponseBody(){
        super();
    }

    public ResponseBody(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseBody(String code, String message, T obj) {
        this.code = code;
        this.obj = obj;
        this.message = message;
    }
    public ResponseBody(String code, String message, T obj, String isSuccess) {
        this.code = code;
        this.obj = obj;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public static <T> ResponseBody<T> success(T data){
        return new ResponseBody<>(ServerStatusCode.SUCCESSFUL_OK.getCode(), ServerStatusCode.SUCCESSFUL_OK.getMessage(), data, "true");
    }

    public static <T> ResponseBody<T> failure(String code, String message, T data){
        ResponseBody<T> responseBody = new ResponseBody<>();
        responseBody.setCode(code);
        responseBody.setMessage(message);
        responseBody.setObj(data);
        return responseBody;
    }

}
