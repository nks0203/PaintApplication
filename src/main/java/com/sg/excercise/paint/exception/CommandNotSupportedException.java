package com.sg.excercise.paint.exception;

public class CommandNotSupportedException extends Exception {
    public CommandNotSupportedException() {
    }

    public CommandNotSupportedException(String message) {
        super(message);
    }

    public CommandNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandNotSupportedException(Throwable cause) {
        super(cause);
    }

    public CommandNotSupportedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
