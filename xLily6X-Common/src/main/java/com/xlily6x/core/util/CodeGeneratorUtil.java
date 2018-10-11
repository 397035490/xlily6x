package com.xlily6x.core.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 21/7/2017.
 */
public class CodeGeneratorUtil {

    static Logger logger = LogManager.getLogger(CodeGeneratorUtil.class);

    public static String generatorCodeZip(GeneratorHelper generatorHelper) throws Exception{
        File srcdir = new File(generatorHelper.getHomePath());
        if (!srcdir.exists()){
            logger.debug("HomePath not exists !");
            if(!srcdir.getParentFile().mkdir()){
                logger.debug("Create directory failure" );
            }else{
                logger.debug("Create directory success" );
            }
        }
        CodeGeneratorUtil.generator(generatorHelper);
        return CodeGeneratorUtil.zipped(generatorHelper.getHomePath());
    }

    public static GeneratorHelper getGeneratorHelper(){
        return new GeneratorHelper();
    }



    private static String zipped(String homePath) throws Exception{
        String zipFilePath = homePath+".zip";
        File zipFile = new File(zipFilePath);
        File srcdir = new File(homePath);
        Project prj = new Project();

        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        fileSet.setDir(srcdir);
        //fileSet.setIncludes("**/*.java"); //包括哪些文件或文件夹 eg:zip.setIncludes("*.java");
        //fileSet.setExcludes(...); //排除哪些文件或文件夹

        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(zipFile);
        zip.addFileset(fileSet);
        zip.execute();

        return zipFilePath;
    }

    private synchronized static String generatorDirectory(){
        long time = System.currentTimeMillis();
        return String.valueOf(time);
    }


    public static void main(String args[]){
        GeneratorHelper gh = CodeGeneratorUtil.getGeneratorHelper();
        gh.setHomePath("D://");
        gh.setUrl("jdbc:sqlserver://10.205.138.132:1433;databaseName=Pre_Order_UAT");
        gh.setUserName("Pre_Order_UAT_user");
        gh.setPassword("keidjue_87");
        gh.setTableNames(new String[]{"TB_Currency"});
        try {
            CodeGeneratorUtil.generator(gh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generator(GeneratorHelper generatorHelper) throws Exception{

        //创建代码自动生成器
        AutoGenerator mpg = new AutoGenerator();
        //全局配置
        mpg.setGlobalConfig(CodeGeneratorUtil.getGlobalConfig(generatorHelper.getHomePath()));
        //数据源配置
        mpg.setDataSource(CodeGeneratorUtil.getDataSourceConfig(generatorHelper));
        //策略配置
        mpg.setStrategy(CodeGeneratorUtil.getStrategyConfig(generatorHelper));
        //包配置
        mpg.setPackageInfo(CodeGeneratorUtil.getPackageConfig(generatorHelper));
        //自定义模板
        mpg.setTemplate(CodeGeneratorUtil.getTemplateConfig());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("providerClass", "IProvider");
        mpg.setCfg(getInjectionConfig(map));

        mpg.execute();
    }
    private static InjectionConfig getInjectionConfig(final Map<String, Object> map){
        InjectionConfig cfg = new InjectionConfig() {
            public void initMap() {
                this.setMap(map);
            }
        };
        return cfg;
    }

    private static TemplateConfig getTemplateConfig(){
        TemplateConfig tc = new TemplateConfig();
        tc.setController("templates/controller.java.vm");
        tc.setEntity("templates/entity.java.vm");
        tc.setMapper("templates/mapper.java.vm");
        tc.setXml("templates/mapper.xml.vm");
        tc.setService("templates/service.java.vm");
        return tc;
    }

    private static PackageConfig getPackageConfig(GeneratorHelper generatorHelper){
        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(generatorHelper.getBasePackage());
        pc.setEntity("domain");
        pc.setMapper("mapper");
        pc.setXml("mapper.xml");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("api");
        return pc;
    }


    private static StrategyConfig getStrategyConfig(GeneratorHelper generatorHelper){
        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        if(null!=generatorHelper.getExcludeTables()&&generatorHelper.getExcludeTables().length>0){
            strategy.setExclude(generatorHelper.getExcludeTables());
        }
        if(null!=generatorHelper.getIncludeTables()&&generatorHelper.getIncludeTables().length>0){
            strategy.setInclude(generatorHelper.getIncludeTables());
        }
        strategy.setSuperControllerClass("com.xlily6x.core.base.SuperController");
//        strategy.setInclude(generatorHelper.getTableNames());
//        strategy.setSuperEntityColumns(
//                new String[] { "id_", "enable_", "remark_", "create_by", "create_time", "update_by", "update_time" });
        return strategy;
    }

    public static DataSourceConfig getDataSourceConfig(GeneratorHelper generatorHelper){
        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(getDbType(generatorHelper.getDriverName()));
        dsc.setDriverName(generatorHelper.getDriverName());
        dsc.setUsername(generatorHelper.getUserName());
        dsc.setPassword(generatorHelper.getPassword());
        dsc.setUrl(generatorHelper.getUrl());
        return dsc;
    }

    private static DbType getDbType(String driverName){
        if(driverName.contains("mysql")){
            return DbType.MYSQL;
        }else if(driverName.contains("sqlserver")){
            return DbType.SQL_SERVER;
        }else{
            return DbType.MYSQL;
        }

    }


    private static GlobalConfig getGlobalConfig(String homePath){
        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(homePath);
        gc.setFileOverride(false);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);
        gc.setBaseResultMap(false);
        gc.setBaseColumnList(false);
        gc.setOpen(false);
        gc.setAuthor("XiaoWenLong");
        gc.setServiceImplName("%sService");
        return gc;
    }

    public static class GeneratorHelper{
        private String userName;
        private String password;
        private String driverName;
        private String url;
        private String homePath;
        private String basePackage;

        @Deprecated
        private String [] tableNames;
        //需要生成的表
        private String [] includeTables;
        //不需要生成的表
        private String [] excludeTables;

        private GeneratorHelper(){}

        @Override
        public String toString() {
            return "GeneratorVO{" +
                    "userName='" + userName + '\'' +
                    ", password='" + password + '\'' +
                    ", driverName='" + driverName + '\'' +
                    ", url='" + url + '\'' +
                    ", homePath='" + homePath + '\'' +
                    ", tableNames='" + tableNames + '\'' +
                    '}';
        }

        public String[] getTableNames() {
            return tableNames;
        }

        public void setTableNames(String[] tableNames) {
            this.tableNames = tableNames;
        }

        public String[] getIncludeTables() {
            return includeTables;
        }

        public void setIncludeTables(String[] includeTables) {
            this.includeTables = includeTables;
        }

        public String[] getExcludeTables() {
            return excludeTables;
        }

        public void setExcludeTables(String[] excludeTables) {
            this.excludeTables = excludeTables;
        }

        public String getBasePackage() {
            return basePackage;
        }

        public void setBasePackage(String basePackage) {
            this.basePackage = basePackage;
        }

        public String getHomePath() {
            return homePath;
        }

        public void setHomePath(String homePath) {
            this.homePath = homePath+"\\src\\main\\java";
//            this.homePath = homePath;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getDriverName() {
            return driverName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
            if(url.contains("mysql")){
                this.driverName = "com.mysql.jdbc.Driver";
            }else if(url.contains("sqlserver")){
                this.driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            }else{
                this.driverName = "com.mysql.jdbc.Driver";
            }
        }
    }

}
