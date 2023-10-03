package com.android.messaging.sms;

class SmsException extends Exception {
    private static final long serialVersionUID = 1;

    public SmsException() {
    }

    public SmsException(String str) {
        super(str);
    }
}
