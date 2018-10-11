package com.xlily6x.compiler.structure;

import java.util.List;

/**
 * Created by xiaowenlong on 13/4/2018.
 */
public class ElementValue {

    private int tag;
    private int constValueIndex;
    private int typeNameIndex;
    private int constNameIndex;
    private int classInfoIndex;

    private int numValues;
    private List<ElementValue> values;

    private Annotation annotation;


    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getConstValueIndex() {
        return constValueIndex;
    }

    public void setConstValueIndex(int constValueIndex) {
        this.constValueIndex = constValueIndex;
    }

    public int getTypeNameIndex() {
        return typeNameIndex;
    }

    public void setTypeNameIndex(int typeNameIndex) {
        this.typeNameIndex = typeNameIndex;
    }

    public int getConstNameIndex() {
        return constNameIndex;
    }

    public void setConstNameIndex(int constNameIndex) {
        this.constNameIndex = constNameIndex;
    }

    public int getClassInfoIndex() {
        return classInfoIndex;
    }

    public void setClassInfoIndex(int classInfoIndex) {
        this.classInfoIndex = classInfoIndex;
    }

    public int getNumValues() {
        return numValues;
    }

    public void setNumValues(int numValues) {
        this.numValues = numValues;
    }

    public List<ElementValue> getValues() {
        return values;
    }

    public void setValues(List<ElementValue> values) {
        this.values = values;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }
}
