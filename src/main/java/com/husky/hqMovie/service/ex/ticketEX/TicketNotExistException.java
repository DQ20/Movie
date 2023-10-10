package com.husky.hqMovie.service.ex.ticketEX;

import com.husky.hqMovie.service.ex.ServiceException;

public class TicketNotExistException extends ServiceException {

    public TicketNotExistException() {
        super();
    }

    public TicketNotExistException(String message) {
        super(message);
    }

    public TicketNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketNotExistException(Throwable cause) {
        super(cause);
    }

    protected TicketNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
