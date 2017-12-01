package com.highlander.shiro;

import com.highlander.entity.User;
import com.highlander.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roleName = userService.findRoles(user);
        Set<String> permissions = userService.findPermissions(user);
        info.setRoles(roleName);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String account = upToken.getUsername();
        char[] passCharArr = (char[]) upToken.getCredentials();
        String password = new String(passCharArr);
        User user = userService.findByUserAccountPassword(account, password);

        if (user == null) {
            throw new AccountException("帐号或密码不正确！");
        }

        String name2 = getName();
        System.out.println("getName():" + name2);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());

        Calendar in = Calendar.getInstance();
        in.set(2018, 0, 1);
        if (in.getTime().before(new Date())) {
            return null;
        }
        return info;
    }

}
