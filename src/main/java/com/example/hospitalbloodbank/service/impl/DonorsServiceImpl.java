package com.example.hospitalbloodbank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hospitalbloodbank.entity.Donors;
import com.example.hospitalbloodbank.service.DonorsService;
import com.example.hospitalbloodbank.mapper.DonorsMapper;
import org.springframework.stereotype.Service;

/**
* @author mazeyuan
* @description 针对表【Donors(存储献血者的信息)】的数据库操作Service实现
* @createDate 2023-11-19 09:00:15
*/
@Service
public class DonorsServiceImpl extends ServiceImpl<DonorsMapper, Donors>
    implements DonorsService{

    @Override
    public Donors getDonorById(Integer donorID) {
        return this.getById(donorID);
    }

    @Override
    public Donors createDonor(Donors donors) {
        this.save(donors);
        return donors;
    }

    @Override
    public Donors updateDonor(Integer donorID, Donors donors) {
        donors.setDonorID(donorID);
        this.updateById(donors);
        return donors;
    }

    @Override
    public void deleteDonor(Integer donorID) {
        this.removeById(donorID);
    }

}




