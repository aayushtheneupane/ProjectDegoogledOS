package p000;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

/* renamed from: ecd */
/* compiled from: PG */
abstract class ecd extends fwx implements ioe {

    /* renamed from: ad */
    public ContextWrapper f7913ad;

    /* renamed from: af */
    private volatile ftr f7914af;

    /* renamed from: ag */
    private final Object f7915ag = new Object();

    /* access modifiers changed from: protected */
    /* renamed from: T */
    public ftr mo4666T() {
        throw null;
    }

    /* renamed from: U */
    private final void m7146U() {
        if (this.f7913ad == null) {
            this.f7913ad = new fts(super.mo2634k(), (C0147fh) this, true);
            ebq ebq = (ebq) this;
            ((ecb) mo2453b()).mo2414ai();
        }
    }

    /* renamed from: a */
    public void mo2631a(Activity activity) {
        super.mo2631a(activity);
        ContextWrapper contextWrapper = this.f7913ad;
        boolean z = true;
        if (!(contextWrapper == null || ftr.m9609a((Context) contextWrapper) == activity)) {
            z = false;
        }
        ife.m12876b(z, (Object) "onAttach called multiple times with different Context! Sting Fragments should not be retained.");
        m7146U();
    }

    /* renamed from: a */
    public void mo1832a(Context context) {
        super.mo1832a(context);
        m7146U();
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f7914af == null) {
            synchronized (this.f7915ag) {
                if (this.f7914af == null) {
                    this.f7914af = mo4666T();
                }
            }
        }
        return this.f7914af.mo2453b();
    }
}
