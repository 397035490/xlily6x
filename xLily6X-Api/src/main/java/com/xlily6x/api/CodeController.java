package com.xlily6x.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.xlily6x.common.vo.GeneratorVO;
import com.xlily6x.core.base.BaseProvider;
import com.xlily6x.core.base.Parameter;
import com.xlily6x.core.util.CodeGeneratorUtil;
import com.xlily6x.core.util.JDBCBaseUtil;
import com.xlily6x.provider.IProvider;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaowenlong on 31/7/2017.
 */
@Controller
@RequestMapping("/code")
public class CodeController {

    @Autowired
    private IProvider provider;

    @RequestMapping(value = "/testDubbo",produces="text/html;charset=UTF-8")
    @ResponseBody
    public String testdubbo(HttpServletRequest request){
        Parameter parameter = new Parameter("TBLeBankInfoService","selectLilyOne");
//        parameter.setParam(new Object[]{27});
        Object o = provider.execute(parameter);
        System.out.print(JSON.toJSONString(o));
        return JSON.toJSONString(o);
    }

    @RequestMapping("/testDubbo2")
    public void testdubbo2(HttpServletRequest request){
        Parameter parameter = new Parameter("testService","sayHi");
        parameter.setParam(new Object[]{"wen long xiao"});
        Object o = provider.execute(parameter);
        System.out.print(JSON.toJSONString(o));
    }

    @RequestMapping("/testDubbo3")
    public void testdubbo3(HttpServletRequest request){
        Parameter parameter = new Parameter("testService","sayHello");
        Object o = provider.execute(parameter);
        System.out.print(JSON.toJSONString(o));
    }

    Logger logger = LogManager.getLogger(CodeController.class);
    @RequestMapping("/generator")
    public void generator(HttpServletRequest request, HttpServletResponse response, GeneratorVO generatorVO){
        logger.debug("is log4j2 log generator");

        final String homePath = request.getSession().getServletContext().getRealPath("download");
        logger.debug(homePath);
        generatorVO.setHomePath(homePath);
        logger.debug(generatorVO);

        String zipFilePath =generatorCode(generatorVO);
        logger.debug("Lily : "+zipFilePath);
        if(StringUtils.isNotEmpty(zipFilePath)){
            //下载
            downloadZip("Code.zip",zipFilePath,response);
        }
    }


    @RequestMapping(value = "/selectTables",method = RequestMethod.POST)
    @ResponseBody
    public String selectTables(GeneratorVO generatorVO){
        logger.debug(generatorVO);
        CodeGeneratorUtil.GeneratorHelper generatorHelper = voToHelper(generatorVO);

        DataSourceConfig config = CodeGeneratorUtil.getDataSourceConfig(generatorHelper);
        Connection conn = config.getConn();
        String sql = "select TABLE_NAME from information_schema.tables where table_schema='"+generatorHelper.getUserName()+"' and table_type='base table';";
        List<Map<String, Object>> list = JDBCBaseUtil.query(conn,sql);
        logger.debug(list);
        Map<String,Object> map = new HashMap<>();
        map.put("tableList",list);
        String json = "";
        try {
            json = JSON.toJSONString(map);
            logger.debug(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("Lily select Tables");
        return json;
    }

    private CodeGeneratorUtil.GeneratorHelper voToHelper(GeneratorVO generatorVO){
        CodeGeneratorUtil.GeneratorHelper generatorHelper = CodeGeneratorUtil.getGeneratorHelper();
        if(StringUtils.isNotEmpty(generatorVO.getHomePath())){
            generatorHelper.setHomePath(generatorVO.getHomePath());
        }
        if(generatorVO.getTableNames()!=null&&generatorVO.getTableNames().length>0){
            generatorHelper.setTableNames(generatorVO.getTableNames());
        }
        generatorHelper.setUserName(generatorVO.getUserName());
        generatorHelper.setPassword(generatorVO.getPassword());
        generatorHelper.setUrl(generatorVO.getUrl());

        return generatorHelper;
    }

    private String generatorCode(GeneratorVO generatorVO){
        String zipFilePath ="";
        try {
            zipFilePath =CodeGeneratorUtil.generatorCodeZip(voToHelper(generatorVO));

        } catch (Exception e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        return zipFilePath;
    }

    private String downloadZip(String fileName,String filePath,HttpServletResponse response){
        logger.debug("is log4j2 log downloadZip");
        try {
            File file = new File(filePath);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + new String(fileName.getBytes("ISO8859-1"), "UTF-8"));
            response.setContentLength((int) file.length());
            response.setContentType("application/zip");// 定义输出类型
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream buff = new BufferedInputStream(fis);
            byte[] b = new byte[1024];// 相当于我们的缓存
            long k = 0;// 该值用于计算当前实际下载了多少字节
            OutputStream myout = response.getOutputStream();// 从response对象中得到输出流,准备下载
            // 开始循环下载
            while (k < file.length()) {
                int j = buff.read(b, 0, 1024);
                k += j;
                myout.write(b, 0, j);
            }
            logger.debug(k);
            myout.flush();
            buff.close();
//            file.delete();
        } catch (Exception e) {
            logger.debug(e);
        }
        return "success";
    }
}
