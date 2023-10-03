package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: dva */
/* compiled from: PG */
class dva extends hbr implements ioe {

    /* renamed from: a */
    public ContextWrapper f7443a;

    /* renamed from: b */
    private volatile ftr f7444b;

    /* renamed from: c */
    private final Object f7445c = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo4456Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f7443a == null) {
            this.f7443a = new fts(super.mo2634k(), (C0147fh) this, true);
            duc duc = (duc) this;
            ((duk) mo2453b()).mo2410ae();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f7443a;
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
        if (this.f7444b == null) {
            synchronized (this.f7445c) {
                if (this.f7444b == null) {
                    this.f7444b = mo4456Q();
                }
            }
        }
        return this.f7444b.mo2453b();
    }
}
