package com.zgasq.service;

import com.zgasq.pojo.Member;

import java.util.List;

public interface MemberService {
   public Member findByTelephone(String telephone);
   public void add(Member member);
   public List<Integer> findMemberCountByMonths(List<String> months);
}
