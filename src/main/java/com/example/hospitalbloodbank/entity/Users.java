package com.example.hospitalbloodbank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 存储系统用户信息
 * @TableName Users
 */
@TableName(value ="Users")
@Data
public class Users implements Serializable {
    /**
     * 用户ID
     */
    @TableId(value = "UserID", type = IdType.AUTO)
    private Integer userID;

    /**
     * 用户名
     */
    @TableField(value = "Username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "Password")
    private String password;

    /**
     * 用户角色
     */
    @TableField(value = "Role")
    private String role;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}