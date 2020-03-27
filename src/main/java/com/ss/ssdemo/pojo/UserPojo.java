package com.ss.ssdemo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class UserPojo extends BaseRowModel {
    @ExcelProperty(value="ID",index=0)
    private Integer id;
    @ExcelProperty(value="用户名",index=1)
    private String username;
    @ExcelProperty(value="密码",index=2)
    private String password;
    @ExcelProperty(value="权限",index=3)
    private String perms;
}
