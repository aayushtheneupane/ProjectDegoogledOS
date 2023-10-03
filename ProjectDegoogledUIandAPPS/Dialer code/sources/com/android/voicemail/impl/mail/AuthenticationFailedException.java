package com.android.voicemail.impl.mail;

public class AuthenticationFailedException extends MessagingException {
    public static final long serialVersionUID = -1;

    public AuthenticationFailedException(String str) {
        super(5, str);
    }

    public AuthenticationFailedException(String str, Throwable th) {
        super(5, str, th);
    }
}
