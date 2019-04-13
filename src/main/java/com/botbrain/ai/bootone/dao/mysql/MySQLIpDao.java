package com.botbrain.ai.bootone.dao.mysql;

import com.botbrain.ai.bootone.entity.IPManager;

import java.util.List;

/**
 * @描述：TODO  增删改查IP的接口
 * @作者：LZY
 * @日期：2019/4/13 14:23
 * @版本：1.0
 **/
public interface MySQLIpDao {
    //根据页数获取IP
    List<IPManager> queryByPage(int page);
    //插入一条IP
    boolean insertIp(IPManager ipManager);
    //根据IP删除整条数据
    boolean deleteIp(String ip);
    //插入多条IP
    boolean insertIpList(List<IPManager> ipList);
    //删除多条IP
    boolean deleteIpList(List<String> ipList);
}
