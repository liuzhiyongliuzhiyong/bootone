package com.botbrain.ai.bootone.controller;

import com.botbrain.ai.bootone.dao.mysql.MySQLClient;
import com.botbrain.ai.bootone.dao.mysql.MySQLIpDao;
import com.botbrain.ai.bootone.dao.mysql.MySQLIpImpl;
import com.botbrain.ai.bootone.entity.IPManager;
import com.botbrain.ai.bootone.util.JavaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @描述：TODO  IP列表请求的controller
 * @作者：LZY
 * @日期：2019/4/13 15:27
 * @版本：1.0
 **/
@CrossOrigin
@RestController
@RequestMapping(value = "/ip")
public class IpController {
    private static final Logger logger=LoggerFactory.getLogger(IpController.class);

    @GetMapping(value = "/getByPage")
    public static String getIpListByPage(@RequestParam(value = "pageIndex") Integer pageIndex){
        try {
            MySQLClient.getMysqlConn();
            MySQLIpDao mySQLIpDao=new MySQLIpImpl();
            List<IPManager> ipList=mySQLIpDao.queryByPage(pageIndex);
            logger.info(""+ipList);
            return JavaUtil.gson.toJson(ipList);
        }catch (Exception e){
            logger.error("Controller=》getIpListByPage报错，页面信息：{}",pageIndex,e);
        }
        return null;
    }
}
