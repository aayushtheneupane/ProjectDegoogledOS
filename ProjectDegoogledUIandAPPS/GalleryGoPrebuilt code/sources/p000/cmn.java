package p000;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;

/* renamed from: cmn */
/* compiled from: PG */
public final class cmn extends cnb implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f4685Z;

    /* renamed from: a */
    public final C0002ab f4686a = new C0002ab(this);

    /* renamed from: c */
    private cmv f4687c;

    /* renamed from: d */
    private Context f4688d;

    @Deprecated
    public cmn() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f4686a;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f4688d == null) {
            this.f4688d = new hcf((Context) this.f4718b);
        }
        return this.f4688d;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo3258Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f4718b != null) {
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
            if (!this.f4685Z) {
                super.mo1832a(context);
                if (this.f4687c == null) {
                    this.f4687c = ((cmz) mo2453b()).mo2447y();
                    this.f9583V.mo64a(new hbu(this.f4686a));
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
    public final void mo165a(Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            mo7268c(bundle);
            cmv R = mo2635n();
            R.f4705f.mo6988a(R.f4708i);
            R.f4705f.mo6988a(R.f4709j);
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
    public final View mo2630a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            mo7269c(layoutInflater, viewGroup, bundle);
            cmv R = mo2635n();
            R.f4710k = layoutInflater.inflate(R.layout.pick_folder_to_add_items_fragment, viewGroup, false);
            if (bundle == null) {
                iir g = brn.f3429h.mo8793g();
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                brn.m3488a((brn) g.f14318b);
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                brn brn = (brn) g.f14318b;
                brn.f3431a |= 16;
                brn.f3436f = true;
                R.f4701b.mo5659r().mo6419a().mo6849a((int) R.id.folder_picker_content, (C0147fh) brp.m3494a((brn) g.mo8770g())).mo5244a();
            }
            Toolbar toolbar = (Toolbar) R.f4710k.findViewById(R.id.folder_picker_toolbar);
            toolbar.mo1088b(!R.f4700a.f4692b ? R.string.pick_folder_to_copy_items_title : R.string.pick_folder_to_move_items_title);
            R.f4703d.mo2573a(toolbar);
            View view = R.f4710k;
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
            this.f4685Z = true;
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
            cmv R = mo2635n();
            ihg.m13038a((C0147fh) this, bsh.class, (hol) new cmw(R));
            ihg.m13038a((C0147fh) this, cjw.class, (hol) new cmx(R));
            ihg.m13038a((C0147fh) this, brx.class, (hol) new cmy(R));
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
    public final cmv mo2635n() {
        cmv cmv = this.f4687c;
        if (cmv == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f4685Z) {
            return cmv;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
