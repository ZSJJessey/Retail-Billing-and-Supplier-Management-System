package com.zhoushijie.demo.mapper;

import com.zhoushijie.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PasswordMapper {
    @Update("update usertable set password=#{password}  where  username=#{username}" )
    public int UpdatePassword(User user);
}
