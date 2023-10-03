package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;

/* renamed from: cci */
/* compiled from: PG */
class cci extends fwy implements ioe {

    /* renamed from: Z */
    public ContextWrapper f4053Z;

    /* renamed from: aa */
    private volatile ftr f4054aa;

    /* renamed from: ab */
    private final Object f4055ab = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo3032Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f4053Z == null) {
            this.f4053Z = new fts(super.mo2634k(), (C0147fh) this, true);
            cdr cdr = (cdr) this;
            ((cdw) mo2453b()).mo2422aq();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f4053Z;
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
    public LayoutInflater mo2633b(Bundle bundle) {
        return LayoutInflater.from(new fts(super.mo2633b(bundle), (C0147fh) this, true));
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f4054aa == null) {
            synchronized (this.f4055ab) {
                if (this.f4054aa == null) {
                    this.f4054aa = mo3032Q();
                }
            }
        }
        return this.f4054aa.mo2453b();
    }
}
