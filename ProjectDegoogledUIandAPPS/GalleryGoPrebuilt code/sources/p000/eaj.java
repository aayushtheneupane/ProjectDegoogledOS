package p000;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import com.google.android.libraries.material.progress.MaterialProgressBar;
import com.google.android.material.button.MaterialButton;

/* renamed from: eaj */
/* compiled from: PG */
public final class eaj extends eap implements hbf, ioe, hbd {

    /* renamed from: aa */
    private eam f7778aa;

    /* renamed from: ab */
    private Context f7779ab;

    /* renamed from: ac */
    private final hkx f7780ac = new hkx(this);

    /* renamed from: ad */
    private final C0002ab f7781ad = new C0002ab(this);

    /* renamed from: ae */
    private boolean f7782ae;

    @Deprecated
    public eaj() {
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f7781ad;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f7779ab == null) {
            this.f7779ab = new hcf((Context) this.f7792Z);
        }
        return this.f7779ab;
    }

    /* access modifiers changed from: protected */
    /* renamed from: R */
    public final /* bridge */ /* synthetic */ ftr mo4627R() {
        return hcl.m11211d(this);
    }

    /* renamed from: a */
    public static eaj m7021a(eai eai) {
        eaj eaj = new eaj();
        ftr.m9611b(eaj);
        ftr.m9610a((C0147fh) eaj);
        hcl.m11210a(eaj, eai);
        return eaj;
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f7792Z != null) {
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
        hlq a = this.f7780ac.mo7537a();
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
            if (!this.f7782ae) {
                super.mo1832a(context);
                if (this.f7778aa == null) {
                    this.f7778aa = ((ean) mo2453b()).mo2387I();
                    this.f9583V.mo64a(new hbu(this.f7781ad));
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
        eam Q = mo2635n();
        Context k = Q.f7786a.mo2634k();
        View inflate = LayoutInflater.from(k).inflate(R.layout.progress_for_dialog, (ViewGroup) null, false);
        MaterialProgressBar materialProgressBar = (MaterialProgressBar) inflate.findViewById(R.id.progress_bar);
        if (Q.f7787b.f7776c != -1) {
            materialProgressBar.setIndeterminate(false);
            materialProgressBar.setMax(Q.f7787b.f7776c);
        }
        if (Q.f7787b.f7777d) {
            MaterialButton materialButton = (MaterialButton) inflate.findViewById(R.id.stop_button);
            materialButton.setVisibility(0);
            materialButton.setOnClickListener(Q.f7790e.mo7575a((View.OnClickListener) new eak(Q), "interrupt task"));
        }
        Q.f7788c.mo7113a(Q.f7789d, guy.DONT_CARE, new eal(Q, materialProgressBar));
        gfi gfi = new gfi(k, 2131951907);
        gfi.mo6553b(inflate);
        gfi.mo6555c(Q.f7787b.f7775b);
        C0394oj b = gfi.mo6550b();
        b.setCanceledOnTouchOutside(false);
        b.setCancelable(false);
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
            this.f7782ae = true;
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
        hlq c = this.f7780ac.mo7539c();
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
        hlq b = this.f7780ac.mo7538b();
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

    /* renamed from: Q */
    public final eam mo2635n() {
        eam eam = this.f7778aa;
        if (eam == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f7782ae) {
            return eam;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
