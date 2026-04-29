package com.zhoushijie.demo.service;

import com.zhoushijie.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


public interface PasswordService {
    public int UpdatePassword(User user);

}
