package com.example.hospitalbloodbank.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hospitalbloodbank.entity.Patients;
import com.example.hospitalbloodbank.service.PatientsService;
import com.example.hospitalbloodbank.mapper.PatientsMapper;
import org.springframework.stereotype.Service;

/**
* @author mazeyuan
* @description 针对表【Patients(存储病人的基本信息)】的数据库操作Service实现
* @createDate 2023-11-19 09:00:15
*/
@Service
public class PatientsServiceImpl extends ServiceImpl<PatientsMapper, Patients>
    implements PatientsService{

    @Override
    public Patients getPatientById(Integer patientID) {
        return this.getById(patientID);
    }

    @Override
    public Patients createPatient(Patients patients) {
        this.save(patients);
        return patients;
    }

    @Override
    public Patients updatePatient(Integer patientID, Patients patients) {
        patients.setPatientID(patientID);
        this.updateById(patients);
        return patients;
    }

    @Override
    public void deletePatient(Integer patientID) {
        this.removeById(patientID);
    }
}




