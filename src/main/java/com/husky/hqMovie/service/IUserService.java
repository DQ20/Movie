package com.husky.hqMovie.service;

import com.husky.hqMovie.pojo.User;

import java.util.List;

public interface IUserService {

    Integer UserRegister(User user);
    List<User> queryAll();
    Integer deleteUserById(Integer id);
    User selectByName(String name);
    Integer updateUser(User user);
    void cleanNull();
    User UserLogin(String name,String password);
    void modifyPassword(String name,String oldPassword,String newPassword,String confirmNewPassword);
    void modifyInfo(Integer id,String name,Integer age,String gender,String phone,String email);
    void buyTicket(Integer id,String name);
}
