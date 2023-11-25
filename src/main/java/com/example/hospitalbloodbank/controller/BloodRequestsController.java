package com.example.hospitalbloodbank.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.hospitalbloodbank.common.CommonResponse;
import com.example.hospitalbloodbank.entity.BloodInventory;
import com.example.hospitalbloodbank.entity.BloodRequests;
import com.example.hospitalbloodbank.entity.Users;
import com.example.hospitalbloodbank.mapper.BloodInventoryMapper;
import com.example.hospitalbloodbank.service.BloodInventoryService;
import com.example.hospitalbloodbank.service.BloodRequestsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/bloodRequests")
@Api(tags = "用血申请")
public class BloodRequestsController {

    @Autowired
    private BloodRequestsService bloodRequestsService;

    @Autowired
    private BloodInventoryMapper bloodInventoryMapper;

    @Autowired
    private BloodInventoryService bloodInventoryService;

    // 获取所有用血申请信息
    @GetMapping
    @ApiOperation(value = "获取所有用血申请信息")
    public CommonResponse<?> getAllBloodRequests(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<BloodRequests> list = bloodRequestsService.list();
        PageInfo<BloodRequests> timeDtoPageInfo = new PageInfo<>(list);
        return CommonResponse.success(timeDtoPageInfo);
    }

    // 根据ID获取用血申请信息
    @GetMapping("/{requestID}")
    @ApiOperation(value = "根据ID获取用血申请信息")
    public CommonResponse<?> getBloodRequestById(@PathVariable Integer requestID) {
        return CommonResponse.success(bloodRequestsService.getBloodRequestById(requestID));
    }

    // 创建用血申请信息
    @PostMapping
    @ApiOperation(value = "创建用血申请信息")
    public CommonResponse<?> createBloodRequest(@RequestBody BloodRequests bloodRequests) {
        // 获取用血申请信息的血型和库存数量
        String bloodType = bloodRequests.getBloodType();
        // 获取血型
        if (!bloodType.equals("A") && !bloodType.equals("B") && !bloodType.equals("AB") && !bloodType.equals("O")) {
            return CommonResponse.fail("请输入正确血型（如 A B AB O）");
        }
        // 申请对应血型数量
        Integer requestsQuantity = bloodRequests.getQuantity();
        // 查询血库对应血型
        QueryWrapper<BloodInventory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("BloodType", bloodType);
        BloodInventory bloodInventory = bloodInventoryMapper.selectOne(queryWrapper);
        // 如果对应血型数量 > 申请数量 则
        if (bloodInventory.getQuantity() >= requestsQuantity) {
            bloodRequests.setStatus("申请成功");
            // 构造更新条件
            LambdaUpdateWrapper<BloodInventory> updateWrapper = Wrappers.lambdaUpdate();
            updateWrapper.eq(BloodInventory::getBloodType, bloodType);
            updateWrapper.setSql("quantity = quantity - " + requestsQuantity);
            boolean update = bloodInventoryService.update(updateWrapper);
            if (update) {
                bloodRequestsService.save(bloodRequests);
                return CommonResponse.success(bloodRequests);
            }
        }
        bloodRequests.setStatus("申请失败");

        bloodRequestsService.save(bloodRequests);
        return CommonResponse.success(bloodRequests);
    }

    // 更新用血申请信息
    /*@PutMapping("/{requestID}")
    @ApiOperation(value = "更新用血申请信息")
    public CommonResponse<?> updateBloodRequest(@PathVariable Integer requestID, @RequestBody BloodRequests bloodRequests) {
        bloodRequests.setRequestID(requestID);
        bloodRequestsService.updateById(bloodRequests);
        return CommonResponse.success(bloodRequests);
    }*/

    // 删除用血申请信息
    /*@DeleteMapping("/{requestID}")
    @ApiOperation(value = "删除用血申请信息")
    public CommonResponse<?> deleteBloodRequest(@PathVariable Integer requestID) {
        bloodRequestsService.removeById(requestID);
        return CommonResponse.success(null,"删除用血申请信息成功");
    }*/
}
