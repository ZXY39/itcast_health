package com.zgasq.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zgasq.dao.OrderSettingDao;
import com.zgasq.pojo.OrderSetting;
import com.zgasq.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService{
    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> data) {
        if(data!=null && data.size()>0){
            for (OrderSetting datum : data) {
                long countByOrderDate = orderSettingDao.findCountByOrderDate(datum.getOrderDate());
                if(countByOrderDate>0){
                    orderSettingDao.editNumberByOrderDate(datum);
                }else {
                    orderSettingDao.add(datum);
                }
            }
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String begin =date +"-1";
        String end =date +"-31";
        HashMap<String, String> map = new HashMap<>();
        map.put("begin",begin);
        map.put("end",end);
        List<OrderSetting> list  =orderSettingDao.getOrderSettingByMonth(map);
        List<Map> result =new ArrayList<>();
        if(list!=null &&list.size()>0){
            for(OrderSetting orderSetting:list){
                Map<String, Object> m =new HashMap<>();
                m.put("date",orderSetting.getOrderDate().getDate());
                m.put("number",orderSetting.getNumber());
                m.put("reservations",orderSetting.getReservations());
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        Date orderDate =orderSetting.getOrderDate();
        long countByOrderDate = orderSettingDao.findCountByOrderDate(orderDate);
        if(countByOrderDate>0){
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else {
            orderSettingDao.add(orderSetting);
        }
    }
}
