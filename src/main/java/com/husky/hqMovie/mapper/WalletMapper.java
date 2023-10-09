package com.husky.hqMovie.mapper;

import com.husky.hqMovie.pojo.Wallet;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletMapper {
    @Select("select * from wallet where walletName=#{name}")
    @Results(id = "walletResultMap", value = {
            @Result(property = "walletName", column = "walletName"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "hasTicket", column = "hasTicket"),
            @Result(property = "preTicket", column = "preTicket"),
            @Result(property = "id", column = "id"),
    })
    Wallet selectWalletByName(String name);
    @Select("select * from wallet")
    @ResultMap("walletResultMap")
    List<Wallet> selectAllWallet();

    @Insert("insert into wallet (walletName,balance,hasTicket,preTicket) values (#{walletName},#{balance},#{hasTicket},#{preTicket})")
    int insertWallet(Wallet wallet);

    @Update("update wallet set walletName=#{walletName},balance=#{balance},hasTicket=#{hasTicket},preTicket=#{preTicket} where id=#{id}")
    int updateWallet(Wallet wallet);

    @Delete("delete from wallet where walletName=#{name}")
    int deleteWalletByName(String name);

    @Select("select * from wallet where id=#{id}")
    @ResultMap("walletResultMap")
    Wallet selectWalletById(Integer id);
}
