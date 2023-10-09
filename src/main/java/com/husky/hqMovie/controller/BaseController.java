package com.husky.hqMovie.controller;

import com.husky.hqMovie.service.ex.ServiceException;
import com.husky.hqMovie.service.ex.userEx.InsertException;
import com.husky.hqMovie.service.ex.userEx.PasswordNotTrueException;
import com.husky.hqMovie.service.ex.userEx.UserExistException;
import com.husky.hqMovie.service.ex.walletEx.UpdateMoneyException;
import com.husky.hqMovie.util.JsonResult;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ExceptionHandler;
public class BaseController {
    public static final Integer OK=200;
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result=new JsonResult<>();
        if(e instanceof UserExistException){
            result.setState(400);
            result.setMassage(e.getMessage());
        }
        else if(e instanceof InsertException){
            result.setState(500);
            result.setMassage(e.getMessage());
        }
        else if(e instanceof PasswordNotTrueException){
            result.setState(401);
            result.setMassage(e.getMessage());
        }
        else if(e instanceof UpdateMoneyException){
            result.setMassage(e.getMessage());
            result.setState(511);
        }
        return result;
    }
    protected final Integer getUIDFromSession(HttpSession session){
        Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
        return uid;
    }
    protected final String getUsernameFromSession(HttpSession session){
        String uname=session.getAttribute("uname").toString();
        return uname;
    }
}
