package com.xlily6x.compiler.structure;

import java.util.List;

/**
 * Created by xiaowenlong on 12/4/2018.
 */
public class LineNumberTable {

    private int lineNumberTableLenth;

    private List<NumberTable> numberTableList;

    public NumberTable newNumberTable(){
        return new NumberTable();
    }

    public class NumberTable{
        private int startPc;
        private int lineNumber;

        public int getStartPc() {
            return startPc;
        }

        public void setStartPc(int startPc) {
            this.startPc = startPc;
        }

        public int getLineNumber() {
            return lineNumber;
        }

        public void setLineNumber(int lineNumber) {
            this.lineNumber = lineNumber;
        }
    }

    public int getLineNumberTableLenth() {
        return lineNumberTableLenth;
    }

    public void setLineNumberTableLenth(int lineNumberTableLenth) {
        this.lineNumberTableLenth = lineNumberTableLenth;
    }

    public List<NumberTable> getNumberTableList() {
        return numberTableList;
    }

    public void setNumberTableList(List<NumberTable> numberTableList) {
        this.numberTableList = numberTableList;
    }
}
