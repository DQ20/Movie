package com.husky.hqMovie.service.ex.userEx;

import com.husky.hqMovie.service.ex.ServiceException;
import jdk.jshell.spi.ExecutionControl;

public class PasswordNotTrueException extends ServiceException {
    public PasswordNotTrueException() {
        super();
    }

    public PasswordNotTrueException(String message) {
        super(message);
    }

    public PasswordNotTrueException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotTrueException(Throwable cause) {
        super(cause);
    }

    protected PasswordNotTrueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
