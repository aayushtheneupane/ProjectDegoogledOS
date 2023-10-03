package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: cmc */
/* compiled from: PG */
class cmc extends hbr implements ioe {

    /* renamed from: a */
    private volatile ftr f4663a;

    /* renamed from: b */
    public ContextWrapper f4664b;

    /* renamed from: c */
    private final Object f4665c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo3211Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f4664b == null) {
            this.f4664b = new fts(super.mo2634k(), (C0147fh) this, true);
            ckn ckn = (ckn) this;
            ((cku) mo2453b()).mo2395Q();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f4664b;
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
        if (this.f4663a == null) {
            synchronized (this.f4665c) {
                if (this.f4663a == null) {
                    this.f4663a = mo3211Q();
                }
            }
        }
        return this.f4663a.mo2453b();
    }
}
