package com.xlily6x.compiler.read;

import com.xlily6x.compiler.structure.*;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaowenlong on 13/4/2018.
 */
public class Executor {

    private String CP_METHODREF = "CONSTANT_Methodref";
    private String CP_FIELDREF = "CONSTANT_Fieldref";
    private String CP_CLASS = "CONSTANT_Class";
    private String CP_UTF8 = "CONSTANT_Utf8";
    private String CP_NAMEANDTYPE = "CP_NameAndType";

    private String ATTR_TYPE_CODE = "code";
    private String ATTR_TYPE_LINENUMBERTABLE = "LineNumberTable";
    private String ATTR_TYPE_LOCALVARIABLETABLE = "LocalVariableTable";
    private String ATTR_TYPE_RUNTIMEVISIBLEANNOTATIONS = "RuntimeVisibleAnnotations";
    private String ATTR_TYPE_SOURCEFILE = "SourceFile";

    private CPInfo cpInfo;
    private ClassFile classFile;
    private Logger logger = LogManager.getLogger();

    public static ClassFile compiler(String filePath) throws Exception{
        Executor executor = new Executor();
        ClassFile cf = executor.execute(filePath);
        return  cf;
    }
    private Executor(){}

    private ClassFile execute(String classFilePath) throws Exception{
        File file = new File(classFilePath);
        FileInputStream in = new FileInputStream(file);
        classFile = new ClassFile();
        classFile.setMagic(U4.read(in));
        classFile.setMajorVersion(U2.read(in));
        classFile.setMinorVersion(U2.read(in));
        classFile.setConstantPoolCount(U2.read(in));

        cpInfo = readConstantPool(classFile.getConstantPoolCount(),in);
        classFile.setCpInfo(cpInfo);
        classFile.setAccessFlags(U2.read(in));
        classFile.setThisClass(U2.read(in));
        classFile.setSuperClass(U2.read(in));
        classFile.setInterfacesCount(U2.read(in));

        int [] interfaces = new int[classFile.getInterfacesCount()];
        for(int ij=0;ij<classFile.getInterfacesCount();ij++){
            interfaces[ij] = U2.read(in);
        }
        classFile.setInterfaces(interfaces);

        int fieldCount = U2.read(in);
        classFile.setFieldsCount(fieldCount);
        List<FMInfo> fieldInfos = readFMInfos(fieldCount,in);
        classFile.setFieldInfos(fieldInfos);

        int methodCount = U2.read(in);
        classFile.setMethodsCount(methodCount);
        List<FMInfo> methodInfos = readFMInfos(methodCount,in);
        classFile.setMethodInfos(methodInfos);

        int attrbutesCount = U2.read(in);
        classFile.setAttrbutesCount(attrbutesCount);
        List<AttributeInfo> attributeInfos = readAttributes(attrbutesCount,in);
        classFile.setAttributeInfos(attributeInfos);
        System.out.println(classFile);
        in.close();

        return classFile;
    }

