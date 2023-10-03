package com.android.messaging.datamodel.p038b;

import android.content.Context;
import android.net.Uri;
import com.android.messaging.util.C1424b;
import com.android.messaging.util.C1426c;
import com.android.messaging.util.C1488za;

/* renamed from: com.android.messaging.datamodel.b.d */
public class C0864d extends C0849L {

    /* renamed from: GC */
    final boolean f1116GC;

    public C0864d(Uri uri, int i, int i2, boolean z) {
        this(uri, i, i2, z, false);
    }

    /* renamed from: n */
    public C0883w mo6084n(Context context) {
        Uri uri = this.uri;
        if ("s".equals(uri == null ? null : C1426c.m3603p(uri))) {
            return new C0847J(context, this);
        }
        return new C0863c(context, this);
    }

    public C0864d(Uri uri, int i, int i2, boolean z, boolean z2) {
        super(uri, i, i2, false, true, z, 0, 0);
        C1424b.m3592ia(uri == null || C1488za.m3877z(uri) || C1426c.m3607t(uri));
        this.f1116GC = z2;
    }
}
