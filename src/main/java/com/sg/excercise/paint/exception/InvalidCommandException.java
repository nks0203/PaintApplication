package com.sg.excercise.paint.exception;

public class InvalidCommandException extends Exception {
    private static String message = "Entered command is invalid. Please review the parameters passed and try again";

    public InvalidCommandException() {
        super(message);
    }

    public InvalidCommandException(String message) {
        super(message);
    }

    public InvalidCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCommandException(Throwable cause) {
        super(cause);
    }

    public InvalidCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
