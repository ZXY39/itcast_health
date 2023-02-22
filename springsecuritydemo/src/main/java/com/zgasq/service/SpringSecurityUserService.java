package com.zgasq.service;

import com.zgasq.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpringSecurityUserService implements UserDetailsService {

    public  static Map<String, User> map = new HashMap<>();
    static {
        com.zgasq.pojo.User user1 = new com.zgasq.pojo.User();
        user1.setUsername("admin");
        user1.setPassword("admin");

        com.zgasq.pojo.User user2 = new com.zgasq.pojo.User();
        user2.setUsername("xiaoming");
        user2.setPassword("1234");

        map.put(user1.getUsername(),user1);
        map.put(user2.getUsername(),user2);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= map.get(username);
        if(user ==null){

            return null;
        }else {
            List<GrantedAuthority> list =new ArrayList<>();
            list.add(new SimpleGrantedAuthority("add"));
            list.add(new SimpleGrantedAuthority("delete"));
            list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            org.springframework.security.core.userdetails.User securityUser =
                    new org.springframework.security.core.userdetails.User(username,"{noop}"+user.getPassword(),list);
            return securityUser;
        }
    }
}
