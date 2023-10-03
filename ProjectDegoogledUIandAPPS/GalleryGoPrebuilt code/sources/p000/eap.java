package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;

/* renamed from: eap */
/* compiled from: PG */
class eap extends fwy implements ioe {

    /* renamed from: Z */
    public ContextWrapper f7792Z;

    /* renamed from: aa */
    private volatile ftr f7793aa;

    /* renamed from: ab */
    private final Object f7794ab = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: R */
    public ftr mo4627R() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f7792Z == null) {
            this.f7792Z = new fts(super.mo2634k(), (C0147fh) this, true);
            eaj eaj = (eaj) this;
            ((eao) mo2453b()).mo2413ah();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f7792Z;
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
        if (this.f7793aa == null) {
            synchronized (this.f7794ab) {
                if (this.f7793aa == null) {
                    this.f7793aa = mo4627R();
                }
            }
        }
        return this.f7793aa.mo2453b();
    }
}
