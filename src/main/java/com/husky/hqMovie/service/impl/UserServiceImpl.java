package com.husky.hqMovie.service.impl;

import com.husky.hqMovie.mapper.TicketMapper;
import com.husky.hqMovie.mapper.UserMapper;
import com.husky.hqMovie.mapper.WalletMapper;
import com.husky.hqMovie.pojo.Ticket;
import com.husky.hqMovie.pojo.User;
import com.husky.hqMovie.pojo.Wallet;
import com.husky.hqMovie.service.IUserService;
import com.husky.hqMovie.service.ex.ticketEX.TicketNotExistException;
import com.husky.hqMovie.service.ex.userEx.*;
import com.husky.hqMovie.service.ex.walletEx.BalanceException;
import com.husky.hqMovie.service.ex.walletEx.WalletExistException;
import io.lettuce.core.RedisCommandExecutionException;
import io.lettuce.core.RedisException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Husky
 * date:2023-10-10
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper mapper;
    @Autowired
    private TicketMapper ticketMapper;
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public Integer UserRegister(User user) {
        String name=user.getName();
        String oldPassword=user.getPassword();
        int count=0;
        if(mapper.selectUserByName(name)!=null){
            throw new UserExistException("用户已存在");
        }
        else {
            Date date=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String newDate=sdf.format(date);
            user.setRegisterDate(newDate);
            String salt= UUID.randomUUID().toString().toUpperCase();
            String newPassword=getMD5Password(oldPassword,salt);
            user.setPassword(newPassword);
            user.setSalt(salt);
            count=mapper.savaUser(user);
        }
        if (count!=1){
            throw new InsertException("发生未知异常");
        }
        return count;
    }

    @Override
    public List<User> queryAll() {
        List<User> users=mapper.selectAll();
        return users;
    }

    @Override
    public Integer deleteUserById(Integer id) {
        int count=mapper.deleteById(id);
        return count;
    }

    @Override
    public User selectByName(String name) {
        User user=mapper.selectUserByName(name);
        return user;
    }

    @Override
    public void cleanNull() {
        mapper.cleanNullByName();
    }
    @Override
    public User UserLogin(String name, String password) {
        User user=mapper.selectUserByName(name);
        if (user==null){
            throw new UserExistException("用户不存在");
        }else {
            String salt=user.getSalt();
            String newPassword=getMD5Password(password,salt);
            password=user.getPassword();
            if(!password.equals(newPassword)){
                throw new PasswordNotTrueException("密码不正确");
            }
        }
        return user;
    }

    @Override
    public void modifyPassword(String name, String oldPassword, String newPassword,String confirmNewPassword){
        if (newPassword==null||confirmNewPassword==null||oldPassword==null){
            throw new NullPasswordException("密码不能为空");
        }
        if (!newPassword.equals(confirmNewPassword)){
            throw new PasswordNotTrueException("两次输入的新密码不一致");
        }
        User user = mapper.selectUserByName(name);
        String salt=user.getSalt();
        String pwd=user.getPassword();
        if(!pwd.equals(getMD5Password(oldPassword,salt))){
            throw new PasswordNotTrueException("旧密码不正确");
        }
        else {
            String newSalt=UUID.randomUUID().toString().toUpperCase();
            pwd=getMD5Password(newPassword,newSalt);
            user.setSalt(newSalt);
            user.setPassword(pwd);
            mapper.modifyUser(user);
        }

    }
    @Override
    public void modifyInfo(Integer id,String name,Integer age,String gender,String phone,String email,String oldName) {
            User user=mapper.selectUserById(id);
            if(user==null){
                throw new UserExistException("用户不存在");
            }
            if(mapper.selectUserByName(name)!=null){
                throw new UserExistException("用户已存在");
            }
           try{
               redisTemplate.rename(oldName+"_hasTicket",name+"_hasTicket");
               redisTemplate.rename(oldName+"_preTicket",name+"_preTicket");
           }catch (Exception e){
               System.out.println("忽略");
           }
           finally {
               System.out.println("执行了");
               user.setPhone(phone);
               user.setName(name);
               user.setGender(gender);
               user.setEmail(email);
               user.setAge(age);
               mapper.modifyUser(user);
           }
    }
//需要事务管理、需要多线程开发
    @Override
    public void buyTicket(Integer movieId, String name) {
        Ticket ticket=ticketMapper.selectTicketByTd(movieId);
        Wallet wallet=walletMapper.selectWalletByName(name);
        if(ticket==null){
            throw new TicketNotExistException("该电影不存在...");
        }
        else if(wallet==null){
            throw new WalletExistException("账号不存在...");
        }
        Double price=ticket.getPrice();
        Double balance=wallet.getBalance();
        if(balance<price){
            throw new BalanceException("余额不足");
        }
        else{
            balance=balance-price;
            System.out.println(balance);
            wallet.setBalance(balance);
            System.out.println(wallet);
            walletMapper.updateWallet(wallet);
            String hasTicket=wallet.getHasTicket();
            String movieName=ticket.getName();
            Map<String,Ticket> map=redisTemplate.opsForHash().entries(hasTicket);
            map.put(movieName,ticket);
            redisTemplate.opsForHash().putAll(hasTicket,map);
        }
    }


    public String getMD5Password(String password,String salt){
        String newPassword=null;
        for (int i = 0; i < 3; i++) {
            newPassword=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }

        return newPassword;
    }

}
