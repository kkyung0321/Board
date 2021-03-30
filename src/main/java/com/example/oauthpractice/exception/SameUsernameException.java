package com.example.oauthpractice.exception;

public class SameUsernameException extends RuntimeException {
    public SameUsernameException() {
        super();
    }

    public SameUsernameException(String s) {
        super(s);
    }

    public SameUsernameException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SameUsernameException(Throwable throwable) {
        super(throwable);
    }

    protected SameUsernameException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
