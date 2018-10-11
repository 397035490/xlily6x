package com.xlily6x.common.vo;

import com.baomidou.mybatisplus.generator.config.rules.DbType;

/**
 * Created by xiaowenlong on 1/8/2017.
 */
public class GeneratorVO {

    private String userName;
    private String password;
    private String url;
    private String homePath;

    private String [] tableNames;

    @Override
    public String toString() {
        return "GeneratorVO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                ", homePath='" + homePath + '\'' +
                ", tableNames='" + tableNames + '\'' +
                '}';
    }

    public String[] getTableNames() {
        return tableNames;
    }

    public void setTableNames(String[] tableNames) {
        this.tableNames = tableNames;
    }

    public String getHomePath() {
        return homePath;
    }

    public void setHomePath(String homePath) {
        this.homePath = homePath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