    private AttributeInfo readAttribute(InputStream in){
        AttributeInfo attributeInfo = new AttributeInfo();
        int attributeNameIndex = U2.read(in);
        long atteributeLength = U4.read(in);
        Map<Integer,String> cputf8s = cpInfo.getUtf8Infos();
        String attrType = cputf8s.get(attributeNameIndex);
        attributeInfo.setAttributeNameIndex(attributeNameIndex);
        attributeInfo.setAttributeLength(atteributeLength);
        logger.info("read attr --  attrNameIndex:{} , length:{} , type:{} ",attributeNameIndex,atteributeLength,attrType);
        if(StringUtils.equalsIgnoreCase(attrType,ATTR_TYPE_CODE)){
            //处理 code 类型属性
            Code code = readAttrCode(in);
            attributeInfo.setObject(code);
            attributeInfo.setObjectType(Code.class);
        }else if(StringUtils.equalsIgnoreCase(attrType,ATTR_TYPE_LINENUMBERTABLE)){
            //处理 LineNumberTable 类型属性
            LineNumberTable lineNumberTable = readAttrLineNumberTable(in);
            attributeInfo.setObject(lineNumberTable);
            attributeInfo.setObjectType(LineNumberTable.class);
        }else if(StringUtils.equalsIgnoreCase(attrType,ATTR_TYPE_LOCALVARIABLETABLE)){
            //处理 LocalVariableTable 类型属性
            LocalVariableTable localVariableTable = readAttrLocalVariableTable(in);
            attributeInfo.setObject(localVariableTable);
            attributeInfo.setObjectType(LocalVariableTable.class);
        }else if(StringUtils.equalsIgnoreCase(attrType,ATTR_TYPE_RUNTIMEVISIBLEANNOTATIONS)){
            //处理 RuntimeVisibleAnnotations 类型属性
            RuntimeVisibleAnnotations runtimeVisibleAnnotations = readAttrRuntimeVisibleAnnotations(in);
            attributeInfo.setObject(runtimeVisibleAnnotations);
            attributeInfo.setObjectType(RuntimeVisibleAnnotations.class);
        }else if(StringUtils.equalsIgnoreCase(attrType,ATTR_TYPE_SOURCEFILE)){
            //处理 SourceFile 类型属性
            int sourceFileIndex = readAttrSourceFile(in);
            attributeInfo.setObject(sourceFileIndex);
            attributeInfo.setObjectType(Integer.class);
        }
        return attributeInfo;
    }

    private List<AttributeInfo> readAttributes(int count,InputStream in){
        List<AttributeInfo> attributeInfoList = new ArrayList<>();
        logger.info("*********  Read Attributes *************** Count : {}",count);
        for(int k=0;k<count;k++){
            logger.info("Read Attributes  No.{}",k);
            AttributeInfo attributeInfo = readAttribute(in);
            attributeInfoList.add(attributeInfo);
        }
        return attributeInfoList;
    }

    private FMInfo readFMInfo(InputStream in){
        FMInfo fmInfo = new FMInfo();
        int accessFlags = U2.read(in);
        int nameIndex = U2.read(in);
        int descripterIndex = U2.read(in);
        fmInfo.setAccessFlags(accessFlags);
        fmInfo.setNameIndex(nameIndex);
        fmInfo.setDescriptorIndex(descripterIndex);
        logger.info("read fields -- accessFlags:{}, nameIndex:{}, descripterIndex:{}",accessFlags,nameIndex,descripterIndex);
        int attributesCount = U2.read(in);
        fmInfo.setAteributesCount(attributesCount);
        List<AttributeInfo> attributeInfos = readAttributes(attributesCount,in);
        fmInfo.setAttributeInfos(attributeInfos);
        return fmInfo;
    }

    private List<FMInfo> readFMInfos(int count,InputStream in){
        List<FMInfo> fmInfos = new ArrayList<>();
        logger.info("*********  Read FM *************** Count : {}",count);
        for(int i=0;i<count;i++){
            logger.info("Read FM  No.{}",i);
            FMInfo fmInfo = readFMInfo(in);
            fmInfos.add(fmInfo);
        }
        return fmInfos;
    }


    private int readClassCP(InputStream in){
        int nameIndex = U2.read(in);
        logger.info("Class nameIndex : {}",nameIndex);
        return nameIndex;
    }

    private String readUtf8CP(InputStream in){
        int length = U2.read(in);
        logger.info("UTF-8 length : {}",length);
        int [] st = new int[length];
        for(int j = 0;j<length;j++){
            st[j] = U1.read(in);
        }
        String str = new String(st,0,st.length);
        logger.info("UTF-8 String : {}",str);
        return str;
    }

    private int [] readFMNInfoCP(InputStream in){
        int res []  = new int[2];
        int classIndex = U2.read(in);
        int nameAndTypeIndex = U2.read(in);
        logger.info("ClassIndex : {}",classIndex);
        logger.info("NameAndTypeIndex : {}",nameAndTypeIndex);
        res[0] = classIndex;
        res[1] = nameAndTypeIndex;
        return res;
    }

