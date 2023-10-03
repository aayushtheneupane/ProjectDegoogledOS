package p000;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/* renamed from: bcg */
/* compiled from: PG */
public final class bcg implements bci {

    /* renamed from: a */
    private final auk f2049a;

    /* renamed from: b */
    private final bci f2050b;

    /* renamed from: c */
    private final bci f2051c;

    public bcg(auk auk, bci bci, bci bci2) {
        this.f2049a = auk;
        this.f2050b = bci;
        this.f2051c = bci2;
    }

    /* renamed from: a */
    public final aua mo1806a(aua aua, aqz aqz) {
        Drawable drawable = (Drawable) aua.mo1605b();
        if (drawable instanceof BitmapDrawable) {
            return this.f2050b.mo1806a(azk.m1961a(((BitmapDrawable) drawable).getBitmap(), this.f2049a), aqz);
        }
        if (drawable instanceof bbt) {
            return this.f2051c.mo1806a(aua, aqz);
        }
        return null;
    }
}
