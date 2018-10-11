package com.xlily6x.core.util;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaowenlong on 31/1/2018.
 */
public class LilyTest {

    public static void main(String []args){
        String s = "D:\\Leo-Xiao\\MyWork\\project\\project\\xLily6X\\xLily6X-Common\\target\\classes\\com\\xlily6x\\core\\base\\BaseProvider.class";
//        String s = "D:\\Leo-Xiao\\MyWork\\project\\project\\xLily6X\\xLily6X-Common\\target\\classes\\com\\xlily6x\\core\\util\\FileUtil.class";
        s = s.substring(s.indexOf("classes")+8,s.length());
        System.out.println(s);
        s = s.replace("\\",".");
        s = s.replace(".class","");
        System.out.println(s);

        try {
            Class c = Class.forName(s);
            Method[] ms = c.getMethods();
            for(Method m:ms){
                String mn = m.getName();
                System.out.println(mn);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        LilyTest l = new LilyTest();
//        try {
//            l.searchClass();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    List<String> classPaths = new ArrayList<String>();
    public void searchClass() throws ClassNotFoundException {
        //包名
        String basePack = "com.xlily6x";
        //先把包名转换为路径,首先得到项目的classpath
        String classpath = FileUtil.class.getResource("/").getPath();
        //然后把我们的包名basPach转换为路径名
        basePack = basePack.replace(".", File.separator);
        //然后把classpath和basePack合并
        String searchPath = classpath + basePack;
        doPath(new File(searchPath));
        //这个时候我们已经得到了指定包下所有的类的绝对路径了。我们现在利用这些绝对路径和java的反射机制得到他们的类对象
        for (String s : classPaths) {
            //把 D:\work\code\20170401\search-class\target\classes\com\baibin\search\a\A.class 这样的绝对路径转换为全类名com.baibin.search.a.A
//            s = s.replace(classpath.replace("/","\\").replaceFirst("\\\\",""),"").replace("\\",".").replace(".class","");
//            Class cls = Class.forName(s);
            System.out.println(s);

        }
    }

    /**
     * 该方法会得到所有的类，将类的绝对路径写入到classPaths中
     * @param file
     */
    private void doPath(File file) {
        if (file.isDirectory()) {//文件夹
            //文件夹我们就递归
            File[] files = file.listFiles();
            for (File f1 : files) {
                doPath(f1);
            }
        } else {//标准文件
            //标准文件我们就判断是否是class文件
            if (file.getName().endsWith(".class")) {
                //如果是class文件我们就放入我们的集合中。
                classPaths.add(file.getPath());
            }
        }
    }
}
