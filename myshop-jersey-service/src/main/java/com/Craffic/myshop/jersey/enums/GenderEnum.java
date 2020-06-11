package com.Craffic.myshop.jersey.enums;

import java.io.Serializable;

public enum GenderEnum implements BaseEnum<Integer>, Serializable {
    FEMALE(0, "女性"), MAIL(1, "男性");

    private int value;
    private String desc;

    private GenderEnum(int value, String desc){
        this.value = value;
        this.desc = desc;
    }


    @Override
    public Integer val() {
        return value;
    }

    @Override
    public String desc() {
        return desc;
    }

    private static GenderEnum parseByValue(Integer value){
        for (GenderEnum enumObject:values()){
            if (enumObject.val().equals(value)){
                return enumObject;
            }
        }
        return null;
    }
}
