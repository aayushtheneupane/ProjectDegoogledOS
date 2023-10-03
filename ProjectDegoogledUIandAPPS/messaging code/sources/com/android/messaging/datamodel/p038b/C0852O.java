package com.android.messaging.datamodel.p038b;

import android.content.Context;
import android.net.Uri;
import com.android.messaging.util.C1424b;

/* renamed from: com.android.messaging.datamodel.b.O */
public class C0852O extends C0884x {

    /* renamed from: JC */
    public final Uri f1106JC;

    public C0852O(Uri uri) {
        C1424b.m3594t(uri);
        this.f1106JC = uri;
    }

    /* renamed from: n */
    public C0883w mo6084n(Context context) {
        return new C0851N(context, this);
    }
}
