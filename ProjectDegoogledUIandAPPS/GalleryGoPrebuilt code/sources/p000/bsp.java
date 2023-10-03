package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: bsp */
/* compiled from: PG */
class bsp extends hbr implements ioe {

    /* renamed from: a */
    public ContextWrapper f3494a;

    /* renamed from: b */
    private volatile ftr f3495b;

    /* renamed from: c */
    private final Object f3496c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo2704Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f3494a == null) {
            this.f3494a = new fts(super.mo2634k(), (C0147fh) this, true);
            brk brk = (brk) this;
            ((brs) mo2453b()).mo2401W();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f3494a;
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
        if (this.f3495b == null) {
            synchronized (this.f3496c) {
                if (this.f3495b == null) {
                    this.f3495b = mo2704Q();
                }
            }
        }
        return this.f3495b.mo2453b();
    }
}
