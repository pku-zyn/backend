package com.zyn.ticketorder.controller;

import com.zyn.ticketorder.dto.LoginRequest;
import com.zyn.ticketorder.service.UserService;
import com.zyn.ticketorder.model.User;
import com.zyn.ticketorder.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // 加密工具

    @Autowired
    private JwtUtil jwtUtil;

    // 注册用户
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // 检查用户名、邮箱、手机号是否已存在
        if (userService.getUserByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("用户名已经存在");
        }
        if (userService.getUserByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("邮箱已经存在");
        }
        if (userService.getUserByMobile(user.getMobile()).isPresent()) {
            return ResponseEntity.badRequest().body("手机号已经存在");
        }
        // 注册用户
        userService.registerUser(user);
        return ResponseEntity.ok("用户注册成功，请登录");
    }

    // 更新用户信息
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (userService.getUserByUsername(user.getUsername()).isEmpty()) {
            return ResponseEntity.badRequest().body("用户不存在");
        }

        user.setId(id);  // 设置ID，以便更新
        userService.updateUser(user);
        return ResponseEntity.ok("用户信息更新成功");
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("用户删除成功");
    }

    // 获取用户信息
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id); // 调用按 ID 查询方法
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 登录接口
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String identifier = loginRequest.getIdentifier();
        String password = loginRequest.getPassword();

        // 校验用户名/邮箱/手机号和密码
        Optional<User> user = userService.login(identifier, password);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("用户名或密码错误");
        }

        // 生成 JWT Token
        String token = jwtUtil.generateToken(user.get().getUsername());

        // 返回 Token
        return ResponseEntity.ok(token);
    }
}
