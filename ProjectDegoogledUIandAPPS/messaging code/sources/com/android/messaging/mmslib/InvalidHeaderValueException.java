package com.android.messaging.mmslib;

public class InvalidHeaderValueException extends MmsException {
    private static final long serialVersionUID = -2053384496042052262L;

    public InvalidHeaderValueException() {
    }

    public InvalidHeaderValueException(String str) {
        super(str);
    }
}
