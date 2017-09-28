package com.mt.search.service;

/**
 * Created by robertmurray on 9/27/17.
 */
public class SearchProviderException extends Exception {
    public SearchProviderException() {
    }

    public SearchProviderException(String message) {
        super(message);
    }

    public SearchProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public SearchProviderException(Throwable cause) {
        super(cause);
    }

    public SearchProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
