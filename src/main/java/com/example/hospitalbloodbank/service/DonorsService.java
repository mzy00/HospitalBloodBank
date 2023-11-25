package com.example.hospitalbloodbank.service;

import com.example.hospitalbloodbank.entity.Donors;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author mazeyuan
* @description 针对表【Donors(存储献血者的信息)】的数据库操作Service
* @createDate 2023-11-19 09:00:15
*/
public interface DonorsService extends IService<Donors> {


    Donors getDonorById(Integer donorID);

    Donors createDonor(Donors donors);

    Donors updateDonor(Integer donorID, Donors donors);

    void deleteDonor(Integer donorID);
}
