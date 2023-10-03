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
import android.widget.Switch;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.sharing.SharingAppGridView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

/* renamed from: ecw */
/* compiled from: PG */
public final class ecw extends edr implements hbf, ioe, hbd {

    /* renamed from: aa */
    private edc f7941aa;

    /* renamed from: ab */
    private Context f7942ab;

    /* renamed from: ac */
    private final hkx f7943ac = new hkx(this);

    /* renamed from: ad */
    private final C0002ab f7944ad = new C0002ab(this);

    /* renamed from: ae */
    private boolean f7945ae;

    /* renamed from: af */
    private boolean f7946af;

    @Deprecated
    public ecw() {
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f7944ad;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f7942ab == null) {
            this.f7942ab = new hcf((Context) this.f8054Z);
        }
        return this.f7942ab;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo4685Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: a */
    public static ecw m7199a(ecx ecx) {
        ecw ecw = new ecw();
        ftr.m9611b(ecw);
        ftr.m9610a((C0147fh) ecw);
        hcl.m11210a(ecw, ecx);
        return ecw;
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f8054Z != null) {
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
        hlq a = this.f7943ac.mo7537a();
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
            if (!this.f7946af) {
                super.mo1832a(context);
                if (this.f7941aa == null) {
                    this.f7941aa = ((edf) mo2453b()).mo2390L();
                    this.f9583V.mo64a(new hbu(this.f7944ad));
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
        edc R = mo2635n();
        Context k = R.f8020f.mo2634k();
        int i = 0;
        View inflate = LayoutInflater.from(k).inflate(R.layout.sharing_fragment, (ViewGroup) null, false);
        R.f8016b = (SharingAppGridView) inflate.findViewById(R.id.sharing_app_grid);
        R.f8015a = R.f8016b.mo2635n();
        R.f8025k.mo7113a(new efj(R.f8022h, k.getResources().getInteger(R.integer.appshare_grid_column_count) - 1, !R.f8030p.isPresent() || ((cxh) R.f8030p.get()).equals(cxh.VIDEO), R.f8031q), guy.DONT_CARE, R.f8032r);
        R.f8017c = inflate.findViewById(R.id.share_compression_ui);
        View view = R.f8017c;
        if (!R.mo4718a()) {
            i = 8;
        }
        view.setVisibility(i);
        R.f8018d = (Switch) R.f8017c.findViewById(R.id.image_compression_toggle);
        gdo gdo = new gdo(k);
        gdo.setContentView(inflate);
        BottomSheetBehavior b = gdo.mo6471b();
        b.mo3614c(3);
        b.f5174i = true;
        gdo.setOnShowListener(fhg.m8899a((DialogInterface.OnShowListener) new ecy(R), (C0140fa) R.f8020f));
        return gdo;
    }

    /* renamed from: a */
    public final View mo2630a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            View a = super.mo2630a(layoutInflater, viewGroup, bundle);
            this.f7945ae = false;
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
            this.f7946af = true;
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
        hlq c = this.f7943ac.mo7539c();
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
        hlq b = this.f7943ac.mo7538b();
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
                if (!this.f7945ae) {
                    hok.m11838a((Context) mo5653m()).f13171d = ihg.m13027a((C0140fa) this);
                    ede.m7259a(this, mo2635n());
                    this.f7945ae = true;
                }
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
            if (!this.f9239c && !this.f7945ae) {
                hok.m11838a((Context) mo5653m()).f13171d = view;
                ede.m7259a(this, mo2635n());
                this.f7945ae = true;
            }
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
    public final edc mo2635n() {
        edc edc = this.f7941aa;
        if (edc == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f7946af) {
            return edc;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
