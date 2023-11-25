package com.example.hospitalbloodbank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

/**
 * 存储用血申请信息
 * @TableName BloodRequests
 */
@TableName(value ="BloodRequests")
@Data
public class BloodRequests implements Serializable {
    /**
     * 申请ID
     */
    @TableId(value = "RequestID", type = IdType.AUTO)
    private Integer requestID;

    /**
     * 病人ID
     */
    @TableField(value = "PatientID")
    private Integer patientID;

    /**
     * 申请血型
     */
    @TableField(value = "BloodType")
    private String bloodType;

    /**
     * 申请数量
     */
    @TableField(value = "Quantity")
    private Integer quantity;

    /**
     * 申请日期
     */
    @TableField(value = "RequestDate")
    private LocalDate requestDate;

    /**
     * 申请状态
     */
    @TableField(value = "Status")
    private String status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}