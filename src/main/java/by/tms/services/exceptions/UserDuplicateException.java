package by.tms.services.exceptions;

public class UserDuplicateException extends RuntimeException {
    public UserDuplicateException() {
        super();
    }

    public UserDuplicateException(String message) {
        super(message);
    }

    public UserDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserDuplicateException(Throwable cause) {
        super(cause);
    }

    protected UserDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String getMessage() {
        return "We are have this user already!";
    }
}
