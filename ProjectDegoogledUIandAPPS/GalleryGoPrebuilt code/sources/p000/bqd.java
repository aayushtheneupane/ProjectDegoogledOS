package p000;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import com.google.android.apps.photosgo.R;

/* renamed from: bqd */
/* compiled from: PG */
public final class bqd extends bqx implements hbf, ioe, hbd {

    /* renamed from: aa */
    private bqg f3354aa;

    /* renamed from: ab */
    private Context f3355ab;

    /* renamed from: ac */
    private final hkx f3356ac = new hkx(this);

    /* renamed from: ad */
    private final C0002ab f3357ad = new C0002ab(this);

    /* renamed from: ae */
    private boolean f3358ae;

    @Deprecated
    public bqd() {
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f3357ad;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f3355ab == null) {
            this.f3355ab = new hcf((Context) this.f3402Z);
        }
        return this.f3355ab;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo2664Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: a */
    public static bqd m3356a(bqc bqc) {
        bqd bqd = new bqd();
        ftr.m9611b(bqd);
        ftr.m9610a((C0147fh) bqd);
        hcl.m11210a(bqd, bqc);
        return bqd;
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f3402Z != null) {
            return mo2628P();
        }
        return null;
    }

    /* renamed from: d */
    public final void mo2667d(Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            super.mo2667d(bundle);
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
    public final void mo2665a(int i, int i2, Intent intent) {
        hlq a = this.f3356ac.mo7537a();
        try {
            super.mo2665a(i, i2, intent);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
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
            if (!this.f3358ae) {
                super.mo1832a(context);
                if (this.f3354aa == null) {
                    this.f3354aa = ((bqh) mo2453b()).mo2436n();
                    this.f9583V.mo64a(new hbu(this.f3357ad));
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
            super.mo165a(bundle);
            bqg R = mo2635n();
            R.f3365e = R.f3364d.mo5581a(R.f3361a);
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
    public final Dialog mo193c(Bundle bundle) {
        super.mo193c(bundle);
        bqg R = mo2635n();
        Context k = R.f3361a.mo2634k();
        gfi gfi = new gfi(k, R.style.DeleteDialog);
        gfi.mo6554c();
        gfi.mo6558d(C0643xp.m15940a(k, R.string.delete_confirmation_title, "count", Integer.valueOf(R.f3362b.size())));
        if (R.f3362b.size() > 10) {
            View inflate = LayoutInflater.from(k).inflate(R.layout.delete_confirmation_checkbox, (ViewGroup) null, false);
            R.f3366f = (CheckBox) inflate.findViewById(R.id.delete_confirmation_checkbox);
            R.f3366f.setOnClickListener(new bqe(R));
            gfi.mo6553b(inflate);
        } else {
            gfi.mo6551b((int) R.string.delete_confirmation_body);
        }
        gfi.mo6556c(R.string.delete_confirmation_positive_text, R.f3363c.mo7574a((DialogInterface.OnClickListener) R, "Deletion Click"));
        gfi.f15411a.f15356i = (Drawable) ife.m12898e((Object) k.getDrawable(R.drawable.quantum_gm_ic_delete_forever_vd_theme_24));
        gfi.mo6552b(R.string.delete_confirmation_negative_text, R.f3363c.mo7574a((DialogInterface.OnClickListener) R, "Cancel Deletion Click"));
        gfi.f15411a.f15359l = (Drawable) ife.m12898e((Object) k.getDrawable(R.drawable.quantum_gm_ic_close_vd_theme_24));
        C0394oj b = gfi.mo6550b();
        b.setOnShowListener(fhg.m8899a((DialogInterface.OnShowListener) new bqf(R), (C0140fa) R.f3361a));
        b.setCanceledOnTouchOutside(false);
        return b;
    }

    /* renamed from: a */
    public final View mo2630a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            View a = super.mo2630a(layoutInflater, viewGroup, bundle);
            if (c != null) {
                c.close();
            }
            return a;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: x */
    public final void mo1836x() {
        hlq c = hnb.m11779c();
        try {
            super.mo1836x();
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

    /* renamed from: f */
    public final void mo212f() {
        hlq c = hnb.m11779c();
        try {
            super.mo212f();
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
            super.mo1834c();
            this.f3358ae = true;
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

    public final void onDismiss(DialogInterface dialogInterface) {
        hlq c = this.f3356ac.mo7539c();
        try {
            super.onDismiss(dialogInterface);
            c.close();
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
            LayoutInflater from = LayoutInflater.from(new hcf(super.mo2633b(bundle)));
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
    public final boolean mo2666a(MenuItem menuItem) {
        hlq b = this.f3356ac.mo7538b();
        try {
            boolean a = super.mo2666a(menuItem);
            if (b != null) {
                b.close();
            }
            return a;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: w */
    public final void mo2669w() {
        hlq c = hnb.m11779c();
        try {
            super.mo2669w();
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

    /* renamed from: v */
    public final void mo2668v() {
        hlq c = hnb.m11779c();
        try {
            super.mo2668v();
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

    /* renamed from: d */
    public final void mo210d() {
        hlq c = hnb.m11779c();
        try {
            super.mo210d();
            hok.m11841b((C0140fa) this);
            if (this.f9239c) {
                hok.m11839a((C0140fa) this);
            }
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
            super.mo211e();
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
            super.mo2632a(view, bundle);
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
    public final bqg mo2635n() {
        bqg bqg = this.f3354aa;
        if (bqg == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f3358ae) {
            return bqg;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
