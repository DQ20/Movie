package com.husky.hqMovie.service.ex.walletEx;

import com.husky.hqMovie.service.ex.ServiceException;

public class UpdateMoneyException extends ServiceException {
    public UpdateMoneyException() {
        super();
    }

    public UpdateMoneyException(String message) {
        super(message);
    }

    public UpdateMoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateMoneyException(Throwable cause) {
        super(cause);
    }

    protected UpdateMoneyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
