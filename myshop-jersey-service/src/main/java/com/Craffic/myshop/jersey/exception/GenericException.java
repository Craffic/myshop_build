package com.Craffic.myshop.jersey.exception;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GenericException extends RuntimeException{

    private static final long serialVersionID  = -4213992025937477652L;

    private final Object genericErrorCode;

    private Map<String, Object> context = new ConcurrentHashMap<>();

    public GenericException(String message) {
        super(message);
        this.genericErrorCode = null;
    }

    public GenericException(String message, Throwable e) {
        super(message, e);
        this.genericErrorCode = null;
    }

    public GenericException(Object errorCode) {
        super("ErrorCode=" + String.valueOf(errorCode));
        this.genericErrorCode = errorCode;
    }

    public GenericException(Object errorCode, Throwable e) {
        super("ErrorCode=" + String.valueOf(errorCode), e);
        this.genericErrorCode = errorCode;
    }

    public GenericException(Object errorCode, String message) {
        super("ErrorCode=" + String.valueOf(errorCode) + ")");
        this.genericErrorCode = errorCode;
    }

    public GenericException(Object errorCode, String message, Throwable e) {
        super("ErrorCode=" + String.valueOf(errorCode) + ")", e);
        this.genericErrorCode = errorCode;
    }

    public Object getErrorCode() {
        return this.genericErrorCode;
    }

    public void addContextValue(String key, Object value){
        if (value == null){
            this.context.remove(key);
        } else {
            this.context.put(key, value);
        }
    }

    public <T> T getContextValue (String key){
        T t = (T) context.get(key);
        return t;
    }

    public boolean isClientError(){
        if (getErrorCode() instanceof  String) {
            return ((String) getErrorCode()).startsWith("4");
        }
//        else if (true){}
        return false;
    }

    public boolean isServerCodeError(){
        return !isClientError();
    }
}
