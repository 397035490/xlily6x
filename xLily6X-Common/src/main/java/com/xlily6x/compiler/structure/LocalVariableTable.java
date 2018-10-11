package com.xlily6x.compiler.structure;

import java.util.List;

/**
 * Created by xiaowenlong on 12/4/2018.
 */
public class LocalVariableTable {
    private int localVariableTableLength;
    private List<VariableTable> variableTableList;

    public VariableTable newVariableTable(){
        return new VariableTable();
    }

    public class VariableTable{
        private int startPc;
        private int length;
        private int nameIndex;
        private int descriptorIndex;
        private int index;

        public int getStartPc() {
            return startPc;
        }

        public void setStartPc(int startPc) {
            this.startPc = startPc;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
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

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    public int getLocalVariableTableLength() {
        return localVariableTableLength;
    }

    public void setLocalVariableTableLength(int localVariableTableLength) {
        this.localVariableTableLength = localVariableTableLength;
    }

    public List<VariableTable> getVariableTableList() {
        return variableTableList;
    }

    public void setVariableTableList(List<VariableTable> variableTableList) {
        this.variableTableList = variableTableList;
    }
}
