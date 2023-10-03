package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;

/* renamed from: bqx */
/* compiled from: PG */
class bqx extends fwy implements ioe {

    /* renamed from: Z */
    public ContextWrapper f3402Z;

    /* renamed from: aa */
    private volatile ftr f3403aa;

    /* renamed from: ab */
    private final Object f3404ab = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo2664Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f3402Z == null) {
            this.f3402Z = new fts(super.mo2634k(), (C0147fh) this, true);
            bqd bqd = (bqd) this;
            ((bqi) mo2453b()).mo2399U();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f3402Z;
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
        if (this.f3403aa == null) {
            synchronized (this.f3404ab) {
                if (this.f3403aa == null) {
                    this.f3403aa = mo2664Q();
                }
            }
        }
        return this.f3403aa.mo2453b();
    }
}
