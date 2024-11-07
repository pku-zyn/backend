package com.zyn.ticketorder.service;

import com.zyn.ticketorder.model.User;
import com.zyn.ticketorder.model.UserRegistrationDto;
import com.zyn.ticketorder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean registerUser(UserRegistrationDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            return false; // 邮箱已被注册
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword()); // 注意：实际项目中应加密密码
        userRepository.save(user);
        return true;
    }
}
