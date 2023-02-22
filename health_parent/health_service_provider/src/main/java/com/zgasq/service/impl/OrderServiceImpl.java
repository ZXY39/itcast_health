package com.zgasq.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zgasq.constant.MessageConstant;
import com.zgasq.dao.MemberDao;
import com.zgasq.dao.OrderDao;
import com.zgasq.dao.OrderSettingDao;
import com.zgasq.entity.Result;
import com.zgasq.pojo.Member;
import com.zgasq.pojo.Order;
import com.zgasq.pojo.OrderSetting;
import com.zgasq.service.OrderService;
import com.zgasq.service.OrderSettingService;
import com.zgasq.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.activation.MailcapCommandMap;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Transactional
@Service(interfaceClass = OrderService.class)
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Autowired
    private MemberDao memberDao;
    @Autowired
    private OrderDao orderDao;
    @Override
    public Result order(Map map) throws Exception {
        String orderDate = (String) map.get("orderDate");
        OrderSetting orderSetting = orderSettingDao.findByOrderDate(DateUtils.parseString2Date(orderDate));
        if(orderSetting== null){
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }
        int number = orderSetting.getNumber();
        int reservations = orderSetting.getReservations();
        if(number <=reservations){
            return new Result(false,MessageConstant.ORDER_FULL);
        }

        String tele = (String) map.get("telephone");
        Member member = memberDao.findByTelephone(tele);
        if(member!=null){
            Integer memberId = member.getId();
            Date date = DateUtils.parseString2Date(orderDate);
            String setmealId = (String) map.get("setmealId");
            Order order =new Order(memberId,date,Integer.parseInt(setmealId));
            List<Order> byCondition = orderDao.findByCondition(order);
            if(byCondition !=null &&byCondition.size()>0){
                    return new Result(false,MessageConstant.HAS_ORDERED);
            }

        }else {
            member = new Member();
            member.setName((String) map.get("name"));
            member.setPhoneNumber(tele);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
            member.setRegTime(new Date());
            memberDao.add(member);
            memberDao.add(member);
        }

        Order order = new Order();
        order.setOrderDate(DateUtils.parseString2Date(orderDate));
        order.setMemberId(member.getId());
        order.setOrderType((String) map.get("orderType"));
        order.setOrderStatus(Order.ORDERSTATUS_NO);
        order.setSetmealId(Integer.parseInt((String) map.get("setmealId")));
        orderDao.add(order);
        orderSetting.setReservations(orderSetting.getReservations()+1);
        orderSettingDao.editReservationsByOrderDate(orderSetting);
        return new Result(true,MessageConstant.ORDER_SUCCESS,order.getId());


    }

    @Override
    public Map findById(Integer id) throws Exception {
        Map map = orderDao.findById4Detail(id);
        if(map != null){
            //处理日期格式
            Date orderDate = (Date) map.get("orderDate");
            map.put("orderDate",DateUtils.parseDate2String(orderDate));
        }
        return map;
    }
}
