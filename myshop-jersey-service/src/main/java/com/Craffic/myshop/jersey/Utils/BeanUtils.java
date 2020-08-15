package com.Craffic.myshop.jersey.Utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class BeanUtils {
    private BeanUtils(){}

    /**
     * 对象转字节数组
     */
    public static byte[] objectToBytes(Object obj){

        byte[] bytes = null;
        ByteArrayOutputStream bo = null;
        ObjectOutputStream oo = null;

        try {
            bo = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bo!=null){
                    bo.close();
                }
                if(oo!=null){
                    oo.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }


    /**
     * 字节数组转对象
     * @param bytes
     * @return
     */
    public static Object BytesToObject(byte[] bytes){
        Object obj = null;
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        try {
            bi =new ByteArrayInputStream(bytes);
            oi =new ObjectInputStream(bi);
            obj = oi.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(bi!=null){
                    bi.close();
                }
                if(oi!=null){
                    oi.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return obj;
    }

    public static JSONObject strToJsonObj(String str){
        JSONObject object = JSONObject.parseObject(str);
        return object;
    }
}
