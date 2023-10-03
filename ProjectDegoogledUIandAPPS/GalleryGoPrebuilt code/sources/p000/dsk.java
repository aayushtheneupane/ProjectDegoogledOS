package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: dsk */
/* compiled from: PG */
class dsk extends hbr implements ioe {

    /* renamed from: a */
    public ContextWrapper f7278a;

    /* renamed from: b */
    private volatile ftr f7279b;

    /* renamed from: c */
    private final Object f7280c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo4386Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f7278a == null) {
            this.f7278a = new fts(super.mo2634k(), (C0147fh) this, false);
            dsh dsh = (dsh) this;
            ((dsj) mo2453b()).mo2451c();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f7278a;
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
        if (this.f7279b == null) {
            synchronized (this.f7280c) {
                if (this.f7279b == null) {
                    this.f7279b = mo4386Q();
                }
            }
        }
        return this.f7279b.mo2453b();
    }
}
