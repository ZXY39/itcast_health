package com.zgasq.dao;

import com.github.pagehelper.Page;
import com.zgasq.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    public void add(CheckGroup checkGroup);
    public void setCheckGroupAndCheckItem(Map map);

    public Page<CheckGroup> findByCondition(String queryString);

    public CheckGroup findById(Integer id);

    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    public void edit(CheckGroup checkGroup);

    public void deleteAssosiation(Integer id);

    public void deleteInCheckgroup(Integer id);


    public List<CheckGroup> findAll();

}
