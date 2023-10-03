package p000;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;

/* renamed from: boc */
/* compiled from: PG */
public final class boc extends bor implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f3238Z;

    /* renamed from: b */
    private bof f3239b;

    /* renamed from: c */
    private Context f3240c;

    /* renamed from: d */
    private final C0002ab f3241d = new C0002ab(this);

    @Deprecated
    public boc() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f3241d;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f3240c == null) {
            this.f3240c = new hcf((Context) this.f3273a);
        }
        return this.f3240c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo2629Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f3273a != null) {
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
            if (!this.f3238Z) {
                super.mo1832a(context);
                if (this.f3239b == null) {
                    this.f3239b = ((boi) mo2453b()).mo2435m();
                    this.f9583V.mo64a(new hbu(this.f3241d));
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
            bof R = mo2635n();
            R.f3252j = layoutInflater.inflate(R.layout.category_photo_grid_fragment, viewGroup, false);
            Toolbar toolbar = (Toolbar) R.f3252j.findViewById(R.id.category_photo_grid_toolbar);
            toolbar.mo1098e(R.menu.category_top_menu);
            toolbar.f1030q = R.f3245c.mo7620a(new boe(R), "Menu Item Selected");
            bnc bnc = R.f3246d;
            if ((bnc.f3181a & 2) != 0) {
                toolbar.mo1088b(bnc.f3185e);
            }
            R.f3247e.mo2573a(toolbar);
            if (bundle == null) {
                C0182gn a = R.f3244b.mo5659r().mo6419a();
                dwj dwj = R.f3248f;
                iir g = dwi.f7492h.mo8793g();
                cxd cxd = R.f3246d.f3186f;
                if (cxd == null) {
                    cxd = cxd.f5884h;
                }
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                dwi dwi = (dwi) g.f14318b;
                cxd.getClass();
                dwi.f7495b = cxd;
                dwi.f7494a |= 1;
                cpi cpi = cpi.HOME_NO_MOVE;
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                dwi dwi2 = (dwi) g.f14318b;
                dwi2.f7498e = cpi.f5364h;
                dwi2.f7494a |= 8;
                a.mo6849a((int) R.id.content, dwj.mo4524a((dwi) g.mo8770g())).mo5244a();
            }
            R.f3251i = fhg.m8901a(((fea) R.f3249g.f9364c.mo5563a(74319).mo5513a(ffh.f9451a)).mo5560a(R.f3252j), 74900).mo5510a();
            View view = R.f3252j;
            if (view != null) {
                if (c != null) {
                    c.close();
                }
                return view;
            }
            throw new NullPointerException("Fragment cannot use Event annotations with null view!");
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
            this.f3238Z = true;
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

    /* renamed from: a */
    public final void mo2632a(View view, Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            hok.m11838a((Context) mo5653m()).f13171d = view;
            bof R = mo2635n();
            ihg.m13038a((C0147fh) this, dvr.class, (hol) new bog(R));
            ihg.m13038a((C0147fh) this, dwz.class, (hol) new boh(R));
            mo7267b(view, bundle);
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

    /* access modifiers changed from: private */
    /* renamed from: R */
    public final bof mo2635n() {
        bof bof = this.f3239b;
        if (bof == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f3238Z) {
            return bof;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
