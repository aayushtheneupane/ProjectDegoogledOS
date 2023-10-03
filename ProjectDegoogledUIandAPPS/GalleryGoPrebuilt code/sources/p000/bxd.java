package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: bxd */
/* compiled from: PG */
class bxd extends hbr implements ioe {

    /* renamed from: a */
    private volatile ftr f3814a;

    /* renamed from: b */
    public ContextWrapper f3815b;

    /* renamed from: c */
    private final Object f3816c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo2773Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f3815b == null) {
            this.f3815b = new fts(super.mo2634k(), (C0147fh) this, true);
            buk buk = (buk) this;
            ((bvy) mo2453b()).mo2403Y();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f3815b;
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
        if (this.f3814a == null) {
            synchronized (this.f3816c) {
                if (this.f3814a == null) {
                    this.f3814a = mo2773Q();
                }
            }
        }
        return this.f3814a.mo2453b();
    }
}
