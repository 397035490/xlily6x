package com.xlily6x.compiler.structure;

import java.util.List;

/**
 * Created by xiaowenlong on 13/4/2018.
 */
public class FMInfo {

    private int accessFlags;
    private int nameIndex;
    private int descriptorIndex;
    private int ateributesCount;
    private List<AttributeInfo> attributeInfos;

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    public int getAteributesCount() {
        return ateributesCount;
    }

    public void setAteributesCount(int ateributesCount) {
        this.ateributesCount = ateributesCount;
    }

    public List<AttributeInfo> getAttributeInfos() {
        return attributeInfos;
    }

    public void setAttributeInfos(List<AttributeInfo> attributeInfos) {
        this.attributeInfos = attributeInfos;
    }
}
