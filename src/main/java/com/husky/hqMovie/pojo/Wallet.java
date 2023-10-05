package com.husky.hqMovie.pojo;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Hashtable;
@Data
public class Wallet {
    @NotNull
    private String walletName;
    @NotNull
    private Double balance;
    private Ticket[] hasTicket;
    private Ticket[] preTicket;
}
