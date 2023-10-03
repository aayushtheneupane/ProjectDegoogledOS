package p000;

import android.content.Context;
import android.net.Uri;

/* renamed from: axi */
/* compiled from: PG */
public final class axi implements axo {

    /* renamed from: a */
    private final Context f1825a;

    public axi(Context context) {
        this.f1825a = context;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ axn mo1697a(Object obj, int i, int i2, aqz aqz) {
        Uri uri = (Uri) obj;
        return new axn(new bfa(uri), new axh(this.f1825a, uri));
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1698a(Object obj) {
        return abj.m110a((Uri) obj);
    }
}
