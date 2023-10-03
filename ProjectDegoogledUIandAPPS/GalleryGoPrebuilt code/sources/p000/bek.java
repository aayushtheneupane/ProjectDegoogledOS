package p000;

import android.graphics.drawable.Drawable;
import android.support.p002v7.widget.RecyclerView;

/* renamed from: bek */
/* compiled from: PG */
public abstract class bek implements ber {

    /* renamed from: a */
    private bea f2175a;

    public bek() {
        if (!bfp.m2431a((int) RecyclerView.UNDEFINED_DURATION, (int) RecyclerView.UNDEFINED_DURATION)) {
            StringBuilder sb = new StringBuilder(111);
            sb.append("Width and height must both be > 0 or Target#SIZE_ORIGINAL, but given width: -2147483648 and height: -2147483648");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* renamed from: a */
    public void mo1432a(Drawable drawable) {
    }

    /* renamed from: ae */
    public final bea mo1896ae() {
        return this.f2175a;
    }

    /* renamed from: b */
    public final void mo1442b() {
    }

    /* renamed from: b */
    public final void mo1897b(beq beq) {
    }

    /* renamed from: c */
    public final void mo1443c() {
    }

    /* renamed from: c */
    public void mo1898c(Drawable drawable) {
    }

    /* renamed from: d */
    public final void mo1444d() {
    }

    /* renamed from: a */
    public final void mo1895a(beq beq) {
        beq.mo1906a(RecyclerView.UNDEFINED_DURATION, RecyclerView.UNDEFINED_DURATION);
    }

    /* renamed from: a */
    public final void mo1894a(bea bea) {
        this.f2175a = bea;
    }
}
