package com.xlily6x.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常帮助类
 * Created by xiaowenlong on 18/8/2017.
 */
public class ExceptionUtil {

    /**
     * 打印日志
     * @param e
     * @return
     */
    public static String getStackTraceAsString(Throwable e){
        if(e!=null){
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
