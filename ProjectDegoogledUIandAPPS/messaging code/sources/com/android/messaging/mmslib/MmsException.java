package com.android.messaging.mmslib;

public class MmsException extends Exception {
    private static final long serialVersionUID = -7323249827281485390L;

    public MmsException() {
    }

    public MmsException(String str) {
        super(str);
    }

    public MmsException(Throwable th) {
        super(th);
    }
}
