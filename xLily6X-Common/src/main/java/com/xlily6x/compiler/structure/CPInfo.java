package com.xlily6x.compiler.structure;

import java.util.Map;

/**
 * Created by xiaowenlong on 9/4/2018.
 */
public class CPInfo{

    Map<Integer,String> utf8Infos ;
    Map<Integer,Object> methoderfs;
    Map<Integer,Object> fieldrefs ;
    Map<Integer,Object> nameAndTYpes ;
    Map<Integer,Integer> classs;

    public Map<Integer, String> getUtf8Infos() {
        return utf8Infos;
    }

    public void setUtf8Infos(Map<Integer, String> utf8Infos) {
        this.utf8Infos = utf8Infos;
    }

    public Map<Integer, Object> getMethoderfs() {
        return methoderfs;
    }

    public void setMethoderfs(Map<Integer, Object> methoderfs) {
        this.methoderfs = methoderfs;
    }

    public Map<Integer, Object> getFieldrefs() {
        return fieldrefs;
    }

    public void setFieldrefs(Map<Integer, Object> fieldrefs) {
        this.fieldrefs = fieldrefs;
    }

    public Map<Integer, Object> getNameAndTYpes() {
        return nameAndTYpes;
    }

    public void setNameAndTYpes(Map<Integer, Object> nameAndTYpes) {
        this.nameAndTYpes = nameAndTYpes;
    }

    public Map<Integer, Integer> getClasss() {
        return classs;
    }

    public void setClasss(Map<Integer, Integer> classs) {
        this.classs = classs;
    }
}
