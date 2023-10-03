package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: cnb */
/* compiled from: PG */
class cnb extends hbr implements ioe {

    /* renamed from: a */
    private volatile ftr f4717a;

    /* renamed from: b */
    public ContextWrapper f4718b;

    /* renamed from: c */
    private final Object f4719c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo3258Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f4718b == null) {
            this.f4718b = new fts(super.mo2634k(), (C0147fh) this, true);
            cmn cmn = (cmn) this;
            ((cna) mo2453b()).mo2412ag();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f4718b;
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
        if (this.f4717a == null) {
            synchronized (this.f4719c) {
                if (this.f4717a == null) {
                    this.f4717a = mo3258Q();
                }
            }
        }
        return this.f4717a.mo2453b();
    }
}
