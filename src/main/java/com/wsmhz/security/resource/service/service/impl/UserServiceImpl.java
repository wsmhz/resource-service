package com.wsmhz.security.resource.service.service.impl;


import com.wsmhz.common.business.service.impl.BaseServiceImpl;

import com.wsmhz.security.resource.service.domain.entity.User;
import com.wsmhz.security.resource.service.mapper.UserMapper;

import com.wsmhz.security.resource.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * create by tangbj on 2018/5/21
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByUsername(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        return (User) this.userMapper.selectOneByExample(example);
    }
}
