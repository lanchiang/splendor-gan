package com.github.lanchiang.exceptions;

/**
 * @author Lan Jiang
 * @since 2019-07-25
 */
public class ActionNotExecutableException extends RuntimeException {

    private static final long serialVersionUID = 1523092511328192392L;

    public ActionNotExecutableException() {
    }

    public ActionNotExecutableException(String message) {
        super(message);
    }

    public ActionNotExecutableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionNotExecutableException(Throwable cause) {
        super(cause);
    }

    public ActionNotExecutableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
