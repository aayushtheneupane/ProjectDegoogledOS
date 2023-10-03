package p000;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;

/* renamed from: dzq */
/* compiled from: PG */
public final class dzq extends eaf implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f7732Z;

    /* renamed from: b */
    private dzt f7733b;

    /* renamed from: c */
    private Context f7734c;

    /* renamed from: d */
    private final C0002ab f7735d = new C0002ab(this);

    @Deprecated
    public dzq() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f7735d;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f7734c == null) {
            this.f7734c = new hcf((Context) this.f7763a);
        }
        return this.f7734c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo4619Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f7763a != null) {
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
            if (!this.f7732Z) {
                super.mo1832a(context);
                if (this.f7733b == null) {
                    this.f7733b = ((dzv) mo2453b()).mo2386H();
                    this.f9583V.mo64a(new hbu(this.f7735d));
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
            dzt R = mo2635n();
            R.f7749f = layoutInflater.inflate(R.layout.external_picker_fragment, viewGroup, false);
            Toolbar toolbar = (Toolbar) R.f7749f.findViewById(R.id.external_picker_toolbar);
            toolbar.mo1085a(R.f7745b.mo5653m().getTitle());
            R.f7747d.mo2576b(toolbar);
            dzr dzr = R.f7744a;
            iir g = brn.f3429h.mo8793g();
            int i = !dzr.f7739b ? 3 : 2;
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            brn.m3488a((brn) g.f14318b);
            brm brm = brm.NUMBER_OF_ITEMS;
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            brn brn = (brn) g.f14318b;
            brn.f3432b = brm.f3428c;
            brn.f3431a |= 1;
            cpi cpi = cpi.PICKER;
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            brn brn2 = (brn) g.f14318b;
            brn2.f3437g = cpi.f5364h;
            brn2.f3431a |= 32;
            iir g2 = cqe.f5414d.mo8793g();
            if (g2.f14319c) {
                g2.mo8751b();
                g2.f14319c = false;
            }
            cqe cqe = (cqe) g2.f14318b;
            cqe.f5417b = i - 1;
            int i2 = cqe.f5416a | 1;
            cqe.f5416a = i2;
            cqe.f5416a = i2 | 2;
            cqe.f5418c = 100;
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            brn brn3 = (brn) g.f14318b;
            cqe cqe2 = (cqe) g2.mo8770g();
            cqe2.getClass();
            brn3.f3433c = cqe2;
            brn3.f3431a |= 2;
            if ((dzr.f7738a & 2) != 0) {
                iir g3 = cxd.f5884h.mo8793g();
                String str = dzr.f7740c;
                if (g3.f14319c) {
                    g3.mo8751b();
                    g3.f14319c = false;
                }
                cxd cxd = (cxd) g3.f14318b;
                str.getClass();
                cxd.f5886a |= 32;
                cxd.f5890e = str;
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                brn brn4 = (brn) g.f14318b;
                cxd cxd2 = (cxd) g3.mo8770g();
                cxd2.getClass();
                brn4.f3434d = cxd2;
                brn4.f3431a |= 4;
            }
            R.f7745b.mo5659r().mo6419a().mo6849a((int) R.id.external_picker_content, (C0147fh) brp.m3494a((brn) g.mo8770g())).mo5244a();
            ((fea) R.f7748e.f9364c.mo5563a(74312).mo5513a(ffh.f9451a)).mo5560a(R.f7749f);
            View view = R.f7749f;
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
            this.f7732Z = true;
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
            ihg.m13038a((C0147fh) this, brx.class, (hol) new dzu(mo2635n()));
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
    public final dzt mo2635n() {
        dzt dzt = this.f7733b;
        if (dzt == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f7732Z) {
            return dzt;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
