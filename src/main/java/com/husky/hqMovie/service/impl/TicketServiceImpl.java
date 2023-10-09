package com.husky.hqMovie.service.impl;

import com.husky.hqMovie.mapper.TicketMapper;
import com.husky.hqMovie.pojo.Ticket;
import com.husky.hqMovie.service.ITicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements ITicketService {
    @Autowired
    TicketMapper mapper;

    @Override
    public List<Ticket> getAllTicket() {
        List<Ticket> list=mapper.selectAllTicket();
        return list;
    }

    @Override
    public List<Ticket> getTicketsByStr(String str) {
        List<Ticket> list=mapper.selectTicketByStr(str);
        return list;
    }

    @Override
    public Integer modifyTicket(Ticket ticket) {
        int count=mapper.updateTicket(ticket);
        return count;
    }

    @Override
    public Integer deleteTicketById(Integer id) {
        int count=mapper.deleteTicketById(id);
        return count;
    }

    @Override
    public Integer addTicket(Ticket ticket) {
        int count=mapper.insetTicket(ticket);
        return count;
    }
}
