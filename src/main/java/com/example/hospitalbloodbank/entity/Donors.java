package com.example.hospitalbloodbank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 存储献血者的信息
 * @TableName Donors
 */
@TableName(value ="Donors")
@Data
public class Donors implements Serializable {
    /**
     * 献血者ID
     */
    @TableId(value = "DonorID", type = IdType.AUTO)
    private Integer donorID;

    /**
     * 献血者姓名
     */
    @TableField(value = "Name")
    private String name;

    /**
     * 献血者年龄
     */
    @TableField(value = "Age")
    private Integer age;

    /**
     * 献血者性别
     */
    @TableField(value = "Gender")
    private String gender;

    /**
     * 联系方式
     */
    @TableField(value = "ContactNumber")
    private String contactNumber;

    /**
     * 献血者血型
     */
    @TableField(value = "BloodType")
    private String bloodType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}