package com.ss.ssdemo.service;

import com.ss.ssdemo.entity.User;
import com.ss.ssdemo.pojo.UserPojo;

import java.util.List;

/**
 * @author ss
 * @site www.molink.com
 * @company 乐搜科技有限公司
 * @create 2020-03-17 16:46
 */

public interface UserService {

    public User findByName(String username);

    public User findById(Integer id);

    List<UserPojo> selectAll();
}
