package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: eig */
/* compiled from: PG */
class eig extends hbr implements ioe {

    /* renamed from: a */
    public ContextWrapper f8336a;

    /* renamed from: b */
    private volatile ftr f8337b;

    /* renamed from: c */
    private final Object f8338c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo4815Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f8336a == null) {
            this.f8336a = new fts(super.mo2634k(), (C0147fh) this, true);
            eht eht = (eht) this;
            ((eia) mo2453b()).mo2398T();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f8336a;
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
        if (this.f8337b == null) {
            synchronized (this.f8338c) {
                if (this.f8337b == null) {
                    this.f8337b = mo4815Q();
                }
            }
        }
        return this.f8337b.mo2453b();
    }
}
