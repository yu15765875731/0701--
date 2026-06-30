package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制层。
 * 作用：接收前端请求，负责接口地址的定义和参数处理。
 * 说明：Controller 层通常不写复杂业务逻辑，而是把请求转发给 Service 层。
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 新增用户接口。
     * 接口地址：POST /users
     * 作用：接收前端提交的用户信息，并交给 Service 层处理。
     */
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * 查询单个用户接口。
     * 接口地址：GET /users/{id}
     * 作用：根据用户 ID 查找数据。
     */
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    /**
     * 查询全部用户接口。
     * 接口地址：GET /users
     * 作用：返回所有用户数据，适合用来验证数据库访问是否正常。
     */
    @GetMapping
    public List<User> listUsers() {
        return userService.listUsers();
    }

    /**
     * 删除用户接口。
     * 接口地址：DELETE /users/{id}
     * 作用：删除指定用户，适合练习 REST 风格接口设计。
     */
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "删除成功";
    }
}
