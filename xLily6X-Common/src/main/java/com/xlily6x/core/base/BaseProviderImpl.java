package com.xlily6x.core.base;

import com.alibaba.fastjson.JSON;
import com.xlily6x.core.util.BeanUtil;
import com.xlily6x.core.util.ExceptionUtil;
import com.xlily6x.core.util.InstanceUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaowenlong on 18/8/2017.
 */
public class BaseProviderImpl implements BaseProvider ,ApplicationContextAware{
    protected static Logger logger = LogManager.getLogger();
    private ApplicationContext applicationContext;

    @Override
    public Parameter execute(Parameter parameter) {
        String no = parameter.getNo();
        logger.info("{} request：{}", no, JSON.toJSONString(parameter));
        Object service = null;
        try {
            service =  applicationContext.getBean(parameter.getService());
        } catch (BeansException e) {
            service = applicationContext.getBean(BeanUtil.getBean(parameter.getService()));
        }
        try{
            Serializable id = parameter.getId();
            List<?> list = parameter.getList();
            Map<?, ?> map = parameter.getMap();
            String method = parameter.getMethod();
            Object[] param = parameter.getParam();
            Object result = null;

            if(param!=null){
                result = InstanceUtil.invokeMethod(service,method,param);
            }else if(id!=null){
                result = InstanceUtil.invokeMethod(service,method,id);
            }else{
                result = InstanceUtil.invokeMethod(service,method);
            }

            if(result!=null){
                Parameter response = new Parameter(result);
                logger.info("{} response：{}", no, JSON.toJSONString(response));
                return response;
            }
            logger.info("{} response empty.", no);
            return null;

        }catch (Exception e){
            String msg = ExceptionUtil.getStackTraceAsString(e);
            logger.error(no+" "+msg,e);
            throw e;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
