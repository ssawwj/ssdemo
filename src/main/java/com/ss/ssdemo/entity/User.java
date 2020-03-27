package com.ss.ssdemo.entity;

import lombok.Data;

/**
 * @author ss
 * @site www.molink.com
 * @company 乐搜科技有限公司
 * @create 2020-03-17 16:29
 */
@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private String perms;
}
