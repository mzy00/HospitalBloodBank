package com.example.hospitalbloodbank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 存储病人的基本信息
 * @TableName Patients
 */
@TableName(value ="Patients")
@Data
public class Patients implements Serializable {
    /**
     * 病人ID
     */
    @TableId(value = "PatientID", type = IdType.AUTO)
    private Integer patientID;

    /**
     * 病人姓名
     */
    @TableField(value = "Name")
    private String name;

    /**
     * 病人年龄
     */
    @TableField(value = "Age")
    private Integer age;

    /**
     * 病人性别
     */
    @TableField(value = "Gender")
    private String gender;

    /**
     * 联系方式
     */
    @TableField(value = "ContactNumber")
    private String contactNumber;

    /**
     * 病人血型
     */
    @TableField(value = "BloodType")
    private String bloodType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}