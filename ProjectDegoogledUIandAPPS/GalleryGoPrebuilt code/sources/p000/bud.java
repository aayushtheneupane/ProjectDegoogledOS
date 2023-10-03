package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: bud */
/* compiled from: PG */
class bud extends hbr implements ioe {

    /* renamed from: a */
    public ContextWrapper f3609a;

    /* renamed from: b */
    private volatile ftr f3610b;

    /* renamed from: c */
    private final Object f3611c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo2757Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f3609a == null) {
            this.f3609a = new fts(super.mo2634k(), (C0147fh) this, true);
            bts bts = (bts) this;
            ((buc) mo2453b()).mo2416ak();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f3609a;
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
        if (this.f3610b == null) {
            synchronized (this.f3611c) {
                if (this.f3610b == null) {
                    this.f3610b = mo2757Q();
                }
            }
        }
        return this.f3610b.mo2453b();
    }
}
