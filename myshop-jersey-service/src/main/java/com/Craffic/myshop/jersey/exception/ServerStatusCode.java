package com.Craffic.myshop.jersey.exception;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EqualsAndHashCode(of = "code", doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public abstract class ServerStatusCode implements ServerErrorCode{

    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
    private Object[] arguments;

    public static final ServerStatusCode
        SUCCESSFUL_OK = new InternalServiceStatusCode("200000", "处理成功"),
        CLIENT_BAD_REQUEST_ERROR = new InternalServiceStatusCode("4XX001", "非法请求"),
        REDIS_QUERY_ERROR = new InternalServiceStatusCode("500001", "插入redis数据失败"),
        REDIS_INSERT_ERROR = new InternalServiceStatusCode("500002", "插入redis数据失败");

    protected ServerStatusCode(String code, String message, Object... arguments){
        if (code == null && message == null){
            // 解决反序列化的问题
        } else if (code == null || code.trim().length() != 6 ||
                !(code.startsWith("2") || code.startsWith("4") || code.startsWith("5")) ||
                "0".equals(code.substring(3, 4))) {
            throw new IllegalArgumentException("应用状态码不合法， 必须是6位长度，2开头的位成功，4开头的位客户端错误， 5开头的是服务端错误，第4个字符应该是非0");
        }
        this.code = code == null ? null:code.trim();
        this.message = message;
        this.arguments = arguments;
    }

    public ServerStatusCode(boolean internal, String code, String message, Object[] arguments) {
        this.code = code == null ? null:code.trim();
        this.message = message;
        this.arguments = arguments;
    }

    private ServerStatusCode(){
        this.code = null;
        this.message = null;
        this.arguments = null;
    }

    public abstract ServerStatusCode copy(String customMessage, Object... arguments);

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        if (message == null) return null;

        StringBuffer result = new StringBuffer();
        Matcher matcher = Pattern.compile("[{]([^}]*?)[}]").matcher(Matcher.quoteReplacement(message));

        int count = 0;

        while (matcher.find()){
            String key = matcher.group(1);
            Object value = null;

            // 可以扩展-->当key不为null时，从MessageSource获取消息，但现在阶段只支持从arguments获取
            if (key == null || "".equals(key.trim())){
                value = arguments[count];
                count++;
            }
            matcher.appendReplacement(result, value == null ? matcher.group() : value.toString());
        }
        matcher.appendTail(result);
        return result.toString();


    }

    @Override
    public boolean isClientRrror() {
        return false;
    }

    @Override
    public boolean isServerError() {
        return false;
    }

    @Override
    public int getHttpStatus() {
        return 0;
    }



    public static class InternalServiceStatusCode extends ServerStatusCode{
        private static final long serialVersionUID = 1L;

        private InternalServiceStatusCode(String code, String message, Object... arguments){
            super(true, code ,message, arguments);
        }

        public InternalServiceStatusCode copy(String message, Object... arguments){
            return new InternalServiceStatusCode(this.getCode() ,message, arguments);
        }

        public InternalServiceStatusCode copy(String code, String message, Object... arguments){
            return new InternalServiceStatusCode(this.getCode(), message, arguments);
        }

    }
}
