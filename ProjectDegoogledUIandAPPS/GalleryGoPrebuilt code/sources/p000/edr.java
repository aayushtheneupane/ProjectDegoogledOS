package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;

/* renamed from: edr */
/* compiled from: PG */
class edr extends fwy implements ioe {

    /* renamed from: Z */
    public ContextWrapper f8054Z;

    /* renamed from: aa */
    private volatile ftr f8055aa;

    /* renamed from: ab */
    private final Object f8056ab = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo4685Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f8054Z == null) {
            this.f8054Z = new fts(super.mo2634k(), (C0147fh) this, true);
            ecw ecw = (ecw) this;
            ((edg) mo2453b()).mo2415aj();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f8054Z;
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
        if (this.f8055aa == null) {
            synchronized (this.f8056ab) {
                if (this.f8055aa == null) {
                    this.f8055aa = mo4685Q();
                }
            }
        }
        return this.f8055aa.mo2453b();
    }
}
