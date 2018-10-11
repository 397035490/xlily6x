package com.xlily6x.compiler.structure;

import java.util.List;

/**
 * Created by xiaowenlong on 13/4/2018.
 */
public class Annotation {

    private int typeIndex;
    private int numElementValuePairs;
    private List<ElementValuePair> elementValuePairs;

    public int getTypeIndex() {
        return typeIndex;
    }

    public void setTypeIndex(int typeIndex) {
        this.typeIndex = typeIndex;
    }

    public int getNumElementValuePairs() {
        return numElementValuePairs;
    }

    public void setNumElementValuePairs(int numElementValuePairs) {
        this.numElementValuePairs = numElementValuePairs;
    }

    public List<ElementValuePair> getElementValuePairs() {
        return elementValuePairs;
    }

    public void setElementValuePairs(List<ElementValuePair> elementValuePairs) {
        this.elementValuePairs = elementValuePairs;
    }
}
