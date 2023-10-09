package com.husky.hqMovie.service;

import com.husky.hqMovie.pojo.Ticket;

import java.util.List;

public interface ITicketService {
    List<Ticket> getAllTicket();
    List<Ticket> getTicketsByStr(String str);
    Integer modifyTicket(Ticket ticket);
    Integer deleteTicketById(Integer id);
    Integer addTicket(Ticket ticket);
}
