package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.List;

/**
 * UserService 定义用户业务层接口。
 * 作用：解耦 Controller 与具体实现，便于后续扩展和单元测试。
 */
public interface UserService {

    User createUser(User user);

    User getUserById(Long id);

    List<User> listUsers();

    void deleteUser(Long id);
}
