package com.Craffic.myshop.jersey.Utils;

import java.util.UUID;

public class IdGenerator {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

}
