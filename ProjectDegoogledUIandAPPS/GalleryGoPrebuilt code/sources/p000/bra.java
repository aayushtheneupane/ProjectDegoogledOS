package p000;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;

/* renamed from: bra */
/* compiled from: PG */
public final class bra extends bqz implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f3411Z;

    /* renamed from: b */
    private brb f3412b;

    /* renamed from: c */
    private Context f3413c;

    /* renamed from: d */
    private final C0002ab f3414d = new C0002ab(this);

    @Deprecated
    public bra() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f3414d;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f3413c == null) {
            this.f3413c = new hcf((Context) this.f3408a);
        }
        return this.f3413c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo2685Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f3408a != null) {
            return mo2628P();
        }
        return null;
    }

    /* renamed from: a */
    public final void mo2631a(Activity activity) {
        hlq c = hnb.m11779c();
        try {
            super.mo2631a(activity);
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: a */
    public final void mo1832a(Context context) {
        hlq c = hnb.m11779c();
        try {
            if (!this.f3411Z) {
                super.mo1832a(context);
                if (this.f3412b == null) {
                    this.f3412b = ((brc) mo2453b()).mo2438p();
                    this.f9583V.mo64a(new hbu(this.f3414d));
                }
                if (c != null) {
                    c.close();
                    return;
                }
                return;
            }
            throw new IllegalStateException("A Fragment cannot be attached more than once. Instead, create a new Fragment instance.");
        } catch (ClassCastException e) {
            throw new IllegalStateException("Missing entry point. If you're in a test with explicit entry points specified in your @TestRoot, check that you're not missing the one for this class.", e);
        } catch (Throwable th) {
            if (c != null) {
                try {
                    c.close();
                } catch (Throwable th2) {
                    ifn.m12935a(th, th2);
                }
            }
            throw th;
        }
    }

    /* renamed from: a */
    public final View mo2630a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            mo7269c(layoutInflater, viewGroup, bundle);
            brb R = mo2635n();
            View inflate = layoutInflater.inflate(R.layout.trash_fragment, viewGroup, false);
            R.f3416b.mo2573a((Toolbar) inflate.findViewById(R.id.trash_toolbar));
            C0182gn a = R.f3415a.mo5659r().mo6419a();
            a.mo6848a(cnf.SLIDE_UP.f4724d, cnf.SLIDE_UP.f4725e, cnf.SLIDE_UP.f4726f, cnf.SLIDE_UP.f4727g);
            iir g = dwi.f7492h.mo8793g();
            iir g2 = cxd.f5884h.mo8793g();
            if (g2.f14319c) {
                g2.mo8751b();
                g2.f14319c = false;
            }
            cxd cxd = (cxd) g2.f14318b;
            cxd.f5892g = 3;
            cxd.f5886a |= 128;
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            dwi dwi = (dwi) g.f14318b;
            cxd cxd2 = (cxd) g2.mo8770g();
            cxd2.getClass();
            dwi.f7495b = cxd2;
            dwi.f7494a |= 1;
            dxy dxy = dxy.DESCENDING_TRASHED_TIMESTAMP;
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            dwi dwi2 = (dwi) g.f14318b;
            dwi2.f7499f = dxy.f7618e;
            int i = dwi2.f7494a | 16;
            dwi2.f7494a = i;
            dwi2.f7494a = i | 32;
            dwi2.f7500g = R.layout.empty_bin;
            a.mo6849a((int) R.id.main_content, (C0147fh) dwn.m6817a((dwi) g.mo8770g())).mo5244a();
            if (c != null) {
                c.close();
            }
            return inflate;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: c */
    public final void mo1834c() {
        hlq c = hnb.m11779c();
        try {
            mo7265Y();
            this.f3411Z = true;
            if (c != null) {
                c.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: b */
    public final LayoutInflater mo2633b(Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            LayoutInflater from = LayoutInflater.from(new hcf(LayoutInflater.from(new fts(mo5627H(), (C0147fh) this, true))));
            if (c != null) {
                c.close();
            }
            return from;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    /* renamed from: R */
    public final brb mo2635n() {
        brb brb = this.f3412b;
        if (brb == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f3411Z) {
            return brb;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
