package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;

/* renamed from: djx */
/* compiled from: PG */
class djx extends fwy implements ioe {

    /* renamed from: Z */
    public ContextWrapper f6693Z;

    /* renamed from: aa */
    private volatile ftr f6694aa;

    /* renamed from: ab */
    private final Object f6695ab = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public ftr mo4166Q() {
        throw null;
    }

    /* renamed from: P */
    private final void mo2628P() {
        if (this.f6693Z == null) {
            this.f6693Z = new fts(super.mo2634k(), (C0147fh) this, true);
            djq djq = (djq) this;
            ((djw) mo2453b()).mo2417al();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f6693Z;
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
        if (this.f6694aa == null) {
            synchronized (this.f6695ab) {
                if (this.f6694aa == null) {
                    this.f6694aa = mo4166Q();
                }
            }
        }
        return this.f6694aa.mo2453b();
    }
}
