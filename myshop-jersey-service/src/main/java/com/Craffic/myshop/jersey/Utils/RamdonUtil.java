package com.Craffic.myshop.jersey.Utils;

import java.util.Random;

public class RamdonUtil {

    public static void main(String []args) {
        Random random = new Random();
        for(int i=0;i<10;i++)
            System.out.print((int)(Math.random()*200)+" ");
    }
}
