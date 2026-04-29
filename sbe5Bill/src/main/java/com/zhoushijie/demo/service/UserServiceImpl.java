package com.zhoushijie.demo.service;

import com.zhoushijie.demo.entity.User;
import com.zhoushijie.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisTemplate redisTemplate;

    //java中的多态

    //父类引用指向子类对象

    //controller层调用service层，service层调用mapper层

    //中间有serivce层增加安全性
    @Override
    public User login(User user) {
        return userMapper.login(user);
    }



    @Override
    public List<User> findUserByWhere(User user) {
        //判断缓存里面是否有值  从缓存中拿（get）名为user的元素  (List<User>)--》强制转型
        List<User> list = (List<User>) redisTemplate.opsForValue().get("user");
        //
        if (list == null) {
            //如果没有则调用接口多条件查询的方法拿到数据库查询到的结果
            List<User> userByWhere = userMapper.findUserByWhere(user);
            //放到名为user的缓存中，显示在可视化工具Redis
            redisTemplate.opsForValue().set("user", userByWhere);
        }else {
            //如果拿到值则更新值
            List<User> userByWhere = userMapper.findUserByWhere(user);
            //放到名为user的缓存中，显示在可视化工具Redis
            redisTemplate.opsForValue().set("user", userByWhere);
        }
        //最后再从缓存中拿（get）名为user的元素
        return (List<User>) redisTemplate.opsForValue().get("user");
//        return userMapper.findUserByWhere(user);
    }

    @Override
    public int insertUser(User user) {
        int i = userMapper.insertUser(user);
        if (i > 0) {
            //key为“insertUser”名并以加以更新的主键
            redisTemplate.opsForValue().set("insertUser" + user.getId(), user);
            //set更新完在页面上显示所有更新完的数据
            List<User> userByWhere = userMapper.findUserByWhere(null);
            redisTemplate.opsForValue().set("user", userByWhere);
            i = 1;
        }
        return i;
    }

    @Override
    public User selectUser(Integer id) {
        return userMapper.selectUser(id);
    }

    @Override
    public int updateUser(User user) {
        int i = userMapper.updateUser(user);
        if (i > 0) {
            //key为“updateUser”名并以加以更新的主键
            redisTemplate.opsForValue().set("updateUser" + user.getId(), user);
            //set更新完在页面上显示所有更新完的数据
            List<User> userByWhere = userMapper.findUserByWhere(null);
            redisTemplate.opsForValue().set("user", userByWhere);
            i = 1;
        }
        return i;
    }

    @Override
    public int deleteUserById(Integer id) {
        int i = userMapper.deleteUserById(id);
        if (i > 0) {
            redisTemplate.delete("deleteUserById" + id);
            List<User> userByWhere = userMapper.findUserByWhere(null);
            redisTemplate.opsForValue().set("user", userByWhere);
            i = 1;
        }
        return i;
    }


    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
