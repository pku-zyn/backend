package com.zyn.ticketorder.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity // 标注为实体类
@Table(name = "users") // 指定数据库表名
public class User {

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增策略
    private Long id;

    @Column(nullable = false, unique = true, length = 50) // 非空，唯一，最大长度50
    private String username;

    @Column(nullable = false, unique = true, length = 100) // 非空，唯一，最大长度100
    private String email;

    @Column(nullable = false, unique = true, length = 15) // 非空，唯一，最大长度15
    private String mobile;

    @Column(nullable = false) // 非空
    private String password;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // 在持久化之前设置 createdAt 字段的默认值
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    // 构造器
    public User() {
    }

    public User(String username, String email, String mobile, String password) {
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    // Getters 和 Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // toString 方法
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
