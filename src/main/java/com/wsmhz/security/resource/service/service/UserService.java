package com.wsmhz.security.resource.service.service;

import com.wsmhz.common.business.service.BaseService;
import com.wsmhz.security.resource.service.domain.entity.User;

/**
 * create by tangbj on 2018/5/21
 */
public interface UserService extends BaseService<User> {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User selectByUsername(String username);
}
