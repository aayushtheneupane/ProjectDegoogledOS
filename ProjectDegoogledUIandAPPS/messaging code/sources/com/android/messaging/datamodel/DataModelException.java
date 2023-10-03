package com.android.messaging.datamodel;

public class DataModelException extends Exception {
    private static final long serialVersionUID = 1;
    private final long mBackoff;
    private final int mErrorCode;
    private final boolean mIsInjection;
    private final String mMessage;

    public String getMessage() {
        return this.mMessage;
    }
}
