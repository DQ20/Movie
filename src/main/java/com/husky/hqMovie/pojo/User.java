package com.husky.hqMovie.pojo;

import jakarta.validation.constraints.*;
import lombok.Data;
@Data
public class User {

    private Integer id;
    @NotNull(message = "用户名不能为空")
    private String name;
    private String gender;
    @Size(min = 0,max = 120)
    private Integer age;
    private String registerDate;
    @Email(message = "邮箱格式不正确")
    private String email;
    private String phone;
    private String salt;
    private String password;
}
