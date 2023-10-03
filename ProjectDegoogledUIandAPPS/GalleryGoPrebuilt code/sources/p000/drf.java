package p000;

import android.net.Uri;

/* renamed from: drf */
/* compiled from: PG */
final class drf {

    /* renamed from: a */
    public final Object f7206a;

    /* renamed from: b */
    public final bfa f7207b;

    /* renamed from: c */
    public final Uri f7208c;

    public drf(Object obj, bfa bfa, Uri uri) {
        this.f7206a = obj;
        this.f7207b = bfa;
        this.f7208c = uri;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7208c);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 24);
        sb.append("UriAndCacheKey[loadUri=");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }
}
