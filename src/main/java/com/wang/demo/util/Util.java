package com.wang.demo.util;

import java.util.Random;

public class Util {

    final static String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final static int RANDOM_STRING_LENGTH = 20;

    public static String generateRandomString(){
        StringBuffer randomString = new StringBuffer();
        Random random = new Random();
        for(int i = 0; i < RANDOM_STRING_LENGTH; i++){
            randomString.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));
        }
        return randomString.toString();
    }
}
