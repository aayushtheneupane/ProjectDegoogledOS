package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: bor */
/* compiled from: PG */
class bor extends hbr implements ioe {

    /* renamed from: a */
    public ContextWrapper f3273a;

    /* renamed from: b */
    private volatile ftr f3274b;

    /* renamed from: c */
    private final Object f3275c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo2629Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f3273a == null) {
            this.f3273a = new fts(super.mo2634k(), (C0147fh) this, true);
            boc boc = (boc) this;
            ((boj) mo2453b()).mo2397S();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f3273a;
        boolean z = true;
        if (!(contextWrapper == null || ftr.m9609a((Context) contextWrapper) == activity)) {
            z = false;
        }
        ife.m12876b(z, (Object) "onAttach called multiple times with different Context! Sting Fragments should not be retained.");
        mo2628P();
    }

    /* renamed from: a */
    public void mo1832a(Context context) {
        super.mo1832a(context);
        mo2628P();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f3274b == null) {
            synchronized (this.f3275c) {
                if (this.f3274b == null) {
                    this.f3274b = mo2629Q();
                }
            }
        }
        return this.f3274b.mo2453b();
    }
}