    private CPInfo readConstantPool(int cpCount, InputStream in){
        logger.info("*********  Read ConstantPool ***************");
        Map<Integer,String> utf8Infos = new HashMap<>();
        Map<Integer,Object> methoderfs = new HashMap<>();
        Map<Integer,Object> fieldrefs = new HashMap<>();
        Map<Integer,Object> nameAndTYpes = new HashMap<>();
        Map<Integer,Integer> classs = new HashMap<>();
        for(int i =1;i<cpCount;i++){
            logger.info("ConstantPool No {}",i);
            int tag = U1.read(in);
            logger.info("CP tag : {}",tag);
            if(tag==10){
                logger.info("CP type is : {}",CP_METHODREF);
                int[] methodref = readFMNInfoCP(in);
                methoderfs.put(i,methodref);
            }else if(tag==9){
                logger.info("CP type is : {}",CP_FIELDREF);
                int[] fieldref = readFMNInfoCP(in);
                fieldrefs.put(i,fieldref);
            }else if(tag==7){
                logger.info("CP type is : {}",CP_CLASS);
                int nameIndex = readClassCP(in);
                classs.put(i,nameIndex);
            }else if(tag==1){
                logger.info("CP type is : {}",CP_UTF8);
                String utf8 = readUtf8CP(in);
                utf8Infos.put(i,utf8);
            }else if(tag==12){
                logger.info("CP type is : {}",CP_NAMEANDTYPE);
                int[] nameAndType = readFMNInfoCP(in);
                nameAndTYpes.put(i,nameAndType);
            }
        }
        CPInfo cpInfo = new CPInfo();
        cpInfo.setClasss(classs);
        cpInfo.setFieldrefs(fieldrefs);
        cpInfo.setMethoderfs(methoderfs);
        cpInfo.setNameAndTYpes(nameAndTYpes);
        cpInfo.setUtf8Infos(utf8Infos);

        return cpInfo;

    }





    /**
     * Code_attribute {
     u2 attribute_name_index;
     u4 attribute_length;
     u2 max_stack;
     u2 max_locals;
     u4 code_length;
     u1 code[code_length];
     u2 exception_table_length;
     {   u2 start_pc;
     u2 end_pc;
     u2 handler_pc;
     u2 catch_type;
     } exception_table[exception_table_length];
     u2 attributes_count;
     attribute_info attributes[attributes_count];
     }
     * @param in
     */
    private Code readAttrCode(InputStream in){
        Code code = new Code();
        logger.info("!!!!! read attr code ");
        int maxStack = U2.read(in);
        int maxLocals = U2.read(in);
        long codeLength = U4.read(in);
        code.setMaxStack(maxStack);
        code.setMaxLocals(maxLocals);
        code.setCodeLength(codeLength);
        logger.info("maxStack : {},maxLocals:{},codeLength:{}",maxStack,maxLocals,codeLength);
        int [] codes = new int[Integer.parseInt(Long.toString(codeLength))];
        for(int i=0;i<codeLength;i++){
            codes[i] = U1.read(in);
        }
        code.setCode(codes);
        logger.info("[] codes :{}",codes);
        int exceptionTableLength = U2.read(in);
        code.setExeceptionTableLength(exceptionTableLength);
        List<Code.ExeceptionTable> execeptionTableList = new ArrayList<>();
        logger.info("Exception length : {}",exceptionTableLength);
        for(int j=0;j<exceptionTableLength;j++){
            int startPc = U2.read(in);
            int endPc = U2.read(in);
            int handlerPc = U2.read(in);
            int catchType = U2.read(in);
            logger.info("Exception - startPc:{}, endPc:{}, handlerPc:{}, catchType:{}",startPc,endPc,handlerPc,catchType);
            Code.ExeceptionTable execeptionTable = code.newExeceptionTable();
            execeptionTable.setStartPc(startPc);
            execeptionTable.setEndPc(endPc);
            execeptionTable.setHandlerPc(handlerPc);
            execeptionTable.setCatchType(catchType);
            execeptionTableList.add(execeptionTable);
        }
        code.setExeceptionTableList(execeptionTableList);
        int attrbituesCount = U2.read(in);
        code.setAttributesCount(attrbituesCount);

        List<AttributeInfo> attributeInfoList = readAttributes(attrbituesCount,in);
        code.setAttributeInfoList(attributeInfoList);
        return code;
    }

