package com.xlily6x.compiler.structure;

import java.util.List;

/**
 * Created by xiaowenlong on 12/4/2018.
 */
public class RuntimeVisibleAnnotations {
    private int numAnnotations;
    private List<Annotation> annotations;

    public int getNumAnnotations() {
        return numAnnotations;
    }

    public void setNumAnnotations(int numAnnotations) {
        this.numAnnotations = numAnnotations;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<Annotation> annotations) {
        this.annotations = annotations;
    }
}
