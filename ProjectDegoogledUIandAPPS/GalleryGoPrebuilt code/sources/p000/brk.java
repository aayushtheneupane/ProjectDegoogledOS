package p000;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.devicefolders.DeviceFoldersGridView;

/* renamed from: brk */
/* compiled from: PG */
public final class brk extends bsp implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f3420Z;

    /* renamed from: b */
    private brp f3421b;

    /* renamed from: c */
    private Context f3422c;

    /* renamed from: d */
    private final C0002ab f3423d = new C0002ab(this);

    @Deprecated
    public brk() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f3423d;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f3422c == null) {
            this.f3422c = new hcf((Context) this.f3494a);
        }
        return this.f3422c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo2704Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f3494a != null) {
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
            if (!this.f3420Z) {
                super.mo1832a(context);
                if (this.f3421b == null) {
                    this.f3421b = ((brr) mo2453b()).mo2439q();
                    this.f9583V.mo64a(new hbu(this.f3423d));
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

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        bru bru = mo2635n().f3448h;
        int a = bru.mo2712a();
        bru.f3459d.mo10425a(a);
        bru.f3460e.f3454a = a;
    }

    /* renamed from: a */
    public final View mo2630a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        hlq c = hnb.m11779c();
        try {
            mo7269c(layoutInflater, viewGroup, bundle);
            brp R = mo2635n();
            R.f3449i = layoutInflater.inflate(R.layout.device_folders_fragment, viewGroup, false);
            R.f3448h = ((DeviceFoldersGridView) R.f3449i.findViewById(R.id.device_folders_grid)).mo2635n();
            bru bru = R.f3448h;
            brm a = brm.m3486a(R.f3442b.f3432b);
            if (a == null) {
                a = brm.SIZE_AND_NUMBER;
            }
            bru.f3461f.f3479a = a;
            if (!dvg.m6745a(R.f3443c.mo2634k(), brp.f3441a)) {
                R.f3443c.mo5640a(brp.f3441a);
            } else {
                R.mo2709b();
            }
            if (bundle != null) {
                C0647xt layoutManager = R.f3448h.f3457b.getLayoutManager();
                Parcelable parcelable = bundle.getParcelable("folders_recycler_view_state");
                if (parcelable != null) {
                    if (layoutManager != null) {
                        layoutManager.mo10463a(parcelable);
                    }
                }
            }
            ((fea) R.f3447g.f9364c.mo5563a(74308).mo5513a(ffh.f9451a)).mo5560a(R.f3449i);
            View view = R.f3449i;
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
            this.f3420Z = true;
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
        brp R = mo2635n();
        if (i != 1) {
            StringBuilder sb = new StringBuilder(33);
            sb.append("Invalid Request Code: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        } else if (dvg.m6746a(brp.f3441a, strArr, iArr)) {
            R.mo2709b();
        }
    }

    /* renamed from: e */
    public final void mo168e(Bundle bundle) {
        super.mo168e(bundle);
        bru bru = mo2635n().f3448h;
        C0647xt layoutManager = bru.f3457b.getLayoutManager();
        if (bru.f3457b != null && layoutManager != null) {
            bundle.putParcelable("folders_recycler_view_state", layoutManager.mo10476h());
        }
    }

    /* renamed from: d */
    public final void mo210d() {
        hlq c = hnb.m11779c();
        try {
            mo7259S();
            mo2635n().f3445e.mo2552c();
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
            mo2635n().f3445e.mo2550a();
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
            ihg.m13038a((C0147fh) this, bsk.class, (hol) new brq(mo2635n()));
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
    public final brp mo2635n() {
        brp brp = this.f3421b;
        if (brp == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f3420Z) {
            return brp;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
