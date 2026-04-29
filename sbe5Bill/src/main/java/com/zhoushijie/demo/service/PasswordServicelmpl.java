package com.zhoushijie.demo.service;

import com.zhoushijie.demo.entity.User;
import com.zhoushijie.demo.mapper.PasswordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordServicelmpl implements PasswordService {
    @Autowired
    PasswordMapper passwordMapper;

    @Override
    public User UpdatePassword(User user) {
//        return UpdatePassword(user);
        return passwordMapper.UpdatePassword(user);
    }
}
