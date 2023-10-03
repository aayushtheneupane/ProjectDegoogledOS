package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: dpw */
/* compiled from: PG */
class dpw extends hbr implements ioe {

    /* renamed from: a */
    public ContextWrapper f7035a;

    /* renamed from: b */
    private volatile ftr f7036b;

    /* renamed from: c */
    private final Object f7037c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo4304Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f7035a == null) {
            this.f7035a = new fts(super.mo2634k(), (C0147fh) this, true);
            dov dov = (dov) this;
            ((dpv) mo2453b()).mo2396R();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f7035a;
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
        if (this.f7036b == null) {
            synchronized (this.f7037c) {
                if (this.f7036b == null) {
                    this.f7036b = mo4304Q();
                }
            }
        }
        return this.f7036b.mo2453b();
    }
}
