package p000;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.oneup.autoenhance.AutoEnhanceFragmentPeer$10;
import com.google.android.apps.photosgo.oneup.autoenhance.AutoEnhanceFragmentPeer$3;
import com.google.android.apps.photosgo.oneup.autoenhance.AutoEnhanceFragmentPeer$4;
import com.google.android.apps.photosgo.oneup.autoenhance.AutoEnhanceFragmentPeer$5;
import com.google.android.apps.photosgo.oneup.autoenhance.AutoEnhanceFragmentPeer$6;
import com.google.android.apps.photosgo.oneup.autoenhance.AutoEnhanceFragmentPeer$7;
import com.google.android.apps.photosgo.oneup.autoenhance.AutoEnhanceFragmentPeer$8;
import com.google.android.apps.photosgo.oneup.autoenhance.AutoEnhanceFragmentPeer$9;

/* renamed from: dov */
/* compiled from: PG */
public final class dov extends dpw implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f6967Z;

    /* renamed from: b */
    private dpt f6968b;

    /* renamed from: c */
    private Context f6969c;

    /* renamed from: d */
    private final C0002ab f6970d = new C0002ab(this);

    @Deprecated
    public dov() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f6970d;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f6969c == null) {
            this.f6969c = new hcf((Context) this.f7035a);
        }
        return this.f6969c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo4304Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f7035a != null) {
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
            if (!this.f6967Z) {
                super.mo1832a(context);
                if (this.f6968b == null) {
                    this.f6968b = ((dpu) mo2453b()).mo2383E();
                    this.f9583V.mo64a(new hbu(this.f6970d));
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
            dpt R = mo2635n();
            R.f7034z = new AutoEnhanceFragmentPeer$3(new dow(R), dph.f6987a);
            R.f7016h.mo6988a(R.f7034z);
            R.f6999A = new AutoEnhanceFragmentPeer$4(new dpj(R), dpk.f6990a);
            R.f7016h.mo6988a(R.f6999A);
            R.f7000B = new AutoEnhanceFragmentPeer$5(new dpl(R), dpm.f6992a);
            R.f7016h.mo6988a(R.f7000B);
            R.f7001C = new AutoEnhanceFragmentPeer$6(new dpn(R), dpo.f6994a);
            R.f7016h.mo6988a(R.f7001C);
            R.f7002D = new AutoEnhanceFragmentPeer$7(new dpp(R), dpq.f6996a);
            R.f7016h.mo6988a(R.f7002D);
            R.f7003E = new AutoEnhanceFragmentPeer$8(new dox(R), doy.f6973a);
            R.f7016h.mo6988a(R.f7003E);
            R.f7005G = new AutoEnhanceFragmentPeer$9(new doz(R), new dpa(R));
            R.f7016h.mo6988a(R.f7005G);
            R.f7004F = new AutoEnhanceFragmentPeer$10(new dpb(R), new dpc(R));
            R.f7016h.mo6988a(R.f7004F);
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
            dpt R = mo2635n();
            View inflate = layoutInflater.inflate(R.layout.auto_enhance_fragment_layout, viewGroup, false);
            View findViewById = inflate.findViewById(R.id.auto_enhance_cancel_button);
            findViewById.setOnClickListener(R.f7013e.mo7575a((View.OnClickListener) new dpd(R), "auto-enhance cancel"));
            R.f7028t = inflate.findViewById(R.id.auto_enhance_replace_button);
            R.f7028t.setOnClickListener(R.f7013e.mo7575a((View.OnClickListener) new dpe(R), "auto-enhance replace"));
            R.f7012d.mo2575a(inflate.findViewById(R.id.auto_enhance_bottom_bar));
            R.f7025q = (ImageView) inflate.findViewById(R.id.auto_enhance_enhanced_image);
            View.OnTouchListener a = R.f7013e.mo7576a((View.OnTouchListener) new dpf(R), "auto-enhance show original");
            R.f7025q.setOnTouchListener(a);
            R.f7027s = inflate.findViewById(R.id.auto_enhance_compare_button);
            R.f7027s.setOnTouchListener(a);
            R.f7026r = inflate.findViewById(R.id.auto_enhance_original_label);
            R.f7026r.setOnApplyWindowInsetsListener(dpg.f6986a);
            R.f7024p = (ImageView) inflate.findViewById(R.id.auto_enhance_original_image);
            R.f7030v = hto.m12123a(R.f7025q, R.f7027s, findViewById, R.f7028t);
            R.f7029u = hto.m12121a((Object) R.f7024p, (Object) R.f7026r);
            R.f7031w = inflate.findViewById(R.id.progress_indicator);
            ((fea) R.f7022n.f9364c.mo5563a(74317).mo5513a(ffh.f9451a)).mo5560a(inflate);
            R.f7022n.f9364c.mo5563a(74873).mo5560a(R.f7028t);
            if (c != null) {
                c.close();
            }
            return inflate;
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
            this.f6967Z = true;
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

    /* renamed from: v */
    public final void mo2668v() {
        Object obj;
        hlq c = hnb.m11779c();
        try {
            mo7260T();
            dpt R = mo2635n();
            if (!R.f7032x) {
                R.mo4326a();
                apj a = R.f7015g.mo7307a();
                cxi cxi = R.f7010b;
                if (cxi.f5912d) {
                    obj = ebh.m7086a(cxi.f5910b);
                } else {
                    obj = cxi.f5910b;
                }
                ((apj) a.mo1419a(obj).mo1426b(R.f7021m.mo3299c()).mo1854a((aqu) new bfa(Long.valueOf(R.f7010b.f5918j)))).mo1421a(hnr.m11806a(R.f7007I));
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

    /* access modifiers changed from: private */
    /* renamed from: R */
    public final dpt mo2635n() {
        dpt dpt = this.f6968b;
        if (dpt == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f6967Z) {
            return dpt;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
