package p000;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.p002v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.home.HomeNavigationView;
import com.google.android.material.appbar.AppBarLayout;

/* renamed from: cqt */
/* compiled from: PG */
public final class cqt extends crn implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f5439Z;

    /* renamed from: b */
    private crd f5440b;

    /* renamed from: c */
    private Context f5441c;

    /* renamed from: d */
    private final C0002ab f5442d = new C0002ab(this);

    @Deprecated
    public cqt() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f5442d;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f5441c == null) {
            this.f5441c = new hcf((Context) this.f5495a);
        }
        return this.f5441c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo3768Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f5495a != null) {
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
            if (!this.f5439Z) {
                super.mo1832a(context);
                if (this.f5440b == null) {
                    this.f5440b = ((crh) mo2453b()).mo2448z();
                    this.f9583V.mo64a(new hbu(this.f5442d));
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
            crd R = mo2635n();
            View inflate = layoutInflater.inflate(R.layout.home_fragment, viewGroup, false);
            R.f5475p = (ViewGroup) inflate.findViewById(R.id.main_content);
            R.f5477r = (ViewStub) inflate.findViewById(R.id.no_permission_stub);
            R.f5474o = (Toolbar) inflate.findViewById(R.id.main_toolbar);
            R.f5474o.mo1098e(R.menu.home_top_menu);
            R.f5479t = R.f5474o.mo1099f().findItem(R.id.home_select);
            R.f5481v = R.f5474o.mo1099f().findItem(R.id.home_new_folder);
            R.f5474o.f1030q = R.f5464e.mo7620a(new cqw(R), "Menu Item Selected");
            if (R.f5468i.mo3175a()) {
                R.f5480u = R.f5474o.mo1099f().findItem(R.id.home_face_clustering_and_image_labeling);
                R.f5480u.setVisible(true);
            }
            if (R.f5469j.mo3175a()) {
                R.f5474o.mo1099f().findItem(R.id.home_trash).setVisible(true);
            }
            R.f5478s = (AppBarLayout) inflate.findViewById(R.id.home_appbar);
            R.f5478s.mo3587g();
            R.f5482w = (HomeNavigationView) inflate.findViewById(R.id.home_bottom_navigation);
            HomeNavigationView homeNavigationView = R.f5482w;
            homeNavigationView.f4864a.setOnClickListener(new crj(homeNavigationView, R.f5465f.mo7575a((View.OnClickListener) new cqu(R), "Photo Tab Selected")));
            HomeNavigationView homeNavigationView2 = R.f5482w;
            homeNavigationView2.f4865b.setOnClickListener(new crk(homeNavigationView2, R.f5465f.mo7575a((View.OnClickListener) new cqv(R), "Folder Tab Selected")));
            fdr a = ((fea) R.f5471l.f9364c.mo5563a(74306).mo5513a(ffh.f9451a)).mo5560a(inflate);
            R.f5471l.f9364c.mo5563a(74902).mo5560a(R.f5482w.findViewById(R.id.home_photos));
            R.f5471l.f9364c.mo5563a(74897).mo5560a(R.f5482w.findViewById(R.id.home_folders));
            R.f5483x = fhg.m8901a(a, 74900).mo5510a();
            R.f5484y = fhg.m8901a(a, 74901).mo5510a();
            R.f5485z = fhg.m8901a(a, 74899).mo5510a();
            R.f5473n.mo7113a(new cru(R.f5461b), guy.DONT_CARE, R.f5456B);
            if (inflate != null) {
                if (c != null) {
                    c.close();
                }
                return inflate;
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
            this.f5439Z = true;
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
    public final void mo2705a(int i, String[] strArr, int[] iArr) {
        super.mo2705a(i, strArr, iArr);
        crx crx = mo2635n().f5462c;
        if (i != 1) {
            StringBuilder sb = new StringBuilder(33);
            sb.append("Invalid Request Code: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        } else if (dvg.m6746a(crx.f5527a, strArr, iArr)) {
            crx.mo3791a(3);
        } else {
            crx.mo3791a(4);
        }
    }

    /* renamed from: v */
    public final void mo2668v() {
        hlq c = hnb.m11779c();
        try {
            mo7260T();
            crd R = mo2635n();
            R.f5462c.mo3792a((C0147fh) R.f5460a);
            R.mo3778b(R.f5455A);
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

    /* renamed from: e */
    public final void mo168e(Bundle bundle) {
        super.mo168e(bundle);
        mo2635n();
    }

    /* renamed from: a */
    public final void mo2632a(View view, Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            hok.m11838a((Context) mo5653m()).f13171d = view;
            crd R = mo2635n();
            ihg.m13038a((C0147fh) this, dvr.class, (hol) new cre(R));
            ihg.m13038a((C0147fh) this, brx.class, (hol) new crf(R));
            ihg.m13038a((C0147fh) this, dwz.class, (hol) new crg(R));
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
    public final crd mo2635n() {
        crd crd = this.f5440b;
        if (crd == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f5439Z) {
            return crd;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
