package com.xlily6x.core.filter;

import com.alibaba.fastjson.JSONObject;
import com.xlily6x.core.util.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

/**
 * Created by xiaowenlong on 3/8/2017.
 */
@WebFilter(filterName = "/accessFilter",urlPatterns = "/*")
public class AccessFilter implements Filter{

    Logger logger = LogManager.getLogger(AccessFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("AccessFilter init() ");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.debug("AccessFilter doFilter() ");
        boolean next = false;
        HttpServletResponse response1 = (HttpServletResponse)response;
        HttpServletRequest request1 =(HttpServletRequest)request;
        response1.setHeader("Access-Control-Allow-Origin", request1.getHeader("Origin"));
        response1.setHeader("Access-Control-Allow-Credentials", "true");
        Map<String,Cookie> cookieMap = CookieUtil.readCookieMap(request1);
        if(cookieMap!=null){
            if(cookieMap.get("lilyToken")!=null){
                String needFilter = cookieMap.get("lilyToken").getValue();
                if(StringUtils.isNotEmpty(needFilter)&&StringUtils.equals(needFilter,"true")){
                    next = true;
                }
            }
        }

        if(true){
//            response1.getOutputStream().setWriteListener(new WriteListener() {
//                Logger log = LogManager.getLogger(AccessFilter.class);
//                @Override
//                public void onWritePossible() throws IOException {
//                    log.debug("on write possible");
//                }
//
//                @Override
//                public void onError(Throwable t) {
//                    log.debug("on error");
//                }
//            });

            chain.doFilter(request,response1);

            doAfter(request,response1);
        }else{
            PrintStream out = new PrintStream(response1.getOutputStream());
            //response1.setContentType("text/html;charSet=utf-8");
            logger.debug(request1.getContentType());
            response1.setContentType(request1.getContentType());
            JSONObject obj = new JSONObject();
            obj.put("message","The cookie is null or needFilter is null");
            out.println(obj);
            out.close();
        }



    }


    public void doAfter(ServletRequest request, ServletResponse response){

        logger.debug("Lily doAfter");
    }

    @Override
    public void destroy() {
        logger.debug("AccessFilter destroy() ");
    }
}
