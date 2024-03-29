package com.zgasq.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.zgasq.dao.MemberDao;
import com.zgasq.pojo.Member;
import com.zgasq.service.MemberService;
import com.zgasq.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;
    @Override
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);

    }

    @Override
    public void add(Member member) {
        String password =member.getPassword();
        if(password!=null){
            password= MD5Utils.md5(password);
            member.setPassword(password);
        }
        memberDao.add(member);
    }

    @Override
    public List<Integer> findMemberCountByMonths(List<String> months) {
        List<Integer> memberCount =new ArrayList<>();
        for(String month:months){
            String date =month+".31";
            Integer memberCountBeforeDate = memberDao.findMemberCountBeforeDate(date);
            memberCount.add(memberCountBeforeDate);
        }
        return memberCount;
    }
}
