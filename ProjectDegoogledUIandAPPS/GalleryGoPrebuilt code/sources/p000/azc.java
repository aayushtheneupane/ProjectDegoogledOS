package p000;

import android.content.Context;
import android.net.Uri;
import android.os.Build;

/* renamed from: azc */
/* compiled from: PG */
public final class azc implements axo {

    /* renamed from: a */
    private final Context f1893a;

    /* renamed from: b */
    private final axo f1894b;

    /* renamed from: c */
    private final axo f1895c;

    /* renamed from: d */
    private final Class f1896d;

    public azc(Context context, axo axo, axo axo2, Class cls) {
        this.f1893a = context.getApplicationContext();
        this.f1894b = axo;
        this.f1895c = axo2;
        this.f1896d = cls;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ axn mo1697a(Object obj, int i, int i2, aqz aqz) {
        Uri uri = (Uri) obj;
        return new axn(new bfa(uri), new azb(this.f1893a, this.f1894b, this.f1895c, uri, i, i2, aqz, this.f1896d));
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1698a(Object obj) {
        return Build.VERSION.SDK_INT >= 29 && abj.m110a((Uri) obj);
    }
}
