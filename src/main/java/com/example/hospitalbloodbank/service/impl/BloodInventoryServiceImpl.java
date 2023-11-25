package com.example.hospitalbloodbank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hospitalbloodbank.entity.BloodInventory;
import com.example.hospitalbloodbank.service.BloodInventoryService;
import com.example.hospitalbloodbank.mapper.BloodInventoryMapper;
import org.springframework.stereotype.Service;

/**
* @author mazeyuan
* @description 针对表【BloodInventory(存储血液库存信息)】的数据库操作Service实现
* @createDate 2023-11-19 09:00:15
*/
@Service
public class BloodInventoryServiceImpl extends ServiceImpl<BloodInventoryMapper, BloodInventory>
    implements BloodInventoryService{

    @Override
    public BloodInventory getBloodInventoryById(Integer bloodID) {
        return this.getById(bloodID);
    }

    @Override
    public BloodInventory createBloodInventory(BloodInventory bloodInventory) {
        this.save(bloodInventory);
        return bloodInventory;
    }

    @Override
    public BloodInventory updateBloodInventory(Integer bloodID, BloodInventory bloodInventory) {
        bloodInventory.setBloodID(bloodID);
        this.updateById(bloodInventory);
        return bloodInventory;
    }

    @Override
    public void deleteBloodInventory(Integer bloodID) {
        this.removeById(bloodID);
    }

}




