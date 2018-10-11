package com.xlily6x.core.util;

/**
 * Created by xiaowenlong on 23/11/2017.
 */
public class BeanUtil {
    public static String getBean(String beanName){
        String b = beanName;
        String f = b.substring(0,1);
        f =f.toLowerCase();
        b = b.substring(1,b.length());
        b = f+b;
        return b;
    }
}
