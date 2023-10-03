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
import com.google.android.apps.photosgo.oneup.OneUpActionsView;
import com.google.android.apps.photosgo.oneup.OneUpPagerView;
import p003j$.util.Optional;

/* renamed from: dmb */
/* compiled from: PG */
public final class dmb extends don implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f6812Z;

    /* renamed from: a */
    public final C0002ab f6813a = new C0002ab(this);

    /* renamed from: c */
    private dnn f6814c;

    /* renamed from: d */
    private Context f6815d;

    @Deprecated
    public dmb() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f6813a;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f6815d == null) {
            this.f6815d = new hcf((Context) this.f6946b);
        }
        return this.f6815d;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo4240Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f6946b != null) {
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
            if (!this.f6812Z) {
                super.mo1832a(context);
                if (this.f6814c == null) {
                    this.f6814c = ((dnr) mo2453b()).mo2382D();
                    this.f9583V.mo64a(new hbu(this.f6813a));
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
            dnn R = mo2635n();
            R.f6901j.f6746c = new dmf(R);
            R.f6908q.mo6988a(R.f6874D);
            R.f6908q.mo6988a(R.f6875E);
            R.f6908q.mo6988a(R.f6873C);
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
        Throwable th;
        Bundle bundle2 = bundle;
        hlq c = hnb.m11779c();
        try {
            mo7269c(layoutInflater, viewGroup, bundle);
            dnn R = mo2635n();
            View inflate = layoutInflater.inflate(R.layout.oneup_fragment_layout, viewGroup, false);
            R.f6877G = (OneUpPagerView) inflate.findViewById(R.id.oneup_pager);
            R.f6878H = R.f6877G.mo2635n();
            View findViewById = inflate.findViewById(R.id.oneup_appbar);
            Toolbar toolbar = (Toolbar) inflate.findViewById(R.id.oneup_top_toolbar);
            dou dou = R.f6916y;
            dmb dmb = R.f6897f;
            dot dot = r11;
            dot dot2 = new dot((hdt) dou.m6420a((hdt) dou.f6963a.mo2097a(), 1), (cny) dou.m6420a((cny) dou.f6964b.mo2097a(), 2), (hbl) dou.m6420a((hbl) dou.f6965c.mo2097a(), 3), (fee) dou.m6420a((fee) dou.f6966d.mo2097a(), 4), (C0147fh) dou.m6420a(dmb, 5), (ViewStub) dou.m6420a((ViewStub) toolbar.findViewById(R.id.oneup_top_toolbar_badge), 6));
            R.f6881K = dot;
            R.f6879I = toolbar.mo1099f();
            if (R.f6895d.f6830f) {
                R.f6879I.add(0, R.id.oneup_menu_all_photos, 0, R.string.oneup_all_photos).setShowAsActionFlags(1).setIcon(R.drawable.quantum_gm_ic_photo_library_vd_theme_24);
            }
            R.f6879I.add(0, R.id.oneup_menu_infosheet, 0, R.string.oneup_info);
            if (R.f6915x.isPresent()) {
                ((bre) R.f6915x.get()).mo2687a();
            }
            R.f6880J = (OneUpActionsView) inflate.findViewById(R.id.oneup_bottom_navigation);
            R.f6904m.mo2575a((View) R.f6880J);
            OneUpActionsView oneUpActionsView = R.f6880J;
            oneUpActionsView.f4877a.setOnClickListener(R.f6905n.mo7575a((View.OnClickListener) new dmq(R), "OneUp share"));
            OneUpActionsView oneUpActionsView2 = R.f6880J;
            oneUpActionsView2.f4880d.setOnClickListener(R.f6905n.mo7575a((View.OnClickListener) new dnb(R), "OneUp delete"));
            R.f6876F = hso.m12035a(findViewById, R.f6880J, inflate.findViewById(R.id.oneup_top_gradient));
            R.f6882L = false;
            if (bundle2 != null) {
                if (bundle2.containsKey("oneup_media_key")) {
                    R.mo4269a((cxi) imi.m14117c(bundle2, "oneup_media_key", cxi.f5908x, R.f6903l), bundle2.getBundle("oneup_media_state_key"));
                }
            }
            if (!dvg.m6745a(R.f6897f.mo2634k(), dnn.f6870a)) {
                R.f6897f.mo5640a(dnn.f6870a);
            } else {
                R.mo4268a();
            }
            if (!R.f6895d.f6833i) {
                R.f6904m.mo2574a(toolbar, findViewById, 0);
            } else {
                R.f6904m.mo2577b(toolbar, findViewById, 0);
            }
            if (R.f6895d.f6834j) {
                toolbar.mo1096d((int) R.drawable.quantum_gm_ic_camera_alt_vd_theme_24);
            }
            toolbar.f1030q = R.f6906o.mo7620a(new dnc(R), "Menu Item Selected");
            dog dog = R.f6878H;
            dog.f6936c = new dnd(R);
            dog.f6937d = Optional.empty();
            dog.mo4299b();
            R.f6899h.mo4354a((dqw) new dne(R));
            ((fea) R.f6914w.f9364c.mo5563a(74310).mo5513a(ffh.f9451a)).mo5560a(inflate);
            if (inflate != null) {
                if (c != null) {
                    c.close();
                }
                return inflate;
            }
            throw new NullPointerException("Fragment cannot use Event annotations with null view!");
        } catch (Throwable th2) {
            ifn.m12935a(th, th2);
        }
        throw th;
    }

    /* renamed from: x */
    public final void mo1836x() {
        hlq c = hnb.m11779c();
        try {
            mo7264X();
            mo2635n().f6901j.f6746c = null;
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

    /* renamed from: c */
    public final void mo1834c() {
        hlq c = hnb.m11779c();
        try {
            mo7265Y();
            this.f6812Z = true;
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
        dnn R = mo2635n();
        if (i != 1) {
            StringBuilder sb = new StringBuilder(33);
            sb.append("Invalid Request Code: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        } else if (dvg.m6746a(dnn.f6870a, strArr, iArr)) {
            R.mo4268a();
        }
    }

    /* renamed from: v */
    public final void mo2668v() {
        hlq c = hnb.m11779c();
        try {
            mo7260T();
            dnn R = mo2635n();
            R.f6901j.mo4184a(R.f6879I, R.f6878H.mo4296a());
            R.f6902k.mo4208a(R.f6879I, R.f6878H.mo4296a());
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
        dnn R = mo2635n();
        R.f6878H.mo4296a().ifPresent(new dnf(R, bundle));
    }

    /* renamed from: d */
    public final void mo210d() {
        hlq c = hnb.m11779c();
        try {
            mo7259S();
            mo2635n().f6912u.mo2552c();
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
    public final void mo211e() {
        hlq c = hnb.m11779c();
        try {
            mo7262V();
            mo2635n().f6912u.mo2550a();
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
    public final void mo2632a(View view, Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            hok.m11838a((Context) mo5653m()).f13171d = view;
            dnn R = mo2635n();
            ihg.m13038a((C0147fh) this, bqj.class, (hol) new dno(R));
            ihg.m13038a((C0147fh) this, bwp.class, (hol) new dnp(R));
            ihg.m13038a((C0147fh) this, cce.class, (hol) new dnq(R));
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
    public final dnn mo2635n() {
        dnn dnn = this.f6814c;
        if (dnn == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f6812Z) {
            return dnn;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
