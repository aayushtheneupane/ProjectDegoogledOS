package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: bqz */
/* compiled from: PG */
class bqz extends hbr implements ioe {

    /* renamed from: a */
    public ContextWrapper f3408a;

    /* renamed from: b */
    private volatile ftr f3409b;

    /* renamed from: c */
    private final Object f3410c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo2685Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f3408a == null) {
            this.f3408a = new fts(super.mo2634k(), (C0147fh) this, true);
            bra bra = (bra) this;
            ((brd) mo2453b()).mo2419an();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f3408a;
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
        if (this.f3409b == null) {
            synchronized (this.f3410c) {
                if (this.f3409b == null) {
                    this.f3409b = mo2685Q();
                }
            }
        }
        return this.f3409b.mo2453b();
    }
}
