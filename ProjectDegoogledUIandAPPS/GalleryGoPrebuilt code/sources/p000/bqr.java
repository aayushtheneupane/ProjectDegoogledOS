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
import android.widget.Button;
import com.google.android.apps.photosgo.R;
import com.google.android.libraries.material.progress.MaterialProgressBar;

/* renamed from: bqr */
/* compiled from: PG */
public final class bqr extends bqy implements hbf, ioe, hbd {

    /* renamed from: aa */
    private bqu f3389aa;

    /* renamed from: ab */
    private Context f3390ab;

    /* renamed from: ac */
    private final hkx f3391ac = new hkx(this);

    /* renamed from: ad */
    private final C0002ab f3392ad = new C0002ab(this);

    /* renamed from: ae */
    private boolean f3393ae;

    @Deprecated
    public bqr() {
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f3392ad;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f3390ab == null) {
            this.f3390ab = new hcf((Context) this.f3405Z);
        }
        return this.f3390ab;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo2676Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f3405Z != null) {
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
        hlq a = this.f3391ac.mo7537a();
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
            if (!this.f3393ae) {
                super.mo1832a(context);
                if (this.f3389aa == null) {
                    this.f3389aa = ((bqv) mo2453b()).mo2437o();
                    this.f9583V.mo64a(new hbu(this.f3392ad));
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

    public final void onCancel(DialogInterface dialogInterface) {
        ihg.m13040a((hoi) bqk.m3382a(), (C0140fa) mo2635n().f3396a);
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
        bqu R = mo2635n();
        Context k = R.f3396a.mo2634k();
        View inflate = LayoutInflater.from(k).inflate(R.layout.progress_dialog, (ViewGroup) null, false);
        gfi gfi = new gfi(k, R.style.DeleteDialog);
        gfi.mo6554c();
        gfi.mo6558d(C0643xp.m15940a(R.f3396a.mo2634k(), R.string.delete_progress_dialog_title, "num_media", Integer.valueOf(R.f3399d.f3384b)));
        gfi.mo6553b(inflate);
        C0394oj b = gfi.mo6550b();
        b.requestWindowFeature(1);
        b.setCancelable(false);
        b.setCanceledOnTouchOutside(false);
        gvi gvi = R.f3398c;
        bqq bqq = R.f3397b;
        gvi.mo7113a(bqq.f3386a.mo7085a(new bqp(bqq), "DELETION_PROGRESS_KEY"), guy.DONT_CARE, R.f3400e);
        R.f3401f = (MaterialProgressBar) inflate.findViewById(R.id.progress_bar);
        R.mo2682a(0);
        R.f3401f.mo3531c();
        ((Button) inflate.findViewById(R.id.stop_button)).setOnClickListener(new bqs(R));
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
            this.f3393ae = true;
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
        hlq c = this.f3391ac.mo7539c();
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
        hlq b = this.f3391ac.mo7538b();
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
    public final bqu mo2635n() {
        bqu bqu = this.f3389aa;
        if (bqu == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f3393ae) {
            return bqu;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
