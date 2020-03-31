package com.ss.ssdemo.controller.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ss
 * @site www.molink.com
 * @company 乐搜科技有限公司
 * @create 2020-03-17 14:11
 */
@Configuration
public class ShiroConfig {
    /**
          * 创建ShiroFilterFactoryBean
          */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean =new ShiroFilterFactoryBean();
//      设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加shiro内置过滤器，实现权限相关的url拦截
       /**
         * 常见过滤器：
         * anon：无需认证（登录）可以访问
         * authc：必须认证才可以访问
         * user:如果使用Remember Me的功能，可以直接访问
         * perms:该资源必须得到资源权限才可以访问
         * role:该资源必须得到角色权限才可以访问
         */
        Map<String,String> filterMap = new LinkedHashMap<String,String>();
//        filterMap.put("/add.html","authc");
//        filterMap.put("/update.html","anon");
//        filterMap.put("/toLogin.html", "anon");
//        filterMap.put("/login.html", "anon");
//
//        //授权过滤器：授权拦截后，shiro会自动跳转到未授权页面
//        filterMap.put("/add.html", "perms[user:add]");
//        filterMap.put("/update.html", "perms[user:update]");
//        filterMap.put("/*", "authc");
        filterMap.put("/*", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //修改跳转的登录页面，不加此项就会跳转到login.jsp页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin.html");
        //修改自动跳转的未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth.html");
        return shiroFilterFactoryBean;
    }

    /**
          * 创建DefaultWebSecurityManager
          */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getdefaultDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
          * 创建Realm
          */

    @Bean(name="userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    /**
     *
     *ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
