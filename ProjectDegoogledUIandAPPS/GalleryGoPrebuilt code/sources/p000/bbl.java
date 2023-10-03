package p000;

import android.graphics.drawable.Drawable;

/* renamed from: bbl */
/* compiled from: PG */
final class bbl extends bbk {
    private bbl(Drawable drawable) {
        super(drawable);
    }

    /* renamed from: d */
    public final void mo1607d() {
    }

    /* renamed from: a */
    public final Class mo1604a() {
        return this.f1995a.getClass();
    }

    /* renamed from: c */
    public final int mo1606c() {
        return Math.max(1, (this.f1995a.getIntrinsicWidth() * this.f1995a.getIntrinsicHeight()) << 2);
    }

    /* renamed from: a */
    static aua m2101a(Drawable drawable) {
        if (drawable != null) {
            return new bbl(drawable);
        }
        return null;
    }
}
