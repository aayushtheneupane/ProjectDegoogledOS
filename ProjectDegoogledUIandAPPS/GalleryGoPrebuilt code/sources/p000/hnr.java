package p000;

import android.graphics.drawable.Drawable;

/* renamed from: hnr */
/* compiled from: PG */
public final class hnr implements ber {

    /* renamed from: a */
    public final ber f13120a;

    /* renamed from: b */
    private final hmu f13121b = hmu.m11755a(hnf.f13084a);

    private hnr(ber ber) {
        this.f13120a = ber;
    }

    /* renamed from: a */
    public static ber m11806a(ber ber) {
        return new hnr(ber);
    }

    /* renamed from: ae */
    public final bea mo1896ae() {
        return this.f13120a.mo1896ae();
    }

    /* renamed from: a */
    public final void mo1895a(beq beq) {
        this.f13120a.mo1895a(beq);
    }

    /* renamed from: d */
    public final void mo1444d() {
        this.f13120a.mo1444d();
    }

    /* renamed from: b */
    public final void mo1798b(Drawable drawable) {
        m11807a((Runnable) new hnq(this, drawable));
    }

    /* renamed from: a */
    public final void mo1432a(Drawable drawable) {
        m11807a((Runnable) new hno(this, drawable));
    }

    /* renamed from: c */
    public final void mo1898c(Drawable drawable) {
        m11807a((Runnable) new hnn(this, drawable));
    }

    /* renamed from: a */
    public final void mo1433a(Object obj, bex bex) {
        m11807a((Runnable) new hnp(this, obj, bex));
    }

    /* renamed from: b */
    public final void mo1442b() {
        this.f13120a.mo1442b();
    }

    /* renamed from: c */
    public final void mo1443c() {
        this.f13120a.mo1443c();
    }

    /* renamed from: a */
    private final void m11807a(Runnable runnable) {
        if (!hnb.m11774a(hnf.f13084a)) {
            hmq.m11747a(this.f13121b.f13065a, runnable).run();
        } else {
            runnable.run();
        }
    }

    /* renamed from: b */
    public final void mo1897b(beq beq) {
        this.f13120a.mo1897b(beq);
    }

    /* renamed from: a */
    public final void mo1894a(bea bea) {
        this.f13120a.mo1894a(bea);
    }
}
