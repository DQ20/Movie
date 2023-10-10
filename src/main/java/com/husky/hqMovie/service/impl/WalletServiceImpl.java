package com.husky.hqMovie.service.impl;

import com.husky.hqMovie.mapper.WalletMapper;
import com.husky.hqMovie.pojo.Wallet;
import com.husky.hqMovie.service.IWalletService;
import com.husky.hqMovie.service.ex.walletEx.UpdateMoneyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WalletServiceImpl implements IWalletService {
    @Autowired
    private WalletMapper mapper;
    int count=0;
    @Override
    public int insertWallet(String name) {
        Double balance=0.0;
        String hasTicket=name+"_hasTicket";
        String preTicket=name+"_preTicket";
        Wallet wallet=new Wallet();
        wallet.setWalletName(name);
        wallet.setBalance(balance);
        wallet.setPreTicket(preTicket);
        wallet.setHasTicket(hasTicket);
        count=mapper.insertWallet(wallet);
        return count;
    }

    @Override
    public int topUp(String name, Double balance) {
        Wallet wallet=mapper.selectWalletByName(name);
        balance+=wallet.getBalance();
        wallet.setBalance(balance);
        count=mapper.updateWallet(wallet);
        if (count!=1){
            throw new UpdateMoneyException("发生未知错误");
        }
        return count;
    }

    @Override
    public int updateWalletInfo(Integer id,String name) {
        Wallet wallet=mapper.selectWalletById(id);
        wallet.setWalletName(name);
        String hasTicket=name+"_hasTicket";
        String preTicket=name+"_preTicket";
        wallet.setHasTicket(hasTicket);
        wallet.setPreTicket(preTicket);
        count=mapper.updateWallet(wallet);
        return count;
    }

    @Override
    public int deleteWallet(String name) {
        count=mapper.deleteWalletByName(name);
        return count;
    }

    @Override
    public Wallet selectWalletByName(String name) {
        Wallet wallet=mapper.selectWalletByName(name);
        return wallet;
    }

    @Override
    public List<Wallet> selectAllWallet() {
        List<Wallet> wallets=mapper.selectAllWallet();
        return wallets;
    }
}
