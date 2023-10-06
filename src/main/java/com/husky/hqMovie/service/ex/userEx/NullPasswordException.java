package com.husky.hqMovie.service.ex.userEx;

import com.husky.hqMovie.service.ex.ServiceException;

public class NullPasswordException extends ServiceException {

    public NullPasswordException() {
        super();
    }

    public NullPasswordException(String message) {
        super(message);
    }

    public NullPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullPasswordException(Throwable cause) {
        super(cause);
    }

    protected NullPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