    /**
     * LineNumberTable_attribute {
     u2 attribute_name_index;
     u4 attribute_length;
     u2 line_number_table_length;
     {
     u2 start_pc;
     u2 line_number;
     } line_number_table[line_number_table_length];
     }
     * @param in
     */
    private LineNumberTable readAttrLineNumberTable(InputStream in){
        LineNumberTable lineNumberTable = new LineNumberTable();
        List<LineNumberTable.NumberTable> numberTableList = new ArrayList<>();
        logger.info("!!!!! read attr LineNumberTable ");
        int lineNumberTableLength = U2.read(in);
        lineNumberTable.setLineNumberTableLenth(lineNumberTableLength);
        logger.info("lineNumberTableLength:{}",lineNumberTableLength);
        for(int i=0;i<lineNumberTableLength;i++){
            int startPc = U2.read(in);
            int lineNumber = U2.read(in);
            LineNumberTable.NumberTable numberTable = lineNumberTable.newNumberTable();
            numberTable.setStartPc(startPc);
            numberTable.setLineNumber(lineNumber);
            numberTableList.add(numberTable);
            logger.info("startPc:{},lineNumber:{}",startPc,lineNumber);
        }
        lineNumberTable.setNumberTableList(numberTableList);
        return lineNumberTable;
    }

    /**
     * LocalVariableTable_attribute {
     u2 attribute_name_index;
     u4 attribute_length;
     u2 local_variable_table_length;
     {
     u2 start_pc;
     u2 length;
     u2 name_index;
     u2 descriptor_index;
     u2 index;
     } local_variable_table[local_variable_table_length];
     }
     * @param in
     */
    private LocalVariableTable readAttrLocalVariableTable(InputStream in){
        LocalVariableTable localVariableTable = new LocalVariableTable();
        List<LocalVariableTable.VariableTable> variableTableList = new ArrayList<>();
        logger.info("!!!!! read attr LocalVariableTable ");
        int localVariableTableLength = U2.read(in);
        localVariableTable.setLocalVariableTableLength(localVariableTableLength);
        logger.info("LocalVariableTableLength:{}",localVariableTableLength);
        for(int i=0;i<localVariableTableLength;i++){
            LocalVariableTable.VariableTable variableTable = localVariableTable.newVariableTable();
            int startPc = U2.read(in);
            int length = U2.read(in);
            int nameIndex = U2.read(in);
            int descriptorIndex = U2.read(in);
            int index = U2.read(in);
            variableTable.setStartPc(startPc);
            variableTable.setLength(length);
            variableTable.setNameIndex(nameIndex);
            variableTable.setDescriptorIndex(descriptorIndex);
            variableTable.setIndex(index);
            variableTableList.add(variableTable);
            logger.info("StartPc:{}, length:{}, nameIndex:{}, descriptorIndex:{}, index:{}",startPc,length,nameIndex,descriptorIndex,index);
        }
        localVariableTable.setVariableTableList(variableTableList);
        return localVariableTable;
    }

    /**
     * SourceFile_attribute {
     u2 attribute_name_index;
     u4 attribute_length;
     u2 sourcefile_index;
     }
     * @param in
     */
    private int readAttrSourceFile(InputStream in){
        logger.info("!!!!! read attr SourceFile");
        int sourceFileIndex = U2.read(in);
        logger.info("sourceFileIndex:{}, utf8:{}",sourceFileIndex,cpInfo.getUtf8Infos().get(sourceFileIndex));
        return sourceFileIndex;
    }

