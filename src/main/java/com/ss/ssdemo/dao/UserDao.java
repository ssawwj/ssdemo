package com.ss.ssdemo.dao;

import com.ss.ssdemo.entity.User;
import com.ss.ssdemo.pojo.UserPojo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ss
 * @site www.molink.com
 * @company 乐搜科技有限公司
 * @create 2020-03-17 16:41
 */
public interface UserDao {

    public User findByName(String username);

    public User findById(Integer id);

    @Select("select * from user")
    List<UserPojo> selectAll();
}
