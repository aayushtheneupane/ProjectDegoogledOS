package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;

/* renamed from: efw */
/* compiled from: PG */
class efw extends fwy implements ioe {

    /* renamed from: Z */
    public ContextWrapper f8174Z;

    /* renamed from: aa */
    private volatile ftr f8175aa;

    /* renamed from: ab */
    private final Object f8176ab = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo4789Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f8174Z == null) {
            this.f8174Z = new fts(super.mo2634k(), (C0147fh) this, true);
            ege ege = (ege) this;
            ((egi) mo2453b()).mo2418am();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f8174Z;
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
        if (this.f8175aa == null) {
            synchronized (this.f8176ab) {
                if (this.f8175aa == null) {
                    this.f8175aa = mo4789Q();
                }
            }
        }
        return this.f8175aa.mo2453b();
    }
}
