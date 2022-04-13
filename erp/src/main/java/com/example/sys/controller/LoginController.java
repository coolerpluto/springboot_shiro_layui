package com.example.sys.controller;

import com.example.sys.common.ActiverUser;
import com.example.sys.common.ResultObj;
import com.example.sys.common.WebUtils;
import com.example.sys.pojo.LogLogin;
import com.example.sys.service.LogLoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RequestMapping("login")
@RestController
public class LoginController {

    @Autowired
    LogLoginService logLoginService;

    @RequestMapping("login")
    public ResultObj login(String loginname, String pwd){
        UsernamePasswordToken token = new UsernamePasswordToken(loginname, pwd);
        //获得主体
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            ActiverUser activerUser = (ActiverUser) subject.getPrincipal();
            //把user放到session里面方便前端调用相关数据
            WebUtils.getSession().setAttribute("user", activerUser.getUser());
            //记录登录信息存入到logLogin日志表
            LogLogin logLogin = new LogLogin();
            logLogin.setLoginname(activerUser.getUser().getName()+"-"+activerUser.getUser().getLoginname());
            logLogin.setLoginip(WebUtils.getRequest().getRemoteAddr());
            logLogin.setLogintime(new Date());
            logLoginService.save(logLogin);
            //向前端一个ajax返回数据，通过判断code是不是200来判断是否登录成功
            return ResultObj.LOGIN_SUCCESS;
        }catch (AuthenticationException e){
            e.printStackTrace();
            //向前端一个ajax返回数据，通过判断code是不是200来判断是否登录成功
            return ResultObj.LOGIN_ERROR_PASS;
        }
    }
}
