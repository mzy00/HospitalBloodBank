package com.example.hospitalbloodbank.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.hospitalbloodbank.common.CommonResponse;
import com.example.hospitalbloodbank.entity.BloodInventory;
import com.example.hospitalbloodbank.entity.BloodRequests;
import com.example.hospitalbloodbank.entity.DonationRecords;
import com.example.hospitalbloodbank.entity.Donors;
import com.example.hospitalbloodbank.service.BloodInventoryService;
import com.example.hospitalbloodbank.service.DonationRecordsService;
import com.example.hospitalbloodbank.service.DonorsService;
import com.example.hospitalbloodbank.utils.RegexUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/donors")
@Api(tags = "献血者登记信息")
public class DonorsController {

    @Autowired
    private DonorsService donorsService;

    @Autowired
    private BloodInventoryService bloodInventoryService;

    @Autowired
    private DonationRecordsService donationRecordsService;

    // 获取所有献血者信息
    @GetMapping
    @ApiOperation(value = "获取所有献血者信息")
    public CommonResponse<?> getAllDonors(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Donors> list = donorsService.list();
        PageInfo<Donors> timeDtoPageInfo = new PageInfo<>(list);
        return CommonResponse.success(timeDtoPageInfo);
    }

    // 根据ID获取献血者信息
    @GetMapping("/{donorID}")
    @ApiOperation(value = "根据ID获取献血者信息")
    public CommonResponse<?> getDonorById(@PathVariable Integer donorID) {
        return CommonResponse.success(donorsService.getDonorById(donorID));
    }

    // 创建献血者信息
    @PostMapping
    @ApiOperation(value = "创建献血者信息")
    @Transactional
    public CommonResponse<?> createDonor(@RequestBody Donors donors) {
        if (RegexUtils.isPhoneInvalid(donors.getContactNumber())) {
            return CommonResponse.fail("请输入正确手机号");
        }
        // 获取性别
        String gender = donors.getGender();
        if (!gender.equals("男") && !gender.equals("女")) {
            return CommonResponse.fail("请输入正确性别（如 男 女）");
        }
        // 获取血型
        String bloodType = donors.getBloodType();
        if (!bloodType.equals("A") && !bloodType.equals("B") && !bloodType.equals("AB") && !bloodType.equals("O")) {
            return CommonResponse.fail("请输入正确血型（如 A B AB O）");
        }
        donorsService.save(donors);
        // 构造更新条件
        LambdaUpdateWrapper<BloodInventory> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(BloodInventory::getBloodType, bloodType);
        updateWrapper.setSql("quantity = quantity + 1");
        boolean update = bloodInventoryService.update(updateWrapper);
        if (update) {
            DonationRecords donationRecords = new DonationRecords();
            donationRecords.setDonorID(donors.getDonorID());
            donationRecordsService.save(donationRecords);
            return CommonResponse.success(donors);
        }
        return CommonResponse.fail("创建失败");
    }

    // 更新献血者信息
    @PutMapping("/{donorID}")
    @ApiOperation(value = "更新献血者信息")
    @Transactional
    public CommonResponse<?> updateDonor(@PathVariable Integer donorID, @RequestBody Donors donors) {
        if (RegexUtils.isPhoneInvalid(donors.getContactNumber())) {
            return CommonResponse.fail("请输入正确手机号");
        }
        // 获取性别
        String gender = donors.getGender();
        if (!gender.equals("男") && !gender.equals("女")) {
            return CommonResponse.fail("请输入正确性别（如 男 女）");
        }
        // 获取血型
        String bloodType = donors.getBloodType();
        if (!bloodType.equals("A") && !bloodType.equals("B") && !bloodType.equals("AB") && !bloodType.equals("O")) {
            return CommonResponse.fail("请输入正确血型（如 A B AB O）");
        }
        donors.setDonorID(donorID);
        donorsService.updateById(donors);
        return CommonResponse.success(donors);
    }

    // 删除献血者信息
    @ApiOperation(value = "删除献血者信息")
    @DeleteMapping("/{donorID}")
    public CommonResponse<?> deleteDonor(@PathVariable Integer donorID) {
        donorsService.removeById(donorID);
        return CommonResponse.success(null, "删除献血者信息成功");
    }
}
