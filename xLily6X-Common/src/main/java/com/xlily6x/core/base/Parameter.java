package com.xlily6x.core.base;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.IdWorker;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaowenlong on 18/8/2017.
 */
public class Parameter implements Serializable {

    public Parameter(){}

    public Parameter(String service, String method) {
        this.service = service;
        this.method = method;
    }

    public Parameter(Object result) {
        this.result = result;
    }

    private String service;
    private String method;

    private Object [] param;
    private Serializable id;
    private Map<?,?> map;
    private List<?> list;
    private Object result;
    private Page<?> page;

    private final String no = "["+ IdWorker.getId()+"]";


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }


    public Serializable getId() {
        return id;
    }

    public void setId(Serializable id) {
        this.id = id;
    }

    public Map<?, ?> getMap() {
        return map;
    }

    public void setMap(Map<?, ?> map) {
        this.map = map;
    }

    public Object[] getParam() {
        return param;
    }

    public void setParam(Object[] param) {
        this.param = param;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Page<?> getPage() {
        return page;
    }

    public void setPage(Page<?> page) {
        this.page = page;
    }
    public String getNo() {
        return no;
    }
}
