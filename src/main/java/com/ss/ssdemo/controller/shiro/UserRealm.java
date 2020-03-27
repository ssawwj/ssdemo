package com.ss.ssdemo.controller.shiro;

import com.ss.ssdemo.entity.User;
import com.ss.ssdemo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ss
 * @site www.molink.com
 * @company 乐搜科技有限公司
 * @create 2020-03-17 14:05
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //添加资源的授权字符串
//        info.addStringPermission("user:add");
        //获取当前用户
        Subject subject= SecurityUtils.getSubject();
        User user=(User)subject.getPrincipal();
        System.out.println("id===="+user.getId());
        //到数据库查询当前登录用户的授权字符串
        User dbUser=userService.findById(user.getId());//通过当前登录用户id查找的数据库用户
        info.addStringPermission(dbUser.getPerms());
        return info;
    }

    /**
     * 认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //先写模拟数据进行验证，下一步再连接数据库，假设数据库的用户名和密码如下
//        String dbusername="shu";
//        String dbpassword="123456";
        //编写shiro判断逻辑，判断用户名和密码
        System.out.println(authenticationToken);

         //1. 判断用户名
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        User user = userService.findByName(token.getUsername());
        if (user==null) {
        //用户名不存在
        return null;//shiro底层会抛出UnknownAccountException
        }
        //2. 判断密码
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");//参数1：需要返回给login方法的数据；参数2：数据库密码，shiro会自动判断
    }
}
