package com.zhoushijie.demo.mapper;

import com.zhoushijie.demo.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    @Select("select * from usertable where username=#{username} and password=#{password}")
    public User login(User user);

    public List<User> findUserByWhere(User user);

    public int insertUser(User user);

    public User selectUser(Integer id);

    public int updateUser(User user);

    @Delete("delete from usertable where id=#{id}")
    public int deleteUserById(Integer id);

    @Select(" select id, username, password, typeType, value\n" +
            "        from usertable\n" +
            "         where id = #{id}\n" +
            "\n")
    public User findById(Integer id);
}
