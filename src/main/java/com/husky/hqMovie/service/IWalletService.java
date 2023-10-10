package com.husky.hqMovie.service;

import com.husky.hqMovie.pojo.Wallet;

import java.util.List;

public interface IWalletService {
    int insertWallet(String name);
    int topUp(String name,Double balance);
    int updateWalletInfo(Integer id,String name);
    int deleteWallet(String name);
    Wallet selectWalletByName(String name);
    List<Wallet> selectAllWallet();
}
