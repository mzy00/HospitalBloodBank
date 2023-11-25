package com.example.hospitalbloodbank.service;

import com.example.hospitalbloodbank.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author mazeyuan
* @description 针对表【Users(存储系统用户信息)】的数据库操作Service
* @createDate 2023-11-19 09:00:15
*/
public interface UsersService extends IService<Users> {

    List<Users> getAllUsers();

    Users getUserById(Integer userId);

    Users createUser(Users users);

    Users updateUser(Integer userId, Users users);

    int deleteUser(Integer userId);

    Users getUserByUsername(String username);
}
