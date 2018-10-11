package com.xlily6x.core.base;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by xiaowenlong on 12/12/2017.
 */
public abstract class SuperController<T extends BaseProvider> {

    protected final Logger logger = LogManager.getLogger();

    @Autowired
    protected T provider;

    protected abstract String getService();

    protected <E>String query(E param){
        logger.info("Super query  ");
        logger.info(param.getClass());
        Parameter parameter = new Parameter(getService(),"selectList");
        EntityWrapper<E> wrapper = new EntityWrapper<E>();
        wrapper.setEntity(param);
        parameter.setParam(new Object[]{wrapper});
        return execute(parameter);
    }

    protected String queryOne(Serializable id){
        Parameter parameter = new Parameter(getService(),"selectById");
        parameter.setId(id);
        return execute(parameter);
    }

    protected <E>String insert(E param){
        logger.info("Super insert  ");
        logger.info(param.getClass());
        Parameter parameter = new Parameter(getService(),"insert");
        parameter.setParam(new Object[]{param});
        return execute(parameter);
    }

    protected <E>String queryLilyPage(Pagination pagination,E param){
        Page<E> page = new Page<>(pagination.getCurrent(),pagination.getSize());
        Parameter parameter = new Parameter(getService(),"selectPage");
        EntityWrapper<E> wrapper = new EntityWrapper<E>();
        wrapper.setEntity(param);
        parameter.setParam(new Object[]{page,wrapper});
        return execute(parameter);
    }

    protected <E>String updateById(E param){
        Parameter parameter = new Parameter(getService(),"updateById");
        parameter.setParam(new Object[]{param});
        return execute(parameter);
    }

    protected <E>String selectCount(E param){
        Parameter parameter = new Parameter(getService(),"selectCount");
        EntityWrapper<E> wrapper = new EntityWrapper<E>();
        wrapper.setEntity(param);
        parameter.setParam(new Object[]{wrapper});
        return execute(parameter);
    }


    protected String execute(Parameter parameter){
        Object o = provider.execute(parameter);
        String res = JSON.toJSONString(o);
        logger.info("execute  : {}.{}(...)",parameter.getService(),parameter.getMethod());
        logger.info("response : {}",res);
        return res;
    }
}
