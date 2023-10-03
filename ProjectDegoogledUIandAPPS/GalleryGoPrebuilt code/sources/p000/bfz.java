package p000;

import android.graphics.Bitmap;
import android.net.Uri;

/* renamed from: bfz */
/* compiled from: PG */
public final class bfz {

    /* renamed from: a */
    public final Uri f2226a;

    /* renamed from: b */
    public final Bitmap f2227b;

    /* renamed from: c */
    public final Integer f2228c;

    /* renamed from: d */
    public boolean f2229d;

    /* renamed from: e */
    public int f2230e;

    /* renamed from: f */
    public int f2231f;

    public bfz(int i) {
        this.f2227b = null;
        this.f2226a = null;
        this.f2228c = Integer.valueOf(i);
        this.f2229d = true;
    }

    public bfz(Bitmap bitmap) {
        this.f2227b = bitmap;
        this.f2226a = null;
        this.f2228c = null;
        this.f2229d = false;
        this.f2230e = bitmap.getWidth();
        this.f2231f = bitmap.getHeight();
    }

    public bfz(Uri uri) {
        this.f2227b = null;
        this.f2226a = uri;
        this.f2228c = null;
        this.f2229d = true;
    }

    /* renamed from: a */
    public final void mo1974a() {
        this.f2229d = true;
    }

    /* renamed from: a */
    public static bfz m2453a(Uri uri) {
        return new bfz(uri);
    }
}
