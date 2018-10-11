package com.xlily6x.compiler.structure;


/**
 * Created by xiaowenlong on 12/4/2018.
 */
public class AttributeInfo {
    private int attributeNameIndex;
    private long attributeLength;
    private Object object;
    private Class<?> objectType;


    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public void setAttributeNameIndex(int attributeNameIndex) {
        this.attributeNameIndex = attributeNameIndex;
    }

    public long getAttributeLength() {
        return attributeLength;
    }

    public void setAttributeLength(long attributeLength) {
        this.attributeLength = attributeLength;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Class<?> getObjectType() {
        return objectType;
    }

    public void setObjectType(Class<?> objectType) {
        this.objectType = objectType;
    }
}
