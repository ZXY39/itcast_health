package com.zgasq.service;


import com.zgasq.entity.PageResult;
import com.zgasq.entity.QueryPageBean;
import com.zgasq.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    public void add(CheckGroup checkGroup, Integer[] checkitemIds);


    public PageResult pageQuery(QueryPageBean queryPageBean);

    public CheckGroup findById(Integer id);

    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    public void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    public void delete(Integer id);

    public List<CheckGroup> findAll();


}
