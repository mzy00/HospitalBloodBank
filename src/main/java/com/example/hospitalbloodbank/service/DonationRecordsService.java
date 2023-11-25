package com.example.hospitalbloodbank.service;

import com.example.hospitalbloodbank.entity.DonationRecords;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author mazeyuan
* @description 针对表【DonationRecords(存储献血记录)】的数据库操作Service
* @createDate 2023-11-19 09:00:15
*/
public interface DonationRecordsService extends IService<DonationRecords> {

    DonationRecords getDonationRecordById(Integer recordID);

    DonationRecords createDonationRecord(DonationRecords donationRecords);

    DonationRecords updateDonationRecord(Integer recordID, DonationRecords donationRecords);

    void deleteDonationRecord(Integer recordID);

}
