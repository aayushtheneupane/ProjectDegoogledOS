package com.android.voicemail.impl.mail;

public class MessagingException extends Exception {
    public static final long serialVersionUID = -1;
    protected Object exceptionData;
    protected int exceptionType;

    public MessagingException(int i, String str) {
        super(str, (Throwable) null);
        this.exceptionType = i;
        this.exceptionData = null;
    }

    public MessagingException(int i, String str, Throwable th) {
        super(str, th);
        this.exceptionType = i;
        this.exceptionData = null;
    }

    public MessagingException(String str) {
        this(0, str, (Throwable) null);
    }
}
