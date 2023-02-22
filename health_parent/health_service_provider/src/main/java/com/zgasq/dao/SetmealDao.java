package com.zgasq.dao;


import com.github.pagehelper.Page;
import com.zgasq.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    public void add(Setmeal setmeal);
    public void setChecksetmealAndCheckGroup(Map map);

    Page<Setmeal> findByCondition(String queryString);

    Setmeal findById(Integer id);

    void edit(Setmeal setmeal);

    List<Setmeal> findAll();

    List<Map<String, Object>> findSetmealCount();
}
