package com.zyn.ticketorder.service;

import com.zyn.ticketorder.model.User;
import com.zyn.ticketorder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // 用于密码加密和验证

    // 根据用户名查询用户
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // 根据邮箱查询用户
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 根据手机号查询用户
    public Optional<User> getUserByMobile(String mobile) {
        return userRepository.findByMobile(mobile);
    }

    // 根据用户ID查询用户
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // 注册新用户
    public User registerUser(User user) {
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // 更新用户信息
    public User updateUser(User user) {
        // 检查用户是否存在
        if (!userRepository.existsById(user.getId())) {
            throw new RuntimeException("用户不存在");
        }

        // 如果用户需要更新密码，确保密码被加密
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return userRepository.save(user);
    }

    // 删除用户
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("用户不存在");
        }
        userRepository.deleteById(userId);
    }

    public Optional<User> login(String identifier, String password) {
        // 查询用户
        Optional<User> user = userRepository.findByUsername(identifier);
        if (user.isEmpty()) {
            user = userRepository.findByEmail(identifier);
        }
        if (user.isEmpty()) {
            user = userRepository.findByMobile(identifier);
        }

        // 日志检查
        if (user.isEmpty()) {
            System.out.println("用户未找到，Identifier：" + identifier);
            return Optional.empty();
        }

        // 验证密码
        boolean isPasswordMatched = passwordEncoder.matches(password, user.get().getPassword());
        if (!isPasswordMatched) {
            System.out.println("密码不匹配，用户：" + identifier);
            System.out.println("输入密码：" + password);
            System.out.println("数据库密码：" + user.get().getPassword());
            return Optional.empty();
        }

        // 返回用户
        System.out.println("登录成功，用户：" + identifier);
        return user;
    }

}
