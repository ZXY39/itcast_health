package com.zgasq.controller;

import com.zgasq.constant.MessageConstant;
import com.zgasq.constant.RedisMessageConstant;
import com.zgasq.entity.Result;
import com.zgasq.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/sendForOrder")
    public Result send4Order(String telephone){
        Integer validateCode = ValidateCodeUtils.generateValidateCode(4);
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_ORDER,300,validateCode.toString());
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS,validateCode);
    }

    @RequestMapping("/sendForLogin")
    public Result sendForLogin(String telephone){
        Integer validateCode = ValidateCodeUtils.generateValidateCode(6);
        jedisPool.getResource().setex(telephone+ RedisMessageConstant.SENDTYPE_LOGIN,300,validateCode.toString());
        return new Result(true, MessageConstant.SEND_VALIDATECODE_SUCCESS,validateCode);
    }
}
