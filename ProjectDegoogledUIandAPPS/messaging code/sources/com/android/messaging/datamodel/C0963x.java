package com.android.messaging.datamodel;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.android.messaging.util.C1413Q;

/* renamed from: com.android.messaging.datamodel.x */
class C0963x extends C0837b {
    /* access modifiers changed from: private */

    /* renamed from: hc */
    public final Uri f1378hc;

    C0963x(String str, Context context, Uri uri, String[] strArr, String str2, String[] strArr2, String str3) {
        super(str, context, uri, strArr, str2, strArr2, str3);
        this.f1378hc = uri;
    }

    public Cursor loadInBackground() {
        C1413Q a = C1413Q.m3556a(new C0961v(this, (C0958s) null));
        a.mo8045b(new C0959t(this, (C0958s) null));
        a.mo8045b(new C0960u(this, (C0958s) null));
        return (Cursor) a.execute((Object) null);
    }

    /* renamed from: loadInBackground  reason: collision with other method in class */
    public Object m4705loadInBackground() {
        C1413Q a = C1413Q.m3556a(new C0961v(this, (C0958s) null));
        a.mo8045b(new C0959t(this, (C0958s) null));
        a.mo8045b(new C0960u(this, (C0958s) null));
        return (Cursor) a.execute((Object) null);
    }
}
