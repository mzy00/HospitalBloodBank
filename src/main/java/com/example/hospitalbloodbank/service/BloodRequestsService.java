package com.example.hospitalbloodbank.service;

import com.example.hospitalbloodbank.entity.BloodRequests;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author mazeyuan
* @description 针对表【BloodRequests(存储用血申请信息)】的数据库操作Service
* @createDate 2023-11-19 09:00:15
*/
public interface BloodRequestsService extends IService<BloodRequests> {

    BloodRequests getBloodRequestById(Integer requestID);

    BloodRequests createBloodRequest(BloodRequests bloodRequests);

    BloodRequests updateBloodRequest(Integer requestID, BloodRequests bloodRequests);

    void deleteBloodRequest(Integer requestID);

}
