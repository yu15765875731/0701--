package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository 负责与数据库交互。
 * 说明：继承 JpaRepository 后，Spring Data JPA 会自动生成常见 CRUD 方法。
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
