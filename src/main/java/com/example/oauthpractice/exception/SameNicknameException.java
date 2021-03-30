package com.example.oauthpractice.exception;

public class SameNicknameException extends RuntimeException {

    public SameNicknameException() {
        super();
    }

    public SameNicknameException(String s) {
        super(s);
    }

    public SameNicknameException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SameNicknameException(Throwable throwable) {
        super(throwable);
    }

    protected SameNicknameException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
