package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: ctl */
/* compiled from: PG */
class ctl extends hbr implements ioe {

    /* renamed from: a */
    public ContextWrapper f5633a;

    /* renamed from: b */
    private volatile ftr f5634b;

    /* renamed from: c */
    private final Object f5635c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo3805Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f5633a == null) {
            this.f5633a = new fts(super.mo2634k(), (C0147fh) this, true);
            css css = (css) this;
            ((csw) mo2453b()).mo2407ab();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f5633a;
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
        if (this.f5634b == null) {
            synchronized (this.f5635c) {
                if (this.f5634b == null) {
                    this.f5634b = mo3805Q();
                }
            }
        }
        return this.f5634b.mo2453b();
    }
}
