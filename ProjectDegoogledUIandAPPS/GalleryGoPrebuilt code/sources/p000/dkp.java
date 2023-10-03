package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;

/* renamed from: dkp */
/* compiled from: PG */
class dkp extends fwy implements ioe {

    /* renamed from: Z */
    public ContextWrapper f6738Z;

    /* renamed from: aa */
    private volatile ftr f6739aa;

    /* renamed from: ab */
    private final Object f6740ab = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo4174Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f6738Z == null) {
            this.f6738Z = new fts(super.mo2634k(), (C0147fh) this, true);
            dkb dkb = (dkb) this;
            ((dkh) mo2453b()).mo2408ac();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f6738Z;
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
        if (this.f6739aa == null) {
            synchronized (this.f6740ab) {
                if (this.f6739aa == null) {
                    this.f6739aa = mo4174Q();
                }
            }
        }
        return this.f6739aa.mo2453b();
    }
}
