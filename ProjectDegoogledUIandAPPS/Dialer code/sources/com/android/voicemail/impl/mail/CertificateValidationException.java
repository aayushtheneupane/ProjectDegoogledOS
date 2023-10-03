package com.android.voicemail.impl.mail;

public class CertificateValidationException extends MessagingException {
    public static final long serialVersionUID = -1;

    public CertificateValidationException(String str) {
        super(10, str);
    }

    public CertificateValidationException(String str, Throwable th) {
        super(10, str, th);
    }
}
