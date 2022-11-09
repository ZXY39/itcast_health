package com.zgasq.dao;

import com.github.pagehelper.Page;
import com.zgasq.entity.PageResult;
import com.zgasq.pojo.CheckItem;

public interface CheckItemDao {
    public void add(CheckItem checkItem);
    public Page<CheckItem> selectByCondition(String queryString);
    public long findCountByCheckItemId(Integer id);
    public void deleteById(Integer id);
}
