package com.Craffic.myshop.jersey.exception;

import java.io.Serializable;

public interface ServerErrorCode extends Serializable {

    String getCode();

    String getMessage();

    boolean isClientRrror();

    boolean isServerError();

    int getHttpStatus();

}
