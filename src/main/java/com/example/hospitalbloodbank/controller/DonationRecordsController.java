package com.example.hospitalbloodbank.controller;

import com.example.hospitalbloodbank.common.CommonResponse;
import com.example.hospitalbloodbank.entity.BloodRequests;
import com.example.hospitalbloodbank.entity.DonationRecords;
import com.example.hospitalbloodbank.service.DonationRecordsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/donationRecords")
@Api(tags = "献血记录")
public class DonationRecordsController {

    @Autowired
    private DonationRecordsService donationRecordsService;

    // 获取所有献血记录
    @GetMapping
    @ApiOperation(value = "获取所有献血记录")
    public CommonResponse<?> getAllDonationRecords(int pageNum, int pageSize) {

        /**
         * PageHelper.startPage(pageNum, pageSize);
         *         List<BloodRequests> list = bloodRequestsService.list();
         *         PageInfo<BloodRequests> timeDtoPageInfo = new PageInfo<>(list);
         *         return CommonResponse.success(timeDtoPageInfo);
         */
        PageHelper.startPage(pageNum, pageSize);
        List<DonationRecords> list = donationRecordsService.list();
        PageInfo<DonationRecords> timeDtoPageInfo = new PageInfo<>(list);
        return CommonResponse.success(timeDtoPageInfo);
    }

    // 根据ID获取献血记录
    @GetMapping("/{recordID}")
    @ApiOperation(value = "根据ID获取献血记录")
    public CommonResponse<?> getDonationRecordById(@PathVariable Integer recordID) {
        return CommonResponse.success(donationRecordsService.getDonationRecordById(recordID));
    }

    // 创建献血记录
    /*@PostMapping
    @ApiOperation(value = "创建献血记录")
    public CommonResponse<?> createDonationRecord(@RequestBody DonationRecords donationRecords) {
        donationRecordsService.save(donationRecords);
        return CommonResponse.success(donationRecords);
    }*/

    // 更新献血记录
    /*@PutMapping("/{recordID}")
    @ApiOperation(value = "更新献血记录")
    public CommonResponse<?> updateDonationRecord(@PathVariable Integer recordID, @RequestBody DonationRecords donationRecords) {
        donationRecords.setRecordID(recordID);
        donationRecordsService.updateById(donationRecords);
        return CommonResponse.success(donationRecords);
    }*/

    // 删除献血记录
    @DeleteMapping("/{recordID}")
    @ApiOperation(value = "删除献血记录")
    public CommonResponse<?> deleteDonationRecord(@PathVariable Integer recordID) {
        donationRecordsService.removeById(recordID);
        return CommonResponse.success(null,"删除献血记录成功");
    }
}
