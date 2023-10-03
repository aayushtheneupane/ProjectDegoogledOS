package com.android.messaging.datamodel.data;

import android.text.TextUtils;

/* renamed from: com.android.messaging.datamodel.data.r */
public class C0935r {

    /* renamed from: jy */
    private String f1273jy;

    /* renamed from: a */
    public boolean mo6524a(C0936s sVar) {
        boolean z = !TextUtils.equals(this.f1273jy, sVar.getMessageId());
        this.f1273jy = sVar.getMessageId();
        return z;
    }
}
