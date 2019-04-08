package com.botbrain.ai.bootone.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @描述：TODO
 * @作者：LZY
 * @日期：2019/3/22 16:42
 * @版本：1.0
 **/
@RestController
@RequestMapping("/hello")
public class TestController {
    @RequestMapping("/boot")
    public Object helloWorld(){
        return "第一个Spring-boot";
    }
}
