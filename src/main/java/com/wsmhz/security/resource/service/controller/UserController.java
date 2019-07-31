package com.wsmhz.security.resource.service.controller;

import com.wsmhz.common.business.annotation.RequestLimit;
import com.wsmhz.common.business.annotation.UnAuth;
import com.wsmhz.common.business.response.ServerResponse;
import com.wsmhz.common.business.utils.DozerBeanUtil;
import com.wsmhz.security.resource.service.api.api.UserApi;
import com.wsmhz.security.resource.service.api.domain.form.UserRegisterForm;
import com.wsmhz.security.resource.service.api.domain.form.UsernameForm;
import com.wsmhz.security.resource.service.api.domain.vo.LoginUserVo;
import com.wsmhz.security.resource.service.api.domain.vo.UserInfoVo;
import com.wsmhz.security.resource.service.domain.entity.User;
import com.wsmhz.security.resource.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created By TangBiJing On 2019/5/11
 * Description:
 */
@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public ServerResponse test() {
        return ServerResponse.createBySuccessMessage("test-success");
    }

    @GetMapping("/userInfo")
    public ServerResponse getUserInfo(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        UserInfoVo userInfoVo = DozerBeanUtil.map(selectByUsername(UsernameForm.builder().username(username).build()), UserInfoVo.class);
        return ServerResponse.createBySuccess(userInfoVo);
    }

//    ************************** 服务接口 ************************

    @Override
    @RequestLimit
    @UnAuth
    @PostMapping("/select")
    public LoginUserVo selectByUsername(@RequestBody @Valid UsernameForm usernameForm) {
        LoginUserVo loginUserVo = DozerBeanUtil.map(userService.selectByUsername(usernameForm.getUsername()), LoginUserVo.class);
        return loginUserVo;
    }

    @Override
    @RequestLimit
    @UnAuth
    @PostMapping("/insert")
    public boolean insert(@RequestBody @Valid UserRegisterForm userRegisterForm) {
        int result = userService.insertSelective(DozerBeanUtil.map(userRegisterForm, User.class));
        return result > 0;
    }

}
