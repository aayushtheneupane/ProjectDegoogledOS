package p000;

import android.graphics.Bitmap;

/* renamed from: azk */
/* compiled from: PG */
public final class azk implements aua, atv {

    /* renamed from: a */
    private final Bitmap f1907a;

    /* renamed from: b */
    private final auk f1908b;

    public azk(Bitmap bitmap, auk auk) {
        this.f1907a = (Bitmap) cns.m4633a((Object) bitmap, "Bitmap must not be null");
        this.f1908b = (auk) cns.m4633a((Object) auk, "BitmapPool must not be null");
    }

    /* renamed from: a */
    public final Class mo1604a() {
        return Bitmap.class;
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo1605b() {
        return this.f1907a;
    }

    /* renamed from: c */
    public final int mo1606c() {
        return bfp.m2426a(this.f1907a);
    }

    /* renamed from: e */
    public final void mo1621e() {
        this.f1907a.prepareToDraw();
    }

    /* renamed from: a */
    public static azk m1961a(Bitmap bitmap, auk auk) {
        if (bitmap != null) {
            return new azk(bitmap, auk);
        }
        return null;
    }

    /* renamed from: d */
    public final void mo1607d() {
        this.f1908b.mo1645a(this.f1907a);
    }
}
