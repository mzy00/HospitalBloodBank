package com.example.hospitalbloodbank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

/**
 * 存储献血记录
 * @TableName DonationRecords
 */
@TableName(value ="DonationRecords")
@Data
public class DonationRecords implements Serializable {
    /**
     * 献血记录ID
     */
    @TableId(value = "RecordID", type = IdType.AUTO)
    private Integer recordID;

    /**
     * 献血者ID
     */
    @TableField(value = "DonorID")
    private Integer donorID;

    /**
     * 献血日期
     */
    @TableField(value = "DonationDate")
    private LocalDate donationDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}