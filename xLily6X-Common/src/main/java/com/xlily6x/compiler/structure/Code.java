package com.xlily6x.compiler.structure;

import java.util.List;

/**
 * Created by xiaowenlong on 12/4/2018.
 */
public class Code {
    private int maxStack;
    private int maxLocals;
    private long codeLength;
    private int [] code;
    private int execeptionTableLength;
    private List<ExeceptionTable> execeptionTableList;
    private int attributesCount;
    private List<AttributeInfo> attributeInfoList;

    public ExeceptionTable newExeceptionTable(){
        return new ExeceptionTable();
    }
    public class ExeceptionTable{
        private int startPc;
        private int endPc;
        private int handlerPc;
        private int catchType;

        public int getStartPc() {
            return startPc;
        }

        public void setStartPc(int startPc) {
            this.startPc = startPc;
        }

        public int getEndPc() {
            return endPc;
        }

        public void setEndPc(int endPc) {
            this.endPc = endPc;
        }

        public int getHandlerPc() {
            return handlerPc;
        }

        public void setHandlerPc(int handlerPc) {
            this.handlerPc = handlerPc;
        }

        public int getCatchType() {
            return catchType;
        }

        public void setCatchType(int catchType) {
            this.catchType = catchType;
        }
    }


    public int getMaxStack() {
        return maxStack;
    }

    public int getExeceptionTableLength() {
        return execeptionTableLength;
    }

    public void setExeceptionTableLength(int execeptionTableLength) {
        this.execeptionTableLength = execeptionTableLength;
    }

    public void setMaxStack(int maxStack) {
        this.maxStack = maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public void setMaxLocals(int maxLocals) {
        this.maxLocals = maxLocals;
    }

    public long getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(long codeLength) {
        this.codeLength = codeLength;
    }

    public int[] getCode() {
        return code;
    }

    public void setCode(int[] code) {
        this.code = code;
    }

    public List<ExeceptionTable> getExeceptionTableList() {
        return execeptionTableList;
    }

    public void setExeceptionTableList(List<ExeceptionTable> execeptionTableList) {
        this.execeptionTableList = execeptionTableList;
    }

    public int getAttributesCount() {
        return attributesCount;
    }

    public void setAttributesCount(int attributesCount) {
        this.attributesCount = attributesCount;
    }

    public List<AttributeInfo> getAttributeInfoList() {
        return attributeInfoList;
    }

    public void setAttributeInfoList(List<AttributeInfo> attributeInfoList) {
        this.attributeInfoList = attributeInfoList;
    }
}
