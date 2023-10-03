package p000;

import android.content.Context;
import android.net.Uri;

/* renamed from: ayz */
/* compiled from: PG */
public final class ayz implements axo {

    /* renamed from: a */
    private final Context f1879a;

    public ayz(Context context) {
        this.f1879a = context.getApplicationContext();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ axn mo1697a(Object obj, int i, int i2, aqz aqz) {
        Long l;
        Uri uri = (Uri) obj;
        if (!abj.m109a(i, i2) || (l = (Long) aqz.mo1502a(bbf.f1988a)) == null || l.longValue() != -1) {
            return null;
        }
        bfa bfa = new bfa(uri);
        Context context = this.f1879a;
        return new axn(bfa, asc.m1549a(context, uri, new asb(context.getContentResolver())));
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1698a(Object obj) {
        Uri uri = (Uri) obj;
        return abj.m110a(uri) && abj.m118b(uri);
    }
}
