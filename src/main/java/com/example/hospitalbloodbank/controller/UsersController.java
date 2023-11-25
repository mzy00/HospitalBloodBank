package com.example.hospitalbloodbank.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.hospitalbloodbank.common.CommonResponse;
import com.example.hospitalbloodbank.entity.BloodRequests;
import com.example.hospitalbloodbank.entity.Users;
import com.example.hospitalbloodbank.mapper.UsersMapper;
import com.example.hospitalbloodbank.service.UsersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@RequestMapping("/api/users")
@Api(tags = "用户功能")
public class UsersController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersMapper usersMapper;

    // 用户登录
    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public CommonResponse<?> loginUser(@RequestBody Users user, HttpServletRequest request) {
        Users existingUser = usersService.getUserByUsername(user.getUsername());
        String encryptPassword = DigestUtils.md5DigestAsHex((user.getPassword()).getBytes());
        if (existingUser != null && existingUser.getPassword().equals(encryptPassword)) {
            // 登录成功
            // 获取当前请求的session
            HttpSession session = request.getSession();

            // 将用户信息存储在session中
            session.setAttribute("user", user);

            // 设置session的最大存活时间为1800秒（30分钟）
            session.setMaxInactiveInterval(1800);

            return CommonResponse.success();
        } else {
            // 登录失败
            return CommonResponse.fail("登录失败");
        }
    }

    // 用户登出
    @GetMapping("/logout")
    @ApiOperation(value = "安全退出")
    public CommonResponse<?> logout(HttpServletRequest request) {
        // 获取当前请求的session
        HttpSession session = request.getSession();

        // 清除session中的用户信息
        session.removeAttribute("user");

        return CommonResponse.success();
    }

    // 获取所有用户
    @GetMapping
    @ApiOperation(value = "获取所有用户")
    public CommonResponse<?> getAllUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Users> allUsers = usersService.getAllUsers();
        PageInfo<Users> usersPageInfo = new PageInfo<>(allUsers);
        return CommonResponse.success(usersPageInfo);
    }

    // 根据ID获取用户
    @GetMapping("/{userId}")
    @ApiOperation(value = "根据ID获取用户")
    public CommonResponse<?> getUserById(@PathVariable Integer userId) {
        return CommonResponse.success(usersService.getUserById(userId));
    }

    // 创建用户
    @PostMapping("/createUser")
    @ApiOperation(value = "创建用户")
    public CommonResponse<?> createUser(@RequestBody Users users) {
        Users oldUser = usersMapper.selectOne(new QueryWrapper<Users>().eq("username", users.getUsername()));
        if (!ObjectUtil.isNull(oldUser)) {
            return CommonResponse.fail("用户已存在，请直接登陆");
        }
        return CommonResponse.success(usersService.createUser(users));
    }

    // 更新用户
    @PutMapping("/{userId}")
    @ApiOperation(value = "更新用户")
    public CommonResponse<?> updateUser(@PathVariable Integer userId, @RequestBody Users users) {
        return CommonResponse.success(usersService.updateUser(userId, users));
    }

    // 删除用户
    @DeleteMapping("/{userId}")
    @ApiOperation(value = "删除用户")
    public CommonResponse<?> deleteUser(@PathVariable Integer userId) {
        if (usersService.deleteUser(userId) > 0) {
            return CommonResponse.success();
        }
        return CommonResponse.fail("删除用户失败");
    }
}
