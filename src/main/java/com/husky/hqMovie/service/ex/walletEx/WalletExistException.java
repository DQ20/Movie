package com.husky.hqMovie.service.ex.walletEx;

import com.husky.hqMovie.service.ex.ServiceException;

public class WalletExistException extends ServiceException {
    public WalletExistException() {
        super();
    }

    public WalletExistException(String message) {
        super(message);
    }

    public WalletExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public WalletExistException(Throwable cause) {
        super(cause);
    }

    protected WalletExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
