package com.xlily6x.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaowenlong on 3/8/2017.
 */
public class CookieUtil {

    /**
     * 将cookie封装到Map里面
     * @param request
     * @return
     */
    public static Map<String,Cookie> readCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if(null!=cookies){
            for(Cookie cookie : cookies){
                cookieMap.put(cookie.getName(), cookie);
            }
        }else{
            return null;
        }
        return cookieMap;
    }
}
