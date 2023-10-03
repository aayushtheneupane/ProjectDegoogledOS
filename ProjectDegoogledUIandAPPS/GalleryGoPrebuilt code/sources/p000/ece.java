package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;

/* renamed from: ece */
/* compiled from: PG */
class ece extends fwy implements ioe {

    /* renamed from: Z */
    public ContextWrapper f7916Z;

    /* renamed from: aa */
    private volatile ftr f7917aa;

    /* renamed from: ab */
    private final Object f7918ab = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo4673Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f7916Z == null) {
            this.f7916Z = new fts(super.mo2634k(), (C0147fh) this, true);
            ecf ecf = (ecf) this;
            ((ecl) mo2453b()).mo2420ao();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f7916Z;
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
        if (this.f7917aa == null) {
            synchronized (this.f7918ab) {
                if (this.f7917aa == null) {
                    this.f7917aa = mo4673Q();
                }
            }
        }
        return this.f7917aa.mo2453b();
    }
}
