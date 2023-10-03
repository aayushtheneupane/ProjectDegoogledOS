package com.android.messaging.datamodel;

import android.net.Uri;
import com.android.messaging.util.C1412P;

/* renamed from: com.android.messaging.datamodel.w */
abstract class C0962w implements C1412P {
    final /* synthetic */ C0963x this$0;

    /* synthetic */ C0962w(C0963x xVar, C0958s sVar) {
        this.this$0 = xVar;
    }

    public Object execute(Object obj) {
        Void voidR = (Void) obj;
        Uri uri = getUri();
        if (uri != null) {
            this.this$0.setUri(uri);
        }
        return C0962w.super.loadInBackground();
    }

    /* access modifiers changed from: protected */
    public abstract Uri getUri();
}
