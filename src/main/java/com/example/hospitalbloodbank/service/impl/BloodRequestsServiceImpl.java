package com.example.hospitalbloodbank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hospitalbloodbank.entity.BloodRequests;
import com.example.hospitalbloodbank.service.BloodRequestsService;
import com.example.hospitalbloodbank.mapper.BloodRequestsMapper;
import org.springframework.stereotype.Service;

/**
* @author mazeyuan
* @description 针对表【BloodRequests(存储用血申请信息)】的数据库操作Service实现
* @createDate 2023-11-19 09:00:15
*/
@Service
public class BloodRequestsServiceImpl extends ServiceImpl<BloodRequestsMapper, BloodRequests>
    implements BloodRequestsService{

    @Override
    public BloodRequests getBloodRequestById(Integer requestID) {
        return this.getById(requestID);
    }

    @Override
    public BloodRequests createBloodRequest(BloodRequests bloodRequests) {
        this.save(bloodRequests);
        return bloodRequests;
    }

    @Override
    public BloodRequests updateBloodRequest(Integer requestID, BloodRequests bloodRequests) {
        bloodRequests.setRequestID(requestID);
        this.updateById(bloodRequests);
        return bloodRequests;
    }

    @Override
    public void deleteBloodRequest(Integer requestID) {
        this.removeById(requestID);
    }

}




