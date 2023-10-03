package com.android.messaging.sms;

import com.android.messaging.util.C1424b;

public class MmsFailureException extends Exception {
    private static final long serialVersionUID = 1;
    public final int rawStatus;
    public final int retryHint;

    public MmsFailureException(int i, int i2) {
        this.retryHint = i;
        m2344Vo();
        this.rawStatus = i2;
    }

    /* renamed from: Vo */
    private void m2344Vo() {
        int i = this.retryHint;
        boolean z = true;
        if (!(i == 1 || i == 2 || i == 3)) {
            z = false;
        }
        C1424b.m3592ia(z);
    }

    public MmsFailureException(int i, String str) {
        super(str);
        this.retryHint = i;
        m2344Vo();
        this.rawStatus = 0;
    }
}
