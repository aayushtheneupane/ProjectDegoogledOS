package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: cmd */
/* compiled from: PG */
class cmd extends hbr implements ioe {

    /* renamed from: a */
    private volatile ftr f4666a;

    /* renamed from: b */
    public ContextWrapper f4667b;

    /* renamed from: c */
    private final Object f4668c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo3229Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f4667b == null) {
            this.f4667b = new fts(super.mo2634k(), (C0147fh) this, true);
            cla cla = (cla) this;
            ((clv) mo2453b()).mo2404Z();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f4667b;
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
        if (this.f4666a == null) {
            synchronized (this.f4668c) {
                if (this.f4666a == null) {
                    this.f4666a = mo3229Q();
                }
            }
        }
        return this.f4666a.mo2453b();
    }
}
