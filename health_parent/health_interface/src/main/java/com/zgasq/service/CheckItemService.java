package com.zgasq.service;

import com.zgasq.entity.PageResult;
import com.zgasq.entity.QueryPageBean;
import com.zgasq.pojo.CheckItem;

public interface CheckItemService {

    public void add(CheckItem checkItem);

    public PageResult pageQuery(QueryPageBean queryPageBean);


    public void deleteById(Integer id);
}
