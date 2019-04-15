package com.botbrain.ai.bootone.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @描述：TODO
 * @作者：LZY
 * @日期：2019/4/13 15:52
 * @版本：1.0
 **/
public class JavaUtil {
    private static final Logger logger=LoggerFactory.getLogger(JavaUtil.class);
    public static Gson gson=new GsonBuilder()
            //支持Map的key为复杂对象的形式
            .setDateFormat("yyyy-MM-dd HH:mm:ss")//时间转化为特定格式
//            .setPrettyPrinting() //对json结果格式化.
            .setVersion(1.0)    //有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.
            .create();
}
