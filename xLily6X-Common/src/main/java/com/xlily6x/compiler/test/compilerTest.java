package com.xlily6x.compiler.test;

import com.xlily6x.compiler.structure.AttributeInfo;
import com.xlily6x.compiler.structure.CPInfo;
import com.xlily6x.compiler.structure.ClassFile;
import com.xlily6x.compiler.structure.Code;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by xiaowenlong on 9/4/2018.
 */
public class compilerTest {

//    String CP_METHODREF = "CONSTANT_Methodref";
//    String CP_FIELDREF = "CONSTANT_Fieldref";
//    String CP_CLASS = "CONSTANT_Class";
//    String CP_UTF8 = "CONSTANT_Utf8";
//    String CP_NAMEANDTYPE = "CP_NameAndType";
//    CPInfo cpInfo;
//    ClassFile classFile;
//    Logger logger = LogManager.getLogger();
//
//
//
//    public void excute() throws Exception{
//        String filepath = "D:\\Leo-Xiao\\MyWork\\project\\Pre-Order\\AF-PreOrder\\branch\\newPreorder-0821-0901\\PreOrder-0828-0901\\PreOrder-Core\\target\\test-classes\\ClassTest.class";
//        File file = new File(filepath);
//        FileInputStream in = new FileInputStream(file);
//        classFile = new ClassFile();
//        classFile.setMagic(U4.read(in));
//        classFile.setMajorVersion(U2.read(in));
//        classFile.setMinorVersion(U2.read(in));
//        classFile.setConstantPoolCount(U2.read(in));
//
//
//        cpInfo = readCP(classFile.getConstantPoolCount(),in);
//        classFile.setCpInfo(cpInfo);
//        classFile.setAccessFlags(U2.read(in));
//        classFile.setThisClass(U2.read(in));
//        classFile.setSuperClass(U2.read(in));
//        classFile.setInterfacesCount(U2.read(in));
//        int [] interfaces = new int[classFile.getInterfacesCount()];
//        for(int ij=0;ij<classFile.getInterfacesCount();ij++){
//            interfaces[ij] = U2.read(in);
//        };
//        classFile.setInterfaces(interfaces);
//
//        classFile.setFieldsCount(U2.read(in));
//        readFields(classFile.getFieldsCount(),in);
//
//        logger.info("剩余字节 ： {}", in.available());
//
//        classFile.setMethodsCount(U2.read(in));
//        logger.info("剩余字节 ： {}", in.available());
//        readFields(classFile.getMethodsCount(),in);
//        logger.info("剩余字节 ： {}", in.available());
////
//        classFile.setAttrbutesCount(U2.read(in));
//        readAttributes(classFile.getAttrbutesCount(),in);
//        System.out.println(classFile);
//        in.close();
//    }
//
//    /**
//     * Code_attribute {
//         u2 attribute_name_index;
//         u4 attribute_length;
//         u2 max_stack;
//         u2 max_locals;
//         u4 code_length;
//         u1 code[code_length];
//         u2 exception_table_length;
//         {   u2 start_pc;
//         u2 end_pc;
//         u2 handler_pc;
//         u2 catch_type;
//         } exception_table[exception_table_length];
//         u2 attributes_count;
//         attribute_info attributes[attributes_count];
//     }
//     * @param in
//     */
//    public Code readAttrCode(FileInputStream in){
//        Code code = new Code();
//        logger.info("!!!!! read attr code ");
//        int maxStack = U2.read(in);
//        int maxLocals = U2.read(in);
//        long codeLength = U4.read(in);
//        code.setMaxStack(maxStack);
//        code.setMaxLocals(maxLocals);
//        code.setCodeLength(codeLength);
//        logger.info("maxStack : {},maxLocals:{},codeLength:{}",maxStack,maxLocals,codeLength);
//        int [] codes = new int[Integer.parseInt(Long.toString(codeLength))];
//        for(int i=0;i<codeLength;i++){
//            codes[i] = U1.read(in);
//        }
//        code.setCode(codes);
//        logger.info("[] codes :{}",codes);
//        int exceptionTableLength = U2.read(in);
//        code.setExeceptionTableLength(exceptionTableLength);
//        List<Code.ExeceptionTable> execeptionTableList = new ArrayList<>();
//        logger.info("exception length :{}",exceptionTableLength);
//        for(int j=0;j<exceptionTableLength;j++){
//            int startPc = U2.read(in);
//            int endPc = U2.read(in);
//            int handlerPc = U2.read(in);
//            int catchType = U2.read(in);
//            logger.info("exception - startPc:{},endPc:{},handlerPc:{},catchType:{}",startPc,endPc,handlerPc,catchType);
//            Code.ExeceptionTable execeptionTable = code.newExeceptionTable();
//            execeptionTable.setStartPc(startPc);
//            execeptionTable.setEndPc(endPc);
//            execeptionTable.setHandlerPc(handlerPc);
//            execeptionTable.setCatchType(catchType);
//            execeptionTableList.add(execeptionTable);
//        }
//        code.setExeceptionTableList(execeptionTableList);
//        int attrbituesCount = U2.read(in);
//        code.setAttributesCount(attrbituesCount);
//        AttributeInfo attributeInfo = readAttributes(attrbituesCount,in);
//
//
//        return code;
//    }
//
//    /**
//     * LineNumberTable_attribute {
//         u2 attribute_name_index;
//         u4 attribute_length;
//         u2 line_number_table_length;
//         {
//            u2 start_pc;
//            u2 line_number;
//         } line_number_table[line_number_table_length];
//     }
//     * @param in
//     */
//    public void readAttrLineNumberTable(FileInputStream in){
//        logger.info("!!!!! read attr LineNumberTable ");
//        int lineNumberTableLength = U2.read(in);
//        logger.info("lineNumberTableLength:{}",lineNumberTableLength);
//        for(int i=0;i<lineNumberTableLength;i++){
//            int startPc = U2.read(in);
//            int lineNumber = U2.read(in);
//            logger.info("startPc:{},lineNumber:{}",startPc,lineNumber);
//        }
//    }
//
//
//    /**
//     * SourceFile_attribute {
//         u2 attribute_name_index;
//         u4 attribute_length;
//         u2 sourcefile_index;
//     }
//     * @param in
//     */
//    public void readAttrSourceFile(FileInputStream in){
//        logger.info("!!!!! read attr SourceFile");
//        int sourceFileIndex = U2.read(in);
//        logger.info("sourceFileIndex:{}, utf8:{}",sourceFileIndex,cpInfo.getUtf8Infos().get(sourceFileIndex));
//
//    }
//
//    /**
//     * RuntimeVisibleAnnotations_attribute {
//         u2         attribute_name_index;
//         u4         attribute_length;
//         u2         num_annotations;
//         annotation annotations[num_annotations];
//         }
//
//     * @param in
//     */
//    public void readAttrRuntimeVisibleAnnotations(FileInputStream in){
//        int numAnnotations = U2.read(in);
//        for(int i=0;i<numAnnotations;i++){
//            readAttrAnnotations(in);
//        }
//
//    }
//
//    /**
//     * annotation {
//         u2 type_index;
//         u2 num_element_value_pairs;
//         {
//         u2   element_name_index;
//         element_value value;
//         } element_value_pairs[num_element_value_pairs];
//     }
//     * @param in
//     */
//    public void readAttrAnnotations(FileInputStream in){
//        logger.info("!!!!! read attr Annotations");
//        int typeIndex = U2.read(in);
//        int numElementValuePairs = U2.read(in);
//        logger.info("typeIndex:{},numElementValuePairs:{}",typeIndex,numElementValuePairs);
//        for(int j=0;j<numElementValuePairs;j++){
//            int elementNameIndex = U2.read(in);
//            logger.info("elementNameIndex:{}",elementNameIndex);
//            readAttrAnnotationsElementValue(in);
//        }
//    }
//
//    /**
//     * element_value {
//         u1 tag;
//         union {
//         u2 const_value_index;
//
//         {
//             u2 type_name_index;
//             u2 const_name_index;
//         } enum_const_value;
//
//         u2 class_info_index;
//
//         annotation annotation_value;
//
//         {
//             u2   num_values;
//             element_value values[num_values];
//         } array_value;
//         } value;
//     }
//     * @param in
//     */
//    public void readAttrAnnotationsElementValue(FileInputStream in){
//        logger.info("!!!!! read attr Annotations ElementValue ");
//        int tag = U1.read(in);
//        int constValueIndex = U2.read(in);
//        int typeNameIndex = U2.read(in);
//        int constNameIndex = U2.read(in);
//        int classInfoIndex = U2.read(in);
//        logger.info("tag:{},constValueIndex:{},typeNameIndex:{},constaNameIndex:{},classInfoIndex:{}",tag,constValueIndex,typeNameIndex,constNameIndex,classInfoIndex);
//        readAttrAnnotations(in);
//        int numValues = U2.read(in);
//        logger.info("numValues:{}",numValues);
//        for(int i=0;i<numValues;i++){
//            readAttrAnnotationsElementValue(in);
//        }
//    }
//
//    /**
//     * LocalVariableTable_attribute {
//         u2 attribute_name_index;
//         u4 attribute_length;
//         u2 local_variable_table_length;
//         {
//             u2 start_pc;
//             u2 length;
//             u2 name_index;
//             u2 descriptor_index;
//             u2 index;
//         } local_variable_table[local_variable_table_length];
//     }
//     * @param in
//     */
//    public void readAttrLocalVariableTable(FileInputStream in){
//        logger.info("!!!!! read attr LocalVariableTable ");
//        int localVariableTableLength = U2.read(in);
//        logger.info("localVariableTableLength:{}",localVariableTableLength);
//        for(int i=0;i<localVariableTableLength;i++){
//            int startPc = U2.read(in);
//            int length = U2.read(in);
//            int nameIndex = U2.read(in);
//            int descriptorIndex = U2.read(in);
//            int index = U2.read(in);
//            logger.info("startPc:{},length:{},nameIndex:{},descriptorIndex:{},index:{}",startPc,length,nameIndex,descriptorIndex,index);
//        }
//    }
//
//
//    public AttributeInfo readAttributes(int ateributesCount,FileInputStream in){
//        AttributeInfo attributeInfo = new AttributeInfo();
//        logger.info("~~~~~~ Read Attributes  ~~~~~~~~");
//        logger.info("attr count : {}",ateributesCount);
//        Map<Integer,String> cputf8s = cpInfo.getUtf8Infos();
//        List<Object> infoList = new ArrayList<>();
//        for(int k=0;k<ateributesCount;k++){
//            int attributeNameIndex = U2.read(in);
//            long atteributeLength = U4.read(in);
//            String attrType = cputf8s.get(attributeNameIndex);
//            logger.info("read attr --  attrNameIndex:{} , length:{} , type:{} ",attributeNameIndex,atteributeLength,attrType);
//            if(StringUtils.equalsIgnoreCase(attrType,"code")){
//                //处理 code 类型属性
//                Code code = readAttrCode(in);
//                infoList.add(code);
//            }else if(StringUtils.equalsIgnoreCase(attrType,"LineNumberTable")){
//                //处理 LineNumberTable 类型属性
//                readAttrLineNumberTable(in);
//            }else if(StringUtils.equalsIgnoreCase(attrType,"LocalVariableTable")){
//                //处理 LocalVariableTable 类型属性
//                readAttrLocalVariableTable(in);
//            }else if(StringUtils.equalsIgnoreCase(attrType,"RuntimeVisibleAnnotations")){
//                //处理 RuntimeVisibleAnnotations 类型属性
//                readAttrRuntimeVisibleAnnotations(in);
//            }else if(StringUtils.equalsIgnoreCase(attrType,"SourceFile")){
//                //处理 SourceFile 类型属性
//                readAttrSourceFile(in);
//            }
//        }
//        attributeInfo.setInfoList(infoList);
//        return attributeInfo;
//    }
//    public void readFields(int fieldCount,FileInputStream in){
//        logger.info("*********  Read Fields *************** Count : {}",fieldCount);
//        for(int j=0;j<fieldCount;j++){
//            logger.info("Read filed No.{}",j);
//            int accessFlags = U2.read(in);
//            int nameIndex = U2.read(in);
//            int descripterIndex = U2.read(in);
//            logger.info("read fields -- accessFlags:{}, nameIndex:{}, descripterIndex:{}",accessFlags,nameIndex,descripterIndex);
//            int ateributesCount = U2.read(in);
//
//            readAttributes(ateributesCount,in);
//        }
//    }
//
//    public CPInfo readCP(int cpCount, FileInputStream in){
//        Map<Integer,String> utf8Infos = new HashMap<>();
//        Map<Integer,Object> methoderfs = new HashMap<>();
//        Map<Integer,Object> fieldrefs = new HashMap<>();
//        Map<Integer,Object> nameAndTYpes = new HashMap<>();
//        Map<Integer,Integer> classs = new HashMap<>();
//        for(int i =1;i<cpCount;i++){
//            logger.info("ConstantPool No {}",i);
//            int tag = U1.read(in);
//            logger.info("CP tag : {}",tag);
//            if(tag==10){
//                logger.info("CP type is : {}",CP_METHODREF);
//                int[] methodref = readMethodf(in);
//                methoderfs.put(i,methodref);
//            }else if(tag==9){
//                logger.info("CP type is : {}",CP_FIELDREF);
//                int[] fieldref = readMethodf(in);
//                fieldrefs.put(i,fieldref);
//            }else if(tag==7){
//                logger.info("CP type is : {}",CP_CLASS);
//                int nameIndex = readClass(in);
//                classs.put(i,nameIndex);
//            }else if(tag==1){
//                logger.info("CP type is : {}",CP_UTF8);
//                String utf8 = readUtf8(in);
//                utf8Infos.put(i,utf8);
//            }else if(tag==12){
//                logger.info("CP type is : {}",CP_NAMEANDTYPE);
//                int[] nameAndType = readMethodf(in);
//                nameAndTYpes.put(i,nameAndType);
//            }
//        }
//        CPInfo cpInfo = new CPInfo();
//        cpInfo.setClasss(classs);
//        cpInfo.setFieldrefs(fieldrefs);
//        cpInfo.setMethoderfs(methoderfs);
//        cpInfo.setNameAndTYpes(nameAndTYpes);
//        cpInfo.setUtf8Infos(utf8Infos);
//
//        return cpInfo;
//
//    }
//    public void readC(int tag,FileInputStream in){
//
//    }
//    public int[] readMethodf(FileInputStream in){
//        int res []  = new int[2];
//        int classIndex = U2.read(in);
//        int nameAndTypeIndex = U2.read(in);
//        logger.info("classIndex : {}",classIndex);
//        logger.info("nameAndTypeIndex : {}",nameAndTypeIndex);
//        res[0] = classIndex;
//        res[1] = nameAndTypeIndex;
//        return res;
//    }
//    public int readClass(FileInputStream in){
//        int nameIndex = U2.read(in);
//        logger.info("nameIndex : {}",nameIndex);
//        return nameIndex;
//    }
//    public String readUtf8(FileInputStream in){
//        int length = U2.read(in);
//        logger.info("UTF-8 length : {}",length);
//        byte [] bytes = new byte[length];
//        int [] st = new int[length];
//        for(int j = 0;j<length;j++){
//            st[j] = U1.read(in);
//        }
//        String str = new String(st,0,st.length);
//        logger.info("UTF-8 String : {}",str);
//        return str;
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//    public void javassistDemo() throws Exception{
//        ClassPool pool = ClassPool.getDefault();
//
//        //定义类
//        CtClass stuClass = pool.makeClass("com.xlily6x.compiler.test.Test2");
//
//
//        //hobbies属性
//        CtField ageField = new CtField(pool.getCtClass("java.util.List"), "hobbies", stuClass);
//        stuClass.addField(ageField);
//
//        Class<?> clazz = stuClass.toClass();
//        System.out.println("class:"+clazz.getName());
//
//        System.out.println("------------属性列表------------");
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            System.out.println(field.getType()+"\t"+field.getName());
//        }
//
//        System.out.println("------------方法列表------------");
//        //方法
//        Method[] methods = clazz.getMethods();
//        for (Method method: methods){
//            System.out.println(method.getReturnType()+"\t"+method.getName()+"\t"+ Arrays.toString(method.getParameterTypes()));
//        }
//        System.out.println( clazz.newInstance().getClass());
//    }
//
//
//    public static void main(String args[]) throws Exception{
//        compilerTest c = new compilerTest();
//        c.excute();
//    }
//

}
