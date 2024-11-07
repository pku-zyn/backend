package com.zyn.ticketorder.model;

public class UserRegistrationDto {
    private String email;
    private String password;

    // 获取 ID
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

    // Getters and Setters
}