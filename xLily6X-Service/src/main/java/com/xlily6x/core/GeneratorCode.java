package com.xlily6x.core;

import com.xlily6x.core.util.CodeGeneratorUtil;
import com.xlily6x.core.util.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成主程序入口
 * Created by xiaowenlong on 22/11/2017.
 */
public class GeneratorCode {

    static Logger logger = LogManager.getLogger();

    /**
     * 代码生成临时目录
     */
    static String TEMPPATH = "temp";
    /**
     * src 目录
     */
    static String CLASSPATH = "src\\main\\java";
    /**
     * 自定义包名
     */
    private static String BASEPACKAGE = "com.xlily6x";

    static String PROJECT_PATH_KEY = "user.dir";


    /**
     * 数据库连接信息
     */
    private static String USERNAME = "system";
    private static String PASSWORD = "123456";
    private static String URL = "jdbc:mysql://10.205.138.135:8066/system?useUnicode=true&amp;characterEncoding=UTF-8";


    //需要生成的表 此数组中的表将会生成代码  为空生成所有表
    private static String [] INCLUDETABLES = new String[]{};
    //不需要生成的表 此数组中的表将不会生成代码  为空生成所有表
    private static String [] EXCLUDETABLES = new String[]{"test_sys_user"};


    public static void main(String args[]){
        String userDir = System.getProperty(PROJECT_PATH_KEY);
        logger.info(userDir);

        CodeGeneratorUtil.GeneratorHelper g = generatorCode(FileUtil.getTargetPath(userDir,TEMPPATH));
        String basePackage = g.getBasePackage();
        String homePath = g.getHomePath();

        Map<String,String> modals = new HashMap<>();
        modals.put("api","xLily6X-Api");
        modals.put("domain","xLily6X-Facade");
        modals.put("service","xLily6X-Service");
        modals.put("mapper","xLily6X-Service");

        copyCode(userDir,homePath,basePackage,modals);

    }

    /**
     * 复制生成的文件到不同项目指定位置
     * @param userDir
     * @param homePath
     * @param basePackage
     * @param modals
     */
    static void copyCode(String userDir,String homePath,String basePackage,Map<String,String> modals){
        String src = CLASSPATH;
        try {
            for(String key : modals.keySet()){
                String tpath = FileUtil.getTargetPath(userDir,modals.get(key),src,FileUtil.packageToPath(basePackage),key);
                String spath = FileUtil.getTargetPath(homePath,FileUtil.packageToPath(basePackage),key);
                logger.info("copy code sources path :");
                logger.info(spath);
                logger.info("copy code target path :");
                logger.info(tpath);
                FileUtil.copyDirectory(spath,tpath);
            }
            //删除临时文件
            FileUtil.deleteDirectory(FileUtil.getTargetPath(userDir,TEMPPATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    static CodeGeneratorUtil.GeneratorHelper generatorCode(String userDir){
        logger.info("Lily CodeGenerator");
        CodeGeneratorUtil.GeneratorHelper g =  CodeGeneratorUtil.getGeneratorHelper();
        g.setIncludeTables(INCLUDETABLES);
        g.setExcludeTables(EXCLUDETABLES);
        g.setPassword(PASSWORD);
        g.setUserName(USERNAME);
        g.setHomePath(userDir);
        g.setBasePackage(BASEPACKAGE);
        g.setUrl(URL);
        try {
            CodeGeneratorUtil.generator(g);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }
}
