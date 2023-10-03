package p000;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.photosgo.R;
import com.google.android.apps.photosgo.photogrid.PhotoGridView;
import java.util.ArrayList;

/* renamed from: dwh */
/* compiled from: PG */
public final class dwh extends dyb implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f7488Z;

    /* renamed from: a */
    public final C0002ab f7489a = new C0002ab(this);

    /* renamed from: c */
    private dwn f7490c;

    /* renamed from: d */
    private Context f7491d;

    @Deprecated
    public dwh() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f7489a;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f7491d == null) {
            this.f7491d = new hcf((Context) this.f7636b);
        }
        return this.f7491d;
    }

    /* access modifiers changed from: protected */
    /* renamed from: R */
    public final /* bridge */ /* synthetic */ ftr mo4523R() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f7636b != null) {
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
            if (!this.f7488Z) {
                super.mo1832a(context);
                if (this.f7490c == null) {
                    this.f7490c = ((dws) mo2453b()).mo2385G();
                    this.f9583V.mo64a(new hbu(this.f7489a));
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
        Parcelable parcelable;
        hlq c = hnb.m11779c();
        try {
            mo7269c(layoutInflater, viewGroup, bundle);
            dwn Q = mo2635n();
            Q.f7512d.mo3758a((cqg) Q);
            Q.f7527s = layoutInflater.inflate(R.layout.photo_grid_fragment, viewGroup, false);
            Q.f7528t = ((PhotoGridView) Q.f7527s.findViewById(R.id.photo_grid)).mo2635n();
            int i = Q.f7517i.f7500g;
            if (i != 0) {
                Q.f7528t.f7556a.mo3753a(i);
            }
            dxg dxg = Q.f7528t;
            ArrayList arrayList = new ArrayList();
            arrayList.add(dwv.m6833j());
            for (int i2 = 0; i2 < 8; i2++) {
                arrayList.add(dwv.m6832i());
            }
            arrayList.add(dwv.m6833j());
            for (int i3 = 0; i3 < 15; i3++) {
                arrayList.add(dwv.m6832i());
            }
            dxg.mo4540a(arrayList, hvb.f13454a, dxy.DESCENDING_CAPTURE_TIMESTAMP);
            C0647xt layoutManager = ((RecyclerView) Q.f7527s.findViewById(R.id.recycler_view)).getLayoutManager();
            if (!(bundle == null || !bundle.containsKey("photogrid_recycler_view_state") || layoutManager == null || (parcelable = bundle.getParcelable("photogrid_recycler_view_state")) == null)) {
                layoutManager.mo10463a(parcelable);
            }
            if (bundle == null || !bundle.containsKey("selected_media_saved")) {
                Q.f7523o.mo4618a(hso.m12047f());
            } else {
                Q.f7529u = bundle.getBoolean("selected_media_saved", false);
            }
            if (!dvg.m6745a(Q.f7516h.mo2634k(), dwn.f7509b)) {
                Q.f7516h.mo5640a(dwn.f7509b);
            } else {
                Q.mo4527b();
            }
            cqe cqe = Q.f7517i.f7497d;
            if (cqe == null) {
                cqe = cqe.f5414d;
            }
            int a = cun.m5438a(cqe.f5417b);
            if (a != 0) {
                if (a == 2) {
                    Q.mo2621a();
                }
            }
            ((fea) Q.f7522n.f9364c.mo5563a(74307).mo5513a(ffh.f9451a)).mo5560a(Q.f7527s);
            View view = Q.f7527s;
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

    /* renamed from: f */
    public final void mo212f() {
        hlq c = hnb.m11779c();
        try {
            mo7263W();
            dwn Q = mo2635n();
            Q.f7512d.mo3761b((cqg) Q);
            Q.f7519k.f7839a.clear();
            Q.f7511c.mo3732b();
            Q.f7512d.mo3762c();
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
            this.f7488Z = true;
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
        dwn Q = mo2635n();
        if (i != 1) {
            StringBuilder sb = new StringBuilder(33);
            sb.append("Invalid Request Code: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        } else if (dvg.m6746a(dwn.f7509b, strArr, iArr)) {
            Q.mo4527b();
        }
    }

    /* renamed from: e */
    public final void mo168e(Bundle bundle) {
        super.mo168e(bundle);
        dwn Q = mo2635n();
        Q.f7523o.mo4618a(dwv.m6830a(Q.f7512d.f5420a));
        bundle.putBoolean("selected_media_saved", Q.f7512d.mo3764d());
        RecyclerView recyclerView = (RecyclerView) Q.f7527s.findViewById(R.id.recycler_view);
        C0647xt layoutManager = recyclerView.getLayoutManager();
        if (recyclerView != null && layoutManager != null) {
            bundle.putParcelable("photogrid_recycler_view_state", layoutManager.mo10476h());
        }
    }

    /* renamed from: d */
    public final void mo210d() {
        hlq c = hnb.m11779c();
        try {
            mo7259S();
            mo2635n().f7514f.mo2552c();
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
            dwn Q = mo2635n();
            Q.f7519k.f7840b.cancel(true);
            Q.f7514f.mo2550a();
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
            dwn Q = mo2635n();
            ihg.m13038a((C0147fh) this, bnd.class, (hol) new dwo(Q));
            ihg.m13038a((C0147fh) this, dxp.class, (hol) new dwp(Q));
            ihg.m13038a((C0147fh) this, bqj.class, (hol) new dwq(Q));
            ihg.m13038a((C0147fh) this, bqk.class, (hol) new dwr(Q));
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

    /* renamed from: Q */
    public final dwn mo2635n() {
        dwn dwn = this.f7490c;
        if (dwn == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f7488Z) {
            return dwn;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
