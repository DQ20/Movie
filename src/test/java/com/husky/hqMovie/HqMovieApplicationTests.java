package com.husky.hqMovie;

//import jakarta.activation.DataSource;
import com.husky.hqMovie.controller.UserController;
import com.husky.hqMovie.mapper.UserMapper;
import com.husky.hqMovie.pojo.User;
import com.husky.hqMovie.service.IUserService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import javax.sql.DataSource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@MapperScan("com.husky.hqMovie.mapper")
@SpringBootTest
class HqMovieApplicationTests {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private DataSource dataSource;
    @Test
    void contextLoads() {
    }
    @Test
    void testConnection(){
        System.out.println(dataSource.getClass());
    }
    @Test
    void testMapper(){
        System.out.println(mapper.selectUserByName("Husky"));

    }
    @Autowired
    private IUserService service;
    @Autowired
    private UserController controller;
    @Test
    void testServiceMap(){
        System.out.println(service);
    }
    @Test
    void testService(){
        User user=new User();
        user.setName("hh");
    }
    public String getMD5Password(String password,String salt){
        String newPassword=null;
        for (int i = 0; i < 3; i++) {
            newPassword= DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }

        return newPassword;
    }
    @Test
    void testClear(){
        service.cleanNull();
    }
}
