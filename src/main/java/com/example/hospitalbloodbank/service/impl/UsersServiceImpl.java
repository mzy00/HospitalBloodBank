package com.example.hospitalbloodbank.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hospitalbloodbank.entity.Users;
import com.example.hospitalbloodbank.service.UsersService;
import com.example.hospitalbloodbank.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
* @author mazeyuan
* @description 针对表【Users(存储系统用户信息)】的数据库操作Service实现
* @createDate 2023-11-19 09:00:15
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users getUserByUsername(String username) {
        return usersMapper.selectOne(new QueryWrapper<Users>().eq("username", username));
    }

    @Override
    public List<Users> getAllUsers() {
        return usersMapper.selectList(null);
    }

    @Override
    public Users getUserById(Integer userId) {
        return usersMapper.selectById(userId);
    }

    @Override
    public Users createUser(Users users) {
        String encryptPassword = DigestUtils.md5DigestAsHex((users.getPassword()).getBytes());
        users.setPassword(encryptPassword);
        usersMapper.insert(users);
        return users;
    }

    @Override
    public Users updateUser(Integer userId, Users users) {
        users.setUserID(userId);
        usersMapper.updateById(users);
        return users;
    }

    @Override
    public int deleteUser(Integer userId) {
        return usersMapper.deleteById(userId);
    }
}
