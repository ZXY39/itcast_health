package com.zgasq.service;

import com.zgasq.entity.PageResult;
import com.zgasq.entity.QueryPageBean;
import com.zgasq.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {
    public void add(Integer[] checkgroupIds, Setmeal setmeal);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public Setmeal findById(Integer id);

    public void edit(Integer[] checkgroupIds, Setmeal setmeal);

    public List<Setmeal> findAll();


    List<Map<String, Object>> findSetmealCount();
}
