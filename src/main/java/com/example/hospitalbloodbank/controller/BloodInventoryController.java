package com.example.hospitalbloodbank.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.hospitalbloodbank.common.CommonResponse;
import com.example.hospitalbloodbank.entity.BloodInventory;
import com.example.hospitalbloodbank.service.BloodInventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/bloodInventory")
@Api(tags = "血库库存")
public class BloodInventoryController {

    @Autowired
    private BloodInventoryService bloodInventoryService;

    // 获取所有血库库存信息
    @GetMapping
    @ApiOperation(value = "获取所有血库库存信息")
    public CommonResponse<?> getAllBloodInventory() {
        return CommonResponse.success(bloodInventoryService.list());
    }

    // 根据ID获取血库库存信息
    @GetMapping("/{bloodID}")
    @ApiOperation(value = "根据ID获取血库库存信息")
    public CommonResponse<?> getBloodInventoryById(@PathVariable Integer bloodID) {
        return CommonResponse.success(bloodInventoryService.getBloodInventoryById(bloodID));
    }

    // 创建血库库存信息
    /*@PostMapping
    @ApiOperation(value = "创建血库库存信息")
    public CommonResponse<?> createBloodInventory(@RequestBody BloodInventory bloodInventory) {
        bloodInventoryService.save(bloodInventory);
        return CommonResponse.success(bloodInventory);
    }*/

    // 更新血库库存信息
    @PutMapping("/updateBloodInventory")
    @ApiOperation(value = "更新血库库存信息")
    public CommonResponse<?> updateBloodInventory(@RequestBody BloodInventory bloodInventory) {

        /**
         * if (bloodInventory.getQuantity() >= requestsQuantity) {
         *             bloodRequests.setStatus("申请成功");
         *             // 构造更新条件
         *             LambdaUpdateWrapper<BloodInventory> updateWrapper = Wrappers.lambdaUpdate();
         *             updateWrapper.eq(BloodInventory::getBloodType, bloodType);
         *             updateWrapper.setSql("quantity = quantity - " + requestsQuantity);
         *             boolean update = bloodInventoryService.update(updateWrapper);
         *             if (update) {
         *                 bloodRequestsService.save(bloodRequests);
         *                 return CommonResponse.success(bloodRequests);
         *             }
         *         }
         */

        LambdaUpdateWrapper<BloodInventory> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.eq(BloodInventory::getBloodType, bloodInventory.getBloodType());
        updateWrapper.setSql("quantity = " + bloodInventory.getQuantity());
        boolean update = bloodInventoryService.update(updateWrapper);
        if (update) {
            return CommonResponse.success(bloodInventory);
        }
        return CommonResponse.fail("更新血库库存信息失败，请重试（血型为 A B AB O）");
    }

    // 删除血库库存信息
    /*@DeleteMapping("/{bloodID}")
    @ApiOperation(value = "删除血库库存信息")
    public CommonResponse<?> deleteBloodInventory(@PathVariable Integer bloodID) {
        bloodInventoryService.removeById(bloodID);
        return CommonResponse.success(null,"删除血库库存信息成功");
    }*/
}
