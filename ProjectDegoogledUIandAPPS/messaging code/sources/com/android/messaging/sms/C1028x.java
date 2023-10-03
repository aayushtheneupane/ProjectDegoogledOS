package com.android.messaging.sms;

import android.net.Uri;

/* renamed from: com.android.messaging.sms.x */
public class C1028x {
    public final int rawStatus;
    public final int resultCode;
    public final int status;
    public final Uri uri;

    public C1028x(int i, int i2, Uri uri2) {
        this.status = i;
        this.rawStatus = i2;
        this.uri = uri2;
        this.resultCode = 0;
    }

    public C1028x(int i, int i2, Uri uri2, int i3) {
        this.status = i;
        this.rawStatus = i2;
        this.uri = uri2;
        this.resultCode = i3;
    }
}
