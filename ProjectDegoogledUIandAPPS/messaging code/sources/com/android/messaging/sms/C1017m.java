package com.android.messaging.sms;

import android.text.TextUtils;

/* renamed from: com.android.messaging.sms.m */
public abstract class C1017m {
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof C1017m)) {
            return false;
        }
        return TextUtils.equals(getUri(), ((C1017m) obj).getUri());
    }

    public abstract int getProtocol();

    public abstract String getUri();

    public int hashCode() {
        return getUri().hashCode();
    }

    /* renamed from: hi */
    public abstract long mo6791hi();
}
