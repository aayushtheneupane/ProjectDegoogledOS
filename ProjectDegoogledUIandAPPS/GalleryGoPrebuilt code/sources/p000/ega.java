package p000;

import android.net.Uri;

/* renamed from: ega */
/* compiled from: PG */
final /* synthetic */ class ega implements hpr {

    /* renamed from: a */
    private final String f8187a;

    /* renamed from: b */
    private final Uri f8188b;

    public ega(String str, Uri uri) {
        this.f8187a = str;
        this.f8188b = uri;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        String str = this.f8187a;
        Uri uri = this.f8188b;
        iir a = efy.f8178b.mo8788a((iix) (efy) obj);
        String uri2 = uri.toString();
        str.getClass();
        uri2.getClass();
        if (a.f14319c) {
            a.mo8751b();
            a.f14319c = false;
        }
        efy efy = (efy) a.f14318b;
        ijy ijy = efy.f8180a;
        if (!ijy.f14369a) {
            efy.f8180a = ijy.mo8831a();
        }
        efy.f8180a.put(str, uri2);
        return (efy) a.mo8770g();
    }
}
