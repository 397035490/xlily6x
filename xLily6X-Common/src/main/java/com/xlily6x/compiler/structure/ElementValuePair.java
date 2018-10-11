package com.xlily6x.compiler.structure;

/**
 * Created by xiaowenlong on 13/4/2018.
 */
public class ElementValuePair {

    private int elementNameIndex;
    private ElementValue value;

    public int getElementNameIndex() {
        return elementNameIndex;
    }

    public void setElementNameIndex(int elementNameIndex) {
        this.elementNameIndex = elementNameIndex;
    }

    public ElementValue getValue() {
        return value;
    }

    public void setValue(ElementValue value) {
        this.value = value;
    }
}
