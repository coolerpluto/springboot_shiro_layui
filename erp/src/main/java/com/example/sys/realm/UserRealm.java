package com.example.sys.realm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.sys.common.ActiverUser;
import com.example.sys.pojo.User;
import com.example.sys.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println(123);
        String principal = (String) authenticationToken.getPrincipal();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("loginname",principal);
        User user = userService.getOne(wrapper);
        if (!ObjectUtils.isEmpty(user)){
            ActiverUser activerUser = new ActiverUser();
            activerUser.setUser(user);
            ByteSource bytes = ByteSource.Util.bytes(user.getSalt());
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activerUser,user.getPwd(),bytes,this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }


}
