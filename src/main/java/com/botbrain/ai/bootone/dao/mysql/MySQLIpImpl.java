package com.botbrain.ai.bootone.dao.mysql;

import com.botbrain.ai.bootone.entity.IPManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @描述：TODO  增删改查IP的接口实现类
 * @作者：LZY
 * @日期：2019/4/13 14:25
 * @版本：1.0
 **/
public class MySQLIpImpl implements MySQLIpDao {

    private static final Logger logger=LoggerFactory.getLogger(MySQLIpImpl.class);
    /**
      * @作者：LZY
      * @描述：//TODO  根据页数获取IP
      * @日期：2019/4/13 15:01
      **/
    public List<IPManager> queryByPage(int page) {
        Statement statement=null;
        ResultSet resultSet=null;
        try {
            statement=MySQLClient.getStateMent();
            String sql="select ip,port,username,password,ip_city,ip_type from IPManager where id>=(select id from IPManager ORDER BY id limit "+ (page-1)*20 +",1) limit 20;";
            resultSet=statement.executeQuery(sql);
            List<IPManager> ipList=new ArrayList<>();
            while (resultSet.next()){
                IPManager ipManager=new IPManager();
                ipManager.setIp(resultSet.getString(1));
                ipManager.setPort(resultSet.getInt(2));
                ipManager.setUsername(resultSet.getString(3));
                ipManager.setPassword(resultSet.getString(4));
                ipManager.setIp_city(resultSet.getString(5));
                ipManager.setIp_type(resultSet.getString(6));
                ipList.add(ipManager);
            }
            logger.info("成功获取第{}页的IP",page);
            return ipList;
        }catch (Exception e){
            logger.error("根据页数查询IP出错了，页数：{}",page,e);
        }finally {
            MySQLClient.closeMySQLSR(statement,resultSet);
        }
        return null;
    }
    /**
      * @作者：LZY
      * @描述：//TODO  插入一条IP
      * @日期：2019/4/13 15:01
      **/
    public boolean insertIp(IPManager ipManager) {
        Statement st=null;
        try {
            st=MySQLClient.getStateMent();
            for (int i=2;i<=253;i++){
                String str1="125.208.17."+i;
                String str2="125.208.23."+i;
                String str3="117.122.210."+i;
                String str4="125.208.2."+i;
                String sql1="INSERT INTO IPManager( ip, port, username, password, ip_city, ip_type ) VALUES('"+ str1 +"', 9048, 'test', 'qwer1011','北京', '高匿名')";
                String sql2="INSERT INTO IPManager( ip, port, username, password, ip_city, ip_type ) VALUES('"+ str2 +"', 9048, 'test', 'qwer1011','北京', '高匿名')";
                String sql3="INSERT INTO IPManager( ip, port, username, password, ip_city, ip_type ) VALUES('"+ str3 +"', 9048, 'test', 'qwer1011','北京', '高匿名')";
                String sql4="INSERT INTO IPManager( ip, port, username, password, ip_city, ip_type ) VALUES('"+ str4 +"', 9048, 'test', 'qwer1011','北京', '高匿名')";
                st.addBatch(sql1);
                st.addBatch(sql2);
                st.addBatch(sql3);
                st.addBatch(sql4);
            }
            st.executeBatch();
            return true;
        }catch (Exception e){
            logger.error("插入单条IP出错，IP信息：{}",ipManager,e);
        }finally {
            MySQLClient.closeMySQLSR(st,null);
        }
        return false;
    }
    /**
      * @作者：LZY
      * @描述：//TODO  删除一条IP
      * @日期：2019/4/13 15:01
      **/
    public boolean deleteIp(String ip) {
        try {

        }catch (Exception e){
            logger.error("删除一条IP报错，IP信息:{}",ip,e);
        }
        return false;
    }
    /**
      * @作者：LZY
      * @描述：//TODO  插入多条IP
      * @日期：2019/4/13 15:02
      **/
    public boolean insertIpList(List<IPManager> ipList) {
        try {

        }catch (Exception e){
            logger.error("插入多条IP出错,ip信息：{}",ipList,e);
        }
        return false;
    }
    /**
      * @作者：LZY
      * @描述：//TODO  删除多条IP
      * @日期：2019/4/13 15:03
      **/
    public boolean deleteIpList(List<String> ipList) {
        try {

        }catch (Exception e){
            logger.error("删除多条IP报错,ip信息：{}",ipList,e);
        }
        return false;
    }
}