    /**
     * RuntimeVisibleAnnotations_attribute {
     u2         attribute_name_index;
     u4         attribute_length;
     u2         num_annotations;
     annotation annotations[num_annotations];
     }

     * @param in
     */
    private RuntimeVisibleAnnotations readAttrRuntimeVisibleAnnotations(InputStream in){
        RuntimeVisibleAnnotations runtimeVisibleAnnotations = new RuntimeVisibleAnnotations();
        int numAnnotations = U2.read(in);
        runtimeVisibleAnnotations.setNumAnnotations(numAnnotations);
        List<Annotation> annotations = new ArrayList<>();
        for(int i=0;i<numAnnotations;i++){
            Annotation annotation = readAttrAnnotation(in);
            annotations.add(annotation);
        }
        runtimeVisibleAnnotations.setAnnotations(annotations);
        return runtimeVisibleAnnotations;

    }

    /**
     * annotation {
     u2 type_index;
     u2 num_element_value_pairs;
     {
     u2   element_name_index;
     element_value value;
     } element_value_pairs[num_element_value_pairs];
     }
     * @param in
     */
    private Annotation readAttrAnnotation(InputStream in){
        logger.info("!!!!!  read attr Annotations");
        Annotation annotation = new Annotation();
        int typeIndex = U2.read(in);
        int numElementValuePairs = U2.read(in);
        annotation.setTypeIndex(typeIndex);
        annotation.setNumElementValuePairs(numElementValuePairs);
        List<ElementValuePair> elementValuePairs = new ArrayList<>();
        logger.info("typeIndex : {}, numElementValuePairs : {}",typeIndex,numElementValuePairs);
        for(int j=0;j<numElementValuePairs;j++){
            ElementValuePair elementValuePair = new ElementValuePair();
            int elementNameIndex = U2.read(in);
            elementValuePair.setElementNameIndex(elementNameIndex);
            logger.info("elementNameIndex : {}",elementNameIndex);
            ElementValue elementValue = readAttrAnnotationsElementValue(in);
            elementValuePair.setValue(elementValue);
            elementValuePairs.add(elementValuePair);
        }
        annotation.setElementValuePairs(elementValuePairs);
        return annotation;
    }

    /**
     * element_value {
         u1 tag;
         union {
             u2 const_value_index;
             {
                 u2 type_name_index;
                 u2 const_name_index;
             } enum_const_value;
             u2 class_info_index;
             annotation annotation_value;
             {
                 u2   num_values;
                 element_value values[num_values];
             } array_value;
         } value;
     }
     * @param in
     */
    private ElementValue readAttrAnnotationsElementValue(InputStream in){
        ElementValue elementValue = new ElementValue();
        logger.info("!!!!!  read attr Annotations ElementValue ");
        int tag = U1.read(in);
        int constValueIndex = U2.read(in);
        int typeNameIndex = U2.read(in);
        int constNameIndex = U2.read(in);
        int classInfoIndex = U2.read(in);
        elementValue.setTag(tag);
        elementValue.setConstValueIndex(constValueIndex);
        elementValue.setTypeNameIndex(typeNameIndex);
        elementValue.setConstNameIndex(constNameIndex);
        elementValue.setClassInfoIndex(classInfoIndex);
        logger.info("tag : {},constValueIndex : {},typeNameIndex : {},constaNameIndex : {},classInfoIndex:{}",tag,constValueIndex,typeNameIndex,constNameIndex,classInfoIndex);
        Annotation annotation = readAttrAnnotation(in);
        elementValue.setAnnotation(annotation);
        int numValues = U2.read(in);
        logger.info("numValues : {}",numValues);
        List<ElementValue> elementValues = new ArrayList<>();
        for(int i=0;i<numValues;i++){
            ElementValue e = readAttrAnnotationsElementValue(in);
            elementValues.add(e);
        }
        elementValue.setValues(elementValues);
        return elementValue;
    }
}
