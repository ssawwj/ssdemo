package com.ss.ssdemo.service.impl;

import com.ss.ssdemo.dao.UserDao;
import com.ss.ssdemo.entity.User;
import com.ss.ssdemo.pojo.UserPojo;
import com.ss.ssdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ss
 * @site www.molink.com
 * @company 乐搜科技有限公司
 * @create 2020-03-17 16:48
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findByName(String username) {
        return userDao.findByName(username);
    }

    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public List<UserPojo> selectAll() {

        return  userDao.selectAll();
    }


}
