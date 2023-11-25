package com.example.hospitalbloodbank.controller;

import com.example.hospitalbloodbank.common.CommonResponse;
import com.example.hospitalbloodbank.entity.BloodRequests;
import com.example.hospitalbloodbank.entity.Patients;
import com.example.hospitalbloodbank.service.PatientsService;
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
@RequestMapping("/api/patients")
@Api(tags = "病人信息")
public class PatientsController {

    @Autowired
    private PatientsService patientsService;

    // 获取所有病人信息
    @GetMapping
    @ApiOperation(value = "获取所有病人信息")
    public CommonResponse<?> getAllPatients(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Patients> list = patientsService.list();
        PageInfo<Patients> patientsPageInfo = new PageInfo<>(list);
        return CommonResponse.success(patientsPageInfo);
    }

    // 根据ID获取病人信息
    @GetMapping("/{patientID}")
    @ApiOperation(value = "根据ID获取病人信息")
    public CommonResponse<?> getPatientById(@PathVariable Integer patientID) {
        return CommonResponse.success(patientsService.getById(patientID));
    }

    // 创建病人信息
    @PostMapping
    @ApiOperation(value = "创建病人信息")
    @Transactional
    public CommonResponse<?> createPatient(@RequestBody Patients patients) {
        if (patients.getAge() > 0 && patients.getAge() < 200) {
            return CommonResponse.fail("请输入正确年龄");
        }
        if (RegexUtils.isPhoneInvalid(patients.getContactNumber())) {
            return CommonResponse.fail("请输入正确手机号");
        }
        // 获取性别
        String gender = patients.getGender();
        if (!gender.equals("男") && !gender.equals("女")) {
            return CommonResponse.fail("请输入正确性别（如 男 女）");
        }
        // 获取血型
        String bloodType = patients.getBloodType();
        if (!bloodType.equals("A") && !bloodType.equals("B") && !bloodType.equals("AB") && !bloodType.equals("O")) {
            return CommonResponse.fail("请输入正确血型（如 A B AB O）");
        }
        patientsService.save(patients);
        return CommonResponse.success(patients);
    }

    // 更新病人信息
    @PutMapping("/{patientID}")
    @ApiOperation(value = "更新病人信息")
    @Transactional
    public CommonResponse<?> updatePatient(@PathVariable Integer patientID, @RequestBody Patients patients) {
        if (patients.getAge() > 0 && patients.getAge() < 200) {
            return CommonResponse.fail("请输入正确年龄");
        }
        if (RegexUtils.isPhoneInvalid(patients.getContactNumber())) {
            return CommonResponse.fail("请输入正确手机号");
        }
        // 获取性别
        String gender = patients.getGender();
        if (!gender.equals("男") && !gender.equals("女")) {
            return CommonResponse.fail("请输入正确性别（如 男 女）");
        }
        // 获取血型
        String bloodType = patients.getBloodType();
        if (!bloodType.equals("A") && !bloodType.equals("B") && !bloodType.equals("AB") && !bloodType.equals("O")) {
            return CommonResponse.fail("请输入正确血型（如 A B AB O）");
        }
        patients.setPatientID(patientID);
        patientsService.updateById(patients);
        return CommonResponse.success(patients);
    }

    // 删除病人信息
    @DeleteMapping("/{patientID}")
    @ApiOperation(value = "删除病人信息")
    public CommonResponse<?> deletePatient(@PathVariable Integer patientID) {
        patientsService.removeById(patientID);
        return CommonResponse.success(null,"删除病人信息成功");
    }
}
