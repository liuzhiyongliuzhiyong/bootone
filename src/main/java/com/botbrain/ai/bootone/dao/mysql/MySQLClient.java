package com.botbrain.ai.bootone.dao.mysql;

import com.botbrain.ai.bootone.entity.IPManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

/**
 * @描述：TODO  Mysql客户端连接
 * @作者：LZY
 * @日期：2019/4/13 11:17
 * @版本：1.0
 **/
@Component
public class MySQLClient {
    private static final Logger logger=LoggerFactory.getLogger(MySQLClient.class);
    @Autowired
    private static Environment env;

    public static Environment getEnv() {
        return env;
    }

    public static void setEnv(Environment env) {
        MySQLClient.env = env;
    }

    private static Connection connection=null;
    /**
      * @作者：LZY
      * @描述：//TODO  获取MySQL连接
      * @日期：2019/4/13 11:25
      **/
    public static Connection getMysqlConn(){
        try {
            if (connection==null){
                synchronized (MySQLClient.class){
                    //调用Class.forName()方法加载驱动程序
                    Class.forName(env.getProperty("jdbc.driver"));
                    logger.info("连接MySQL。。。");
                    connection = DriverManager.getConnection(
                            env.getProperty("jdbc.url"),
                            env.getProperty("jdbc.username"),
                            env.getProperty("jdbc.password"));
                    logger.info("成功连接Mysql数据库");
                    return connection;
                }
            }
        }catch (Exception e){
            logger.error("获取MySQL连接出错",e);
        }
        return null;
    }
    /**
      * @作者：LZY
      * @描述：//TODO  获取Statement对象
      * @日期：2019/4/13 14:49
      **/
    public static Statement getStateMent(){
        try {
            return connection.createStatement();
        }catch (Exception e){
            logger.error("获取Statement对象出错",e);
        }
        return null;
    }
    /**
      * @作者：LZY
      * @描述：//TODO  获取预编译对象
      * @日期：2019/4/13 14:51
      **/
    public static PreparedStatement getPst(String sql){
        try {
            return connection.prepareStatement(sql);
        }catch (Exception e){
            logger.error("获取预编译对象",e);
        }
        return null;
    }
    /**
      * @作者：LZY
      * @描述：//TODO  关闭MySQL连接
      * @日期：2019/4/13 11:28
      **/
    public static void closeMySQL(){
        try {
            if (connection!=null) connection.close();
            if (connection!=null) connection=null;
        }catch (Exception e){
            logger.error("关闭MySQL连接出错了",e);
        }
    }
    /**
      * @作者：LZY
      * @描述：//TODO  关闭MySQL预编译及结果集信息
      * @日期：2019/4/13 11:31
      **/
    public static void closeMySQLPR(PreparedStatement pst, ResultSet rs){
        try {
            if (rs!=null) rs.close();
            if (rs!=null) rs=null;
            if (pst!=null) pst.close();
            if (pst!=null) pst=null;
        }catch (Exception e){
            logger.error("关闭MySQL预编译及结果集信息出错了",e);
        }
    }
    /**
      * @作者：LZY
      * @描述：//TODO  关闭MySQL结果集信息
      * @日期：2019/4/13 11:35
      **/
    public static void closeMySQLSR(Statement st,ResultSet rs){
        try {
            if (rs!=null) rs.close();
            if (rs!=null) rs=null;
            if (st!=null) st.close();
            if (st!=null) st=null;
        }catch (Exception e){
            logger.error("关闭MySQL结果集信息出错",e);
        }
    }
    /**
      * @作者：LZY
      * @描述：//TODO  创建代理IP去重表
      * @日期：2019/4/13 11:37
      **/
    private static boolean createIPRepeatTable(){
        Statement statement=null;
        try {
            String sql="CREATE TABLE IPRepeatManager(" +
                    " id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    " ip VARCHAR(15) NOT NULL UNIQUE, " +
                    " INDEX IPRepeatManager_seq(ip)" +
                    " )ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            statement=connection.createStatement();
            if (statement.executeUpdate(sql)==0)logger.info("IPRepeatManager表创建成功");
            else logger.info("IPRepeatManager表创建失败");
        }catch (Exception e){
            logger.error("创建代理IP表失败",e);
        }finally {
            closeMySQLSR(statement,null);
        }
        return false;
    }
    /**
     * @作者：LZY
     * @描述：//TODO  创建代理IP表
     * @日期：2019/4/13 11:37
     **/
    private static boolean createIPTable(){
        Statement statement=null;
        try {
            String sql="CREATE TABLE IPManager(" +
                    " id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    " ip VARCHAR(15) NOT NULL UNIQUE, " +
                    " port INT(10) NOT NULL, " +
                    " username VARCHAR(20), " +
                    " password VARCHAR(20), " +
                    " ip_city VARCHAR(50), " +
                    " ip_type VARCHAR(50), " +
                    " INDEX IPManager_seq(ip)" +
                    " )ENGINE=InnoDB DEFAULT CHARSET=utf8;";
            statement=connection.createStatement();
            if (statement.executeUpdate(sql)==0)logger.info("IPManager表创建成功");
            else logger.info("IPManager表创建失败");
        }catch (Exception e){
            logger.error("创建代理IP表失败",e);
        }finally {
            closeMySQLSR(statement,null);
        }
        return false;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context =SpringApplication.run(MySQLClient.class,args);
        env=context.getEnvironment();
        MySQLClient.getMysqlConn();
//        createIPRepeatTable();
//        createIPTable();
        MySQLIpDao mySQLIpDao=new MySQLIpImpl();
//        boolean flag=mySQLIpDao.insertIp(new IPManager());
//        System.out.println(flag);
        List<IPManager> ipList=mySQLIpDao.queryByPage(3);
        System.out.println(ipList);
    }
}
