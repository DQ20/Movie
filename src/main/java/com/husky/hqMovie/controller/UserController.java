package com.husky.hqMovie.controller;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.husky.hqMovie.pojo.User;
import com.husky.hqMovie.service.impl.UserServiceImpl;
import com.husky.hqMovie.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserServiceImpl service;
    @PostMapping  ("/register")
    public JsonResult reg(@RequestBody User user){
        service.UserRegister(user);
        return new JsonResult<>(OK,user);
    }
    @PostMapping("login")
    public JsonResult<User> login(@RequestBody JSONObject object, HttpSession session){
        String name=object.getString("username");
        String password=object.getString("password");
        User user=service.UserLogin(name,password);
        session.setAttribute("uid",user.getId());
        session.setAttribute("uname",user.getName());
        return new JsonResult<User>(OK,user);
    }

    @GetMapping("/selectAll")
    public JsonResult<List<User>> selectAll(){
        List<User> list=new ArrayList<>();
        list=service.queryAll();
        return new JsonResult<>(OK,list);
    }
    @PostMapping("/modifyPassword")
    public JsonResult<Void> modifyPassword(@RequestBody String requestJson,HttpSession session){
        JSONObject jsonObject= JSON.parseObject(requestJson);
        String name=(String)session.getAttribute("uname");
        String oldPassword=jsonObject.getString("oldPassword");
        String newPassword=jsonObject.getString("newPassword");
        service.modifyPassword(name,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }
    @PutMapping("/modifyInfo")
    public JsonResult<User> modifyInfo(@RequestBody String requestJson,HttpSession session){
        JSONObject jsonObject=JSON.parseObject(requestJson);
        Integer id=(Integer)session.getAttribute("uid");
        String name=jsonObject.getString("name");
        Integer age=jsonObject.getInteger("age");
        String gender=jsonObject.getString("gender");
        String email=jsonObject.getString("email");
        String phone=jsonObject.getString("phone");
        session.setAttribute("uname",name);
        service.modifyInfo(id,name,age,gender,phone,email);
        User user = service.selectByName(name);
        return new JsonResult<>(OK,user);
    }
    @GetMapping("/logout")
    public JsonResult<String> logout(HttpSession session) {
        // 清除用户的会话信息
        session.invalidate();
        return new JsonResult<>(OK,"成功退出");
    }
    @GetMapping("/showUser")
    public JsonResult<User> showUser(HttpSession session){
        String name=(String) session.getAttribute("uname");
        User user = service.selectByName(name);
        return new JsonResult<>(OK,user);
    }
}
