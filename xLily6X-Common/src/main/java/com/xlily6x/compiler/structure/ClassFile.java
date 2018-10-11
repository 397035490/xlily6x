package com.xlily6x.compiler.structure;

import java.util.List;

/**
 * Created by xiaowenlong on 9/4/2018.
 */
public class ClassFile {

    private long magic;
    private int minorVersion;
    private int majorVersion;
    private int constantPoolCount;
    private int accessFlags;
    private int thisClass;
    private int superClass;
    private int interfacesCount;
    private int fieldsCount;
    private int methodsCount;
    private int attrbutesCount;
    private  int [] interfaces;



    private CPInfo cpInfo;

    private List<FMInfo> fieldInfos;
    private List<FMInfo> methodInfos;
    private List<AttributeInfo> attributeInfos;

    public List<FMInfo> getFieldInfos() {
        return fieldInfos;
    }

    public void setFieldInfos(List<FMInfo> fieldInfos) {
        this.fieldInfos = fieldInfos;
    }

    public List<FMInfo> getMethodInfos() {
        return methodInfos;
    }

    public void setMethodInfos(List<FMInfo> methodInfos) {
        this.methodInfos = methodInfos;
    }

    public List<AttributeInfo> getAttributeInfos() {
        return attributeInfos;
    }

    public void setAttributeInfos(List<AttributeInfo> attributeInfos) {
        this.attributeInfos = attributeInfos;
    }

    public int[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(int[] interfaces) {
        this.interfaces = interfaces;
    }

    public CPInfo getCpInfo() {
        return cpInfo;
    }

    public void setCpInfo(CPInfo cpInfo) {
        this.cpInfo = cpInfo;
    }

    @Override
    public String toString() {
        return "ClassFile{" +
                "magic=" + magic +
                ", minorVersion=" + minorVersion +
                ", majorVersion=" + majorVersion +
                ", constantPoolCount=" + constantPoolCount +
                ", accessFlags=" + accessFlags +
                ", thisClass=" + thisClass +
                ", superClass=" + superClass +
                ", interfacesCount=" + interfacesCount +
                ", fieldsCount=" + fieldsCount +
                ", methodsCount=" + methodsCount +
                ", attrbutesCount=" + attrbutesCount +
                '}';
    }

    public long getMagic() {
        return magic;
    }

    public void setMagic(long magic) {
        this.magic = magic;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getConstantPoolCount() {
        return constantPoolCount;
    }

    public void setConstantPoolCount(int constantPoolCount) {
        this.constantPoolCount = constantPoolCount;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public int getThisClass() {
        return thisClass;
    }

    public void setThisClass(int thisClass) {
        this.thisClass = thisClass;
    }

    public int getSuperClass() {
        return superClass;
    }

    public void setSuperClass(int superClass) {
        this.superClass = superClass;
    }

    public int getInterfacesCount() {
        return interfacesCount;
    }

    public void setInterfacesCount(int interfacesCount) {
        this.interfacesCount = interfacesCount;
    }

    public int getFieldsCount() {
        return fieldsCount;
    }

    public void setFieldsCount(int fieldsCount) {
        this.fieldsCount = fieldsCount;
    }

    public int getMethodsCount() {
        return methodsCount;
    }

    public void setMethodsCount(int methodsCount) {
        this.methodsCount = methodsCount;
    }

    public int getAttrbutesCount() {
        return attrbutesCount;
    }

    public void setAttrbutesCount(int attrbutesCount) {
        this.attrbutesCount = attrbutesCount;
    }
}
