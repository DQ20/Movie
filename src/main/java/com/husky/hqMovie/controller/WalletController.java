package com.husky.hqMovie.controller;

import com.husky.hqMovie.pojo.Wallet;
import com.husky.hqMovie.service.IWalletService;
import com.husky.hqMovie.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController extends BaseController{
    @Autowired
    IWalletService service;
    @GetMapping("/showWallet")
    public JsonResult<Wallet> showWallet(HttpSession session){
        String name=(String) session.getAttribute("uname");
        Wallet wallet=service.selectWalletByName(name);
        return new JsonResult<>(OK,wallet);
    }
    @PostMapping ("/updateBanlance")
    public JsonResult<Wallet> updateBalance(@RequestBody Double balance, HttpSession session){
        String name=(String) session.getAttribute("uname");
        service.updateBalance(name,balance);
        return new JsonResult<>(OK);
    }
}
