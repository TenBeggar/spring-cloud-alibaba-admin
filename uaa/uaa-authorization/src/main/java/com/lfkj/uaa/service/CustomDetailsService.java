package com.lfkj.uaa.service;

import com.lfkj.uaa.domain.CustomDetails;
import com.lfkj.uaa.domain.User;
import com.lfkj.uaa.mapper.UserMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

@Service
public class CustomDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        Assert.notNull(user, "账号或密码错误");
        return new CustomDetails(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList("ADMIN"));
    }
}
