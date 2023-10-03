package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: eaf */
/* compiled from: PG */
class eaf extends hbr implements ioe {

    /* renamed from: a */
    public ContextWrapper f7763a;

    /* renamed from: b */
    private volatile ftr f7764b;

    /* renamed from: c */
    private final Object f7765c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo4619Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f7763a == null) {
            this.f7763a = new fts(super.mo2634k(), (C0147fh) this, true);
            dzq dzq = (dzq) this;
            ((dzw) mo2453b()).mo2402X();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f7763a;
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
        if (this.f7764b == null) {
            synchronized (this.f7765c) {
                if (this.f7764b == null) {
                    this.f7764b = mo4619Q();
                }
            }
        }
        return this.f7764b.mo2453b();
    }
}
