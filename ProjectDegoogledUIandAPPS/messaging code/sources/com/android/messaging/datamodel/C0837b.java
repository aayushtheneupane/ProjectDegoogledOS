package com.android.messaging.datamodel;

import android.content.Context;
import android.content.CursorLoader;
import android.net.Uri;

/* renamed from: com.android.messaging.datamodel.b */
public class C0837b extends CursorLoader {

    /* renamed from: gc */
    private final String f1088gc;

    public C0837b(String str, Context context, Uri uri, String[] strArr, String str2, String[] strArr2, String str3) {
        super(context, uri, strArr, str2, strArr2, str3);
        this.f1088gc = str;
    }

    public String getBindingId() {
        return this.f1088gc;
    }
}
