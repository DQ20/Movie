package com.husky.hqMovie.controller;

import com.husky.hqMovie.controller.BaseController;
import com.husky.hqMovie.pojo.Ticket;
import com.husky.hqMovie.service.ITicketService;
import com.husky.hqMovie.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController extends BaseController {
    @Autowired
    ITicketService service;
    @RequestMapping("/showTicket")
    public JsonResult showTicket(){
        List<Ticket> list=service.getAllTicket();
        return new JsonResult<>(OK,list);
    }
}
