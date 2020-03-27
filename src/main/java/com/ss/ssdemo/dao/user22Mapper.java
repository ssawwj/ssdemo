package com.ss.ssdemo.dao;

import com.ss.ssdemo.entity.user22;

public interface user22Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(user22 record);

    int insertSelective(user22 record);

    user22 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(user22 record);

    int updateByPrimaryKey(user22 record);
}