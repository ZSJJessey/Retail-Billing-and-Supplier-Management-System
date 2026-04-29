package com.zhoushijie.demo.service;

import com.zhoushijie.demo.entity.User;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface UserService {
//对外暴露的就是接口（UserService）

    public User login(User user);

    public List<User> findUserByWhere(User user);

    public int insertUser(User user);

    public User selectUser(Integer id);

    public int updateUser(User user);

    public int deleteUserById(Integer id);
//用户权限方法
    public User findById(Integer id);

}
