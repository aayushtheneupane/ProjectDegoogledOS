package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: crn */
/* compiled from: PG */
class crn extends hbr implements ioe {

    /* renamed from: a */
    public ContextWrapper f5495a;

    /* renamed from: b */
    private volatile ftr f5496b;

    /* renamed from: c */
    private final Object f5497c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo3768Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f5495a == null) {
            this.f5495a = new fts(super.mo2634k(), (C0147fh) this, true);
            cqt cqt = (cqt) this;
            ((cri) mo2453b()).mo2406aa();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f5495a;
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
        if (this.f5496b == null) {
            synchronized (this.f5497c) {
                if (this.f5496b == null) {
                    this.f5496b = mo3768Q();
                }
            }
        }
        return this.f5496b.mo2453b();
    }
}
