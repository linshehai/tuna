package com.hy.tuna.utils;

public class StringUtils {

    public static boolean hasText(Object str){
        return str!=null&&!"".equals(str);
    }

    public static boolean isEmpty(Object value) {
        return value==null||"".equals(value);
    }
}
