package com.zgasq.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zgasq.constant.MessageConstant;
import com.zgasq.entity.Result;
import com.zgasq.pojo.Setmeal;
import com.zgasq.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    @RequestMapping("/getAllSetmeal")
    public Result getAllSetmeal(){
        try{
            List<Setmeal> list= setmealService.findAll();
            return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS,list);

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_SETMEAL_LIST_FAIL);
        }
    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            Setmeal setmeal = setmealService.findById(id);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }
}
