package com.zgasq.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zgasq.dao.MemberDao;
import com.zgasq.dao.OrderDao;
import com.zgasq.service.ReportService;
import com.zgasq.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = ReportService.class)
public class ReportServiceImpl implements ReportService {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private OrderDao orderDao;
    @Override
    public Map<String, Object> getBusinessReportData() throws Exception {
        Map<String, Object> result =new HashMap<>();
        String today= DateUtils.parseDate2String(DateUtils.getToday());
        String thisWeekMonday= DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
        String firstDay4ThisMonth= DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());

        Integer todayNewMember = memberDao.findMemberCountByDate(today);

        Integer totalMember =memberDao.findMemberTotalCount();
        Integer thisWeekNewMember = memberDao.findMemberCountAfterDate(thisWeekMonday);
        Integer thisMonthNewMember = memberDao.findMemberCountAfterDate(firstDay4ThisMonth);

//今日预约数
        Integer todayOrderNumber = orderDao.findOrderCountByDate(today);

        //本周预约数
        Integer thisWeekOrderNumber = orderDao.findOrderCountAfterDate(thisWeekMonday);

        //本月预约数
        Integer thisMonthOrderNumber = orderDao.findOrderCountAfterDate(firstDay4ThisMonth);

        //今日到诊数
        Integer todayVisitsNumber = orderDao.findVisitsCountByDate(today);

        //本周到诊数
        Integer thisWeekVisitsNumber = orderDao.findVisitsCountAfterDate(thisWeekMonday);

        //本月到诊数
        Integer thisMonthVisitsNumber = orderDao.findVisitsCountAfterDate(firstDay4ThisMonth);

        List<Map> hotSetmeal = orderDao.findHotSetmeal();
        result.put("reportDate",today);
        result.put("todayNewMember",todayNewMember);
        result.put("totalMember",totalMember);
        result.put("thisWeekNewMember",thisWeekNewMember);
        result.put("thisMonthNewMember",thisMonthNewMember);
        result.put("todayOrderNumber",todayOrderNumber);
        result.put("thisWeekOrderNumber",thisWeekOrderNumber);
        result.put("thisMonthOrderNumber",thisMonthOrderNumber);
        result.put("todayVisitsNumber",todayVisitsNumber);
        result.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
        result.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
        result.put("hotSetmeal",hotSetmeal);
        return result;
    }
}
