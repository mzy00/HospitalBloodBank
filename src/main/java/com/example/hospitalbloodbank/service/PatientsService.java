package com.example.hospitalbloodbank.service;

import com.example.hospitalbloodbank.entity.Patients;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author mazeyuan
* @description 针对表【Patients(存储病人的基本信息)】的数据库操作Service
* @createDate 2023-11-19 09:00:15
*/
public interface PatientsService extends IService<Patients> {
    Patients getPatientById(Integer patientID);

    Patients createPatient(Patients patients);

    Patients updatePatient(Integer patientID, Patients patients);

    void deletePatient(Integer patientID);

}
