package com.example.hospitalbloodbank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hospitalbloodbank.entity.DonationRecords;
import com.example.hospitalbloodbank.service.DonationRecordsService;
import com.example.hospitalbloodbank.mapper.DonationRecordsMapper;
import org.springframework.stereotype.Service;

/**
* @author mazeyuan
* @description 针对表【DonationRecords(存储献血记录)】的数据库操作Service实现
* @createDate 2023-11-19 09:00:15
*/
@Service
public class DonationRecordsServiceImpl extends ServiceImpl<DonationRecordsMapper, DonationRecords>
    implements DonationRecordsService{

    @Override
    public DonationRecords getDonationRecordById(Integer recordID) {
        return this.getById(recordID);
    }

    @Override
    public DonationRecords createDonationRecord(DonationRecords donationRecords) {
        this.save(donationRecords);
        return donationRecords;
    }

    @Override
    public DonationRecords updateDonationRecord(Integer recordID, DonationRecords donationRecords) {
        donationRecords.setRecordID(recordID);
        this.updateById(donationRecords);
        return donationRecords;
    }

    @Override
    public void deleteDonationRecord(Integer recordID) {
        this.removeById(recordID);
    }

}




