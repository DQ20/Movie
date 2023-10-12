package com.husky.hqMovie.controller;

import com.husky.hqMovie.pojo.Wallet;
import com.husky.hqMovie.service.IWalletService;
import com.husky.hqMovie.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wallet")
public class WalletController extends BaseController{
    @Autowired
    IWalletService service;
    @Autowired
    RedisTemplate redisTemplate;
    @GetMapping("/showWallet")
    public JsonResult showWallet(HttpSession session){
        List<Map> list=new ArrayList<>();
        String name=(String) session.getAttribute("uname");
        Wallet wallet=service.selectWalletByName(name);
        String hasMovie=wallet.getHasTicket();
        String preMovie=wallet.getPreTicket();
        Map hasMap=redisTemplate.opsForHash().entries(hasMovie);
        Map perMap=redisTemplate.opsForHash().entries(preMovie);
        list.add(hasMap);
        list.add(perMap);
        JsonResult result=new JsonResult(OK);
        result.setData(wallet);
        result.setTickets(list);
        return result;
    }
    @GetMapping ("/topUp")
    public JsonResult<Wallet> topUp(@RequestParam(name = "balance") Double balance, HttpSession session){
        String name=(String) session.getAttribute("uname");
        service.topUp(name,balance);
        return new JsonResult<>(OK);
    }
}
