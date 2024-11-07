package com.zyn.ticketorder.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    // 获取 ID
    public Long getId() {
        return id;
    }

    // 设置 ID
    public void setId(Long id) {
        this.id = id;
    }

    // 获取邮箱
    public String getEmail() {
        return email;
    }

    // 设置邮箱
    public void setEmail(String email) {
        this.email = email;
    }

    // 获取密码
    public String getPassword() {
        return password;
    }

    // 设置密码
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    // Getters and Setters
}