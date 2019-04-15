package com.botbrain.ai.bootone;

import com.botbrain.ai.bootone.dao.mysql.MySQLClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class BootoneApplication {
    private static final Logger logger=LoggerFactory.getLogger(BootoneApplication.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context =SpringApplication.run(BootoneApplication.class, args);
        logger.info("jdbc.driver:"+context.getEnvironment().getProperty("jdbc.driver"));
        MySQLClient mySQLClient=new MySQLClient();
        mySQLClient.setEnv(context.getEnvironment());
        MySQLClient.getMysqlConn();
    }
}
