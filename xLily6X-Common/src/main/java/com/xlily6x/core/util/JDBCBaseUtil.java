package com.xlily6x.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaowenlong on 2/8/2017.
 */
public class JDBCBaseUtil {
    /**
     * 日志
     */
    static Logger logger = LogManager.getLogger(JDBCBaseUtil.class);

    private static PreparedStatement ps = null;
    private static ResultSet rs= null;

    /**
     * 通过sql语句，自动生成列名。不需要传入列名集合了
     * @author xiaowenlong
     * @date 2014年12月30日下午4:27:38
     * @param sql 查询sql语句
     * @param param 查询sql语句中的参数 , 没有则不提供  可不给
     * @return
     */
    public static List<Map<String, Object>> query(Connection conn,String sql,Object ... param){
        //获得列名称
        List<String> cloumNames = getCloums(sql);
        if(cloumNames==null||cloumNames.size()<=0){
            logger.error("该方法不支持  * 号查询");
            return null;
        }
        return query(conn,sql,cloumNames);
    }

    /**
     * DQL
     * 通用查询
     * @author xiaowenlong
     * @date 2014年12月30日上午10:57:33
     * @param sql    查询用的sql语句  必须传入
     * @param cloumNames 查询sql语句中的列，按顺序 必须传入
     * @param param 查询sql语句中的参数 , 没有则不提供  可不给
     * @return
     */
    public static List<Map<String, Object>> query(Connection conn,String sql,List<String> cloumNames, Object ... param){
        if(cloumNames==null||cloumNames.size()<=0){
            logger.error("列名集合不能为空");
            return null;
        }
        logger.debug("sql:"+sql);
        List<Map<String, Object>> resultData = new ArrayList<Map<String, Object>>();
        try {
//            conn = getConnection();
            ps = conn.prepareStatement(sql);

            //设置参数
            if(param.length>=1){
                String strParam="";
                int i = 1;
                for(Object o:param){
                    ps.setObject(i, o);
                    strParam+=o+",";
                    i++;
                }
                logger.debug("PrepareParams:{"+strParam.substring(0,strParam.length()-1)+"}");
            }
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String,Object> resultObj = new HashMap<String, Object>();
                for(int j=0;j<cloumNames.size();j++){
                    resultObj.put(cloumNames.get(j), rs.getObject(cloumNames.get(j)));
                    logger.debug(cloumNames.get(j)+":"+rs.getObject(cloumNames.get(j)));
                }
                resultData.add(resultObj);
                logger.debug(resultObj.toString());
            }
            logger.debug("resultSize:{"+resultData.size()+"}");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCBaseUtil.closeAll(rs, ps, conn);
        }
        return resultData;
    }

    /**
     * DML
     * 增删改执行方法
     * @author xiaowenlong
     * @date 2014年12月30日下午2:37:26
     * @param sql
     * @param obj
     * @return 返回受影响行数
     */
    public static Integer excute(Connection conn,String sql, List<Object> obj){
        int res = 0;
        try {
//            conn = getConnection();
            ps = conn.prepareStatement(sql);
            if(obj.size()>0){
                int i = 1;
                for(Object o : obj){
                    ps.setObject(i, o);
                    i++;
                }
            }
            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCBaseUtil.closeAll(rs, ps, conn);
        }
        return res;
    }

    /**
     * DML
     * 增删改执行方法
     * @author xiaowenlong
     * @date 2014年12月30日下午2:37:26
     * @param sql
     * @param obj
     * @return 返回受影响行数
     */
    public static Integer excute(Connection conn,String sql, Object[] obj){
        int res = 0;
        try {
//            conn = getConnection();
            ps = conn.prepareStatement(sql);
            if(obj.length>0){
                int i = 1;
                for(Object o : obj){
                    ps.setObject(i, o);
                    i++;
                }
            }
            res = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCBaseUtil.closeAll(rs, ps, conn);
        }
        return res;
    }


    /**
     * 连接数据库
     *
     * @return
     */
//    private static Connection getConnection() {
//
////        try {
//////            Class.forName(PropertiesUtil.getDriver());
//////            conn = DriverManager.getConnection(PropertiesUtil.getUrl(),
//////                    PropertiesUtil.getUsername(), PropertiesUtil.getPassword());
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////        return conn;
//    }

    /**
     * 关闭
     */
    private static void closeAll(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 截取列名
     * @author xiaowenlong
     * @date 2014年12月30日下午4:27:08
     * @param sql
     * @return 返回 列头 集合
     */
    private static List<String> getCloums(String sql){
        List<String> cloumNames = new ArrayList<String>();
        int endIndex = sql.lastIndexOf("from")>0?sql.lastIndexOf("from"):sql.lastIndexOf("FROM");
        String str = sql.substring(6,endIndex);
        if(str.trim().equals("*")){
            logger.debug("Sql语句中请勿使用 * 号，必须使用详细字段作为列头");
            return null;
        }
        String [] cloums = str.trim().split(",");
        for(String s:cloums){
            cloumNames.add(s.trim());
        }
        return cloumNames;
    }

    private static List<String> getCloumNames() {
        return new ArrayList<String>();
    }
}
