package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: dyb */
/* compiled from: PG */
class dyb extends hbr implements ioe {

    /* renamed from: a */
    private volatile ftr f7635a;

    /* renamed from: b */
    public ContextWrapper f7636b;

    /* renamed from: c */
    private final Object f7637c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: R */
    public ftr mo4523R() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f7636b == null) {
            this.f7636b = new fts(super.mo2634k(), (C0147fh) this, true);
            dwh dwh = (dwh) this;
            ((dwt) mo2453b()).mo2411af();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f7636b;
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
        if (this.f7635a == null) {
            synchronized (this.f7637c) {
                if (this.f7635a == null) {
                    this.f7635a = mo4523R();
                }
            }
        }
        return this.f7635a.mo2453b();
    }
}
