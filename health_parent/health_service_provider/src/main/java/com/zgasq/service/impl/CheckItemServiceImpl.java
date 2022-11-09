package com.zgasq.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zgasq.dao.CheckItemDao;
import com.zgasq.entity.PageResult;
import com.zgasq.entity.QueryPageBean;
import com.zgasq.pojo.CheckItem;
import com.zgasq.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> checkItems = checkItemDao.selectByCondition(queryString);
        long total = checkItems.getTotal();
        List<CheckItem> result = checkItems.getResult();

        return new PageResult(total,result);
    }

    @Override
    public void deleteById(Integer id) {
        long countByCheckItemId = checkItemDao.findCountByCheckItemId(id);
        if(countByCheckItemId>0){
           new RuntimeException();
        }
        checkItemDao.deleteById(id);

    }
}
