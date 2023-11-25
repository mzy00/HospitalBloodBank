package com.example.hospitalbloodbank.service;

import com.example.hospitalbloodbank.entity.BloodInventory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author mazeyuan
* @description 针对表【BloodInventory(存储血液库存信息)】的数据库操作Service
* @createDate 2023-11-19 09:00:15
*/
public interface BloodInventoryService extends IService<BloodInventory> {


    BloodInventory getBloodInventoryById(Integer bloodID);

    BloodInventory createBloodInventory(BloodInventory bloodInventory);

    BloodInventory updateBloodInventory(Integer bloodID, BloodInventory bloodInventory);

    void deleteBloodInventory(Integer bloodID);
}
