package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: ccg */
/* compiled from: PG */
class ccg extends hbr implements ioe {

    /* renamed from: a */
    public ContextWrapper f4049a;

    /* renamed from: b */
    private volatile ftr f4050b;

    /* renamed from: c */
    private final Object f4051c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo3030Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f4049a == null) {
            this.f4049a = new fts(super.mo2634k(), (C0147fh) this, true);
            ccm ccm = (ccm) this;
            ((cdl) mo2453b()).mo2421ap();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f4049a;
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
        if (this.f4050b == null) {
            synchronized (this.f4051c) {
                if (this.f4050b == null) {
                    this.f4050b = mo3030Q();
                }
            }
        }
        return this.f4050b.mo2453b();
    }
}
