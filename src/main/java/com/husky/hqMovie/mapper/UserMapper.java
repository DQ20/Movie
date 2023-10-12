package com.husky.hqMovie.mapper;

import com.husky.hqMovie.pojo.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from t_users where id=#{id}")
    User selectUserById(Integer id);
    @Select("select * from t_users where name=#{name}")
    User selectUserByName(String name);
    @Insert("insert into t_users values (null,#{name},#{gender},#{age},#{registerDate},#{email},#{phone},#{salt},#{password})")
    @Results(id = "baseUserMap",value = {
            @Result(column = "name",property = "name"),
            @Result(column = "gender",property = "gender"),
            @Result(column = "age",property = "age"),
            @Result(column = "registerDate",property = "registerDate"),
            @Result(column = "email",property = "email"),
            @Result(column = "phone",property = "phone"),
            @Result(column = "salt",property = "salt"),
            @Result(column = "password",property = "password")
    }
    )
    Integer savaUser(User user);
    @Select("select * from t_users")
    List<User> selectAll();
    @Delete("delete from t_users where uid=#{id}")
    Integer deleteById(Integer id);
    @Update("update t_users set name=#{name},gender=#{gender},age=#{age},email=#{email},phone=#{phone},salt=#{salt},password=#{password} where id=#{id}")
    @ResultMap("baseUserMap")
    Integer modifyUser(User user);
    @Delete(
            "delete from t_users where name is null "
    )
    void cleanNullByName();
    @Select("select salt wehere name=#{name}")
    String selectSaltByName(String name);
}
