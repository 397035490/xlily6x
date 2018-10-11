package com.xlily6x.compiler.test;

import com.xlily6x.compiler.read.Executor;
import com.xlily6x.compiler.structure.ClassFile;

/**
 * Created by xiaowenlong on 9/4/2018.
 */
public class compiler {


    public static void main(String args[]) throws Exception{
        String filepath = "**\\target\\test-classes\\ClassTest.class";
        ClassFile classFile = Executor.compiler(filepath);
        System.out.print("success");
        System.out.print(classFile);
    }


}
