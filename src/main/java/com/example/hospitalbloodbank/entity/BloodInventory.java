package com.example.hospitalbloodbank.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

/**
 * 存储血液库存信息
 * @TableName BloodInventory
 */
@TableName(value ="BloodInventory")
@Data
public class BloodInventory implements Serializable {
    /**
     * 血液ID
     */
    @TableId(value = "BloodID", type = IdType.AUTO)
    private Integer bloodID;

    /**
     * 血型
     */
    @TableField(value = "BloodType")
    private String bloodType;

    /**
     * 库存数量
     */
    @TableField(value = "Quantity")
    private Integer quantity;

    /**
     * 更新日期
     */
    @TableField(value = "ExpiryDate")
    private LocalDate expiryDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
