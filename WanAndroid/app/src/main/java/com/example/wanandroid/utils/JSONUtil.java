package com.example.wanandroid.utils;

public class JSONUtil {

    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0 || s.equals("[]") || s.equals("{}");
    }

}
