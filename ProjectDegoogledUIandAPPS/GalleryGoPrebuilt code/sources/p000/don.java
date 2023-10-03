package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: don */
/* compiled from: PG */
class don extends hbr implements ioe {

    /* renamed from: a */
    private volatile ftr f6945a;

    /* renamed from: b */
    public ContextWrapper f6946b;

    /* renamed from: c */
    private final Object f6947c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo4240Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f6946b == null) {
            this.f6946b = new fts(super.mo2634k(), (C0147fh) this, true);
            dmb dmb = (dmb) this;
            ((dns) mo2453b()).mo2409ad();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f6946b;
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
        if (this.f6945a == null) {
            synchronized (this.f6947c) {
                if (this.f6945a == null) {
                    this.f6945a = mo4240Q();
                }
            }
        }
        return this.f6945a.mo2453b();
    }
}
