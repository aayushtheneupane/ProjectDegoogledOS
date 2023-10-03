package p000;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import com.google.android.apps.photosgo.R;

/* renamed from: bts */
/* compiled from: PG */
public final class bts extends bud implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f3564Z;

    /* renamed from: b */
    private btw f3565b;

    /* renamed from: c */
    private Context f3566c;

    /* renamed from: d */
    private final C0002ab f3567d = new C0002ab(this);

    @Deprecated
    public bts() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f3567d;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f3566c == null) {
            this.f3566c = new hcf((Context) this.f3609a);
        }
        return this.f3566c;
    }

    @Deprecated
    /* renamed from: a */
    public static bts m3583a(btt btt) {
        bts bts = new bts();
        ftr.m9611b(bts);
        hcl.m11210a(bts, btt);
        return bts;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo2757Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f3609a != null) {
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
            if (!this.f3564Z) {
                super.mo1832a(context);
                if (this.f3565b == null) {
                    this.f3565b = ((bub) mo2453b()).mo2440r();
                    this.f9583V.mo64a(new hbu(this.f3567d));
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
    public final void mo2758a(Menu menu, MenuInflater menuInflater) {
        super.mo2758a(menu, menuInflater);
        btw R = mo2635n();
        int a = flw.m9185a((Activity) R.f3583e);
        int i = a - 1;
        if (a == 0) {
            throw null;
        } else if (i != 4) {
            menuInflater.inflate(R.menu.single_device_folder_top_menu, menu);
            R.f3590l = menu.findItem(R.id.single_device_folder_select);
            R.f3591m = menu.findItem(R.id.single_device_folder_find_large_files);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0071 A[Catch:{ all -> 0x0177, all -> 0x017e }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0072 A[Catch:{ all -> 0x0177, all -> 0x017e }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008d A[Catch:{ all -> 0x0177, all -> 0x017e }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008e A[Catch:{ all -> 0x0177, all -> 0x017e }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0094 A[Catch:{ all -> 0x0177, all -> 0x017e }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0095 A[Catch:{ all -> 0x0177, all -> 0x017e }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View mo2630a(android.view.LayoutInflater r10, android.view.ViewGroup r11, android.os.Bundle r12) {
        /*
            r9 = this;
            hlq r0 = p000.hnb.m11779c()
            r9.mo7269c(r10, r11, r12)     // Catch:{ all -> 0x0177 }
            btw r1 = r9.mo2635n()     // Catch:{ all -> 0x0177 }
            r2 = 2131558585(0x7f0d00b9, float:1.874249E38)
            r3 = 0
            android.view.View r11 = r10.inflate(r2, r11, r3)     // Catch:{ all -> 0x0177 }
            r2 = 2131362073(0x7f0a0119, float:1.8343916E38)
            android.view.View r4 = r11.findViewById(r2)     // Catch:{ all -> 0x0177 }
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4     // Catch:{ all -> 0x0177 }
            r1.f3588j = r4     // Catch:{ all -> 0x0177 }
            r1.mo2759a((android.view.View) r11)     // Catch:{ all -> 0x0177 }
            r4 = 4
            r5 = 1
            if (r12 == 0) goto L_0x0027
            goto L_0x00cd
        L_0x0027:
            dwi r12 = p000.dwi.f7492h     // Catch:{ all -> 0x0177 }
            iir r12 = r12.mo8793g()     // Catch:{ all -> 0x0177 }
            btt r6 = r1.f3581c     // Catch:{ all -> 0x0177 }
            cxd r6 = r6.f3573d     // Catch:{ all -> 0x0177 }
            if (r6 == 0) goto L_0x0034
            goto L_0x0036
        L_0x0034:
            cxd r6 = p000.cxd.f5884h     // Catch:{ all -> 0x0177 }
        L_0x0036:
            boolean r7 = r12.f14319c     // Catch:{ all -> 0x0177 }
            if (r7 != 0) goto L_0x003b
            goto L_0x0040
        L_0x003b:
            r12.mo8751b()     // Catch:{ all -> 0x0177 }
            r12.f14319c = r3     // Catch:{ all -> 0x0177 }
        L_0x0040:
            iix r7 = r12.f14318b     // Catch:{ all -> 0x0177 }
            dwi r7 = (p000.dwi) r7     // Catch:{ all -> 0x0177 }
            r6.getClass()     // Catch:{ all -> 0x0177 }
            r7.f7495b = r6     // Catch:{ all -> 0x0177 }
            int r6 = r7.f7494a     // Catch:{ all -> 0x0177 }
            r6 = r6 | r5
            r7.f7494a = r6     // Catch:{ all -> 0x0177 }
            cqe r8 = r1.f3582d     // Catch:{ all -> 0x0177 }
            r8.getClass()     // Catch:{ all -> 0x0177 }
            r7.f7497d = r8     // Catch:{ all -> 0x0177 }
            r6 = r6 | r4
            r7.f7494a = r6     // Catch:{ all -> 0x0177 }
            btt r6 = r1.f3581c     // Catch:{ all -> 0x0177 }
            boolean r7 = r6.f3574e     // Catch:{ all -> 0x0177 }
            if (r7 == 0) goto L_0x006b
            cxe r6 = r6.f3571b     // Catch:{ all -> 0x0177 }
            if (r6 == 0) goto L_0x0063
            goto L_0x0065
        L_0x0063:
            cxe r6 = p000.cxe.f5893f     // Catch:{ all -> 0x0177 }
        L_0x0065:
            boolean r6 = r6.f5899e     // Catch:{ all -> 0x0177 }
            if (r6 == 0) goto L_0x006b
            r6 = 1
            goto L_0x006d
        L_0x006b:
            r6 = 0
        L_0x006d:
            boolean r7 = r12.f14319c     // Catch:{ all -> 0x0177 }
            if (r7 != 0) goto L_0x0072
            goto L_0x0077
        L_0x0072:
            r12.mo8751b()     // Catch:{ all -> 0x0177 }
            r12.f14319c = r3     // Catch:{ all -> 0x0177 }
        L_0x0077:
            iix r7 = r12.f14318b     // Catch:{ all -> 0x0177 }
            dwi r7 = (p000.dwi) r7     // Catch:{ all -> 0x0177 }
            int r8 = r7.f7494a     // Catch:{ all -> 0x0177 }
            r8 = r8 | 2
            r7.f7494a = r8     // Catch:{ all -> 0x0177 }
            r7.f7496c = r6     // Catch:{ all -> 0x0177 }
            btt r6 = r1.f3581c     // Catch:{ all -> 0x0177 }
            int r6 = r6.f3575f     // Catch:{ all -> 0x0177 }
            cpi r6 = p000.cpi.m5217a(r6)     // Catch:{ all -> 0x0177 }
            if (r6 == 0) goto L_0x008e
            goto L_0x0090
        L_0x008e:
            cpi r6 = p000.cpi.HOME     // Catch:{ all -> 0x0177 }
        L_0x0090:
            boolean r7 = r12.f14319c     // Catch:{ all -> 0x0177 }
            if (r7 != 0) goto L_0x0095
            goto L_0x009a
        L_0x0095:
            r12.mo8751b()     // Catch:{ all -> 0x0177 }
            r12.f14319c = r3     // Catch:{ all -> 0x0177 }
        L_0x009a:
            iix r7 = r12.f14318b     // Catch:{ all -> 0x0177 }
            dwi r7 = (p000.dwi) r7     // Catch:{ all -> 0x0177 }
            int r6 = r6.f5364h     // Catch:{ all -> 0x0177 }
            r7.f7498e = r6     // Catch:{ all -> 0x0177 }
            int r6 = r7.f7494a     // Catch:{ all -> 0x0177 }
            r6 = r6 | 8
            r7.f7494a = r6     // Catch:{ all -> 0x0177 }
            r6 = r6 | 32
            r7.f7494a = r6     // Catch:{ all -> 0x0177 }
            r6 = 2131558463(0x7f0d003f, float:1.8742243E38)
            r7.f7500g = r6     // Catch:{ all -> 0x0177 }
            iix r12 = r12.mo8770g()     // Catch:{ all -> 0x0177 }
            dwi r12 = (p000.dwi) r12     // Catch:{ all -> 0x0177 }
            dwh r12 = p000.dwn.m6817a((p000.dwi) r12)     // Catch:{ all -> 0x0177 }
            bts r6 = r1.f3579a     // Catch:{ all -> 0x0177 }
            gd r6 = r6.mo5659r()     // Catch:{ all -> 0x0177 }
            gn r6 = r6.mo6419a()     // Catch:{ all -> 0x0177 }
            java.lang.String r7 = "photo_grid_fragment"
            r6.mo6850a(r2, r12, r7)     // Catch:{ all -> 0x0177 }
            r6.mo5244a()     // Catch:{ all -> 0x0177 }
        L_0x00cd:
            ok r12 = r1.f3583e     // Catch:{ all -> 0x0177 }
            int r12 = p000.flw.m9185a((android.app.Activity) r12)     // Catch:{ all -> 0x0177 }
            int r2 = r12 + -1
            if (r12 == 0) goto L_0x0175
            if (r2 == r4) goto L_0x012d
            r12 = 2131558476(0x7f0d004c, float:1.8742269E38)
            r2 = 2131362247(0x7f0a01c7, float:1.834427E38)
            android.view.View r2 = r11.findViewById(r2)     // Catch:{ all -> 0x0177 }
            android.support.v7.widget.Toolbar r2 = (android.support.p002v7.widget.Toolbar) r2     // Catch:{ all -> 0x0177 }
            android.view.View r10 = r10.inflate(r12, r2, r5)     // Catch:{ all -> 0x0177 }
            r12 = 2131361999(0x7f0a00cf, float:1.8343766E38)
            android.view.View r12 = r10.findViewById(r12)     // Catch:{ all -> 0x0177 }
            android.widget.TextView r12 = (android.widget.TextView) r12     // Catch:{ all -> 0x0177 }
            r2 = 2131361997(0x7f0a00cd, float:1.8343762E38)
            android.view.View r10 = r10.findViewById(r2)     // Catch:{ all -> 0x0177 }
            android.widget.TextView r10 = (android.widget.TextView) r10     // Catch:{ all -> 0x0177 }
            btt r2 = r1.f3581c     // Catch:{ all -> 0x0177 }
            cxe r2 = r2.f3571b     // Catch:{ all -> 0x0177 }
            if (r2 == 0) goto L_0x0102
            goto L_0x0104
        L_0x0102:
            cxe r2 = p000.cxe.f5893f     // Catch:{ all -> 0x0177 }
        L_0x0104:
            java.lang.String r2 = r2.f5897c     // Catch:{ all -> 0x0177 }
            r12.setText(r2)     // Catch:{ all -> 0x0177 }
            btv r12 = new btv     // Catch:{ all -> 0x0177 }
            r12.<init>(r1, r10)     // Catch:{ all -> 0x0177 }
            gvi r10 = r1.f3585g     // Catch:{ all -> 0x0177 }
            bui r2 = r1.f3584f     // Catch:{ all -> 0x0177 }
            btt r4 = r1.f3581c     // Catch:{ all -> 0x0177 }
            cxd r4 = r4.f3573d     // Catch:{ all -> 0x0177 }
            if (r4 == 0) goto L_0x0119
            goto L_0x011b
        L_0x0119:
            cxd r4 = p000.cxd.f5884h     // Catch:{ all -> 0x0177 }
        L_0x011b:
            buh r5 = new buh     // Catch:{ all -> 0x0177 }
            r5.<init>(r2, r4)     // Catch:{ all -> 0x0177 }
            guy r2 = p000.guy.DONT_CARE     // Catch:{ all -> 0x0177 }
            r10.mo7113a(r5, r2, r12)     // Catch:{ all -> 0x0177 }
            nz r10 = r1.mo2759a((android.view.View) r11)     // Catch:{ all -> 0x0177 }
            r10.mo9493b((boolean) r3)     // Catch:{ all -> 0x0177 }
            goto L_0x0134
        L_0x012d:
            nz r10 = r1.mo2759a((android.view.View) r11)     // Catch:{ all -> 0x0177 }
            r10.mo9493b((boolean) r5)     // Catch:{ all -> 0x0177 }
        L_0x0134:
            fee r10 = r1.f3586h     // Catch:{ all -> 0x0177 }
            feb r10 = r10.f9364c     // Catch:{ all -> 0x0177 }
            r12 = 74309(0x12245, float:1.04129E-40)
            fea r10 = r10.mo5563a(r12)     // Catch:{ all -> 0x0177 }
            fdp r12 = p000.ffh.f9451a     // Catch:{ all -> 0x0177 }
            fdj r10 = r10.mo5513a((p000.fdp) r12)     // Catch:{ all -> 0x0177 }
            fea r10 = (p000.fea) r10     // Catch:{ all -> 0x0177 }
            fdr r10 = r10.mo5560a((android.view.View) r11)     // Catch:{ all -> 0x0177 }
            r12 = 74900(0x12494, float:1.04957E-40)
            fdi r12 = p000.fhg.m8901a((p000.fdr) r10, (int) r12)     // Catch:{ all -> 0x0177 }
            fdr r12 = r12.mo5510a()     // Catch:{ all -> 0x0177 }
            r1.f3592n = r12     // Catch:{ all -> 0x0177 }
            r12 = 80410(0x13a1a, float:1.12678E-40)
            fdi r10 = p000.fhg.m8901a((p000.fdr) r10, (int) r12)     // Catch:{ all -> 0x0177 }
            fdr r10 = r10.mo5510a()     // Catch:{ all -> 0x0177 }
            r1.f3593o = r10     // Catch:{ all -> 0x0177 }
            if (r11 == 0) goto L_0x016d
            if (r0 == 0) goto L_0x016c
            r0.close()
        L_0x016c:
            return r11
        L_0x016d:
            java.lang.NullPointerException r10 = new java.lang.NullPointerException     // Catch:{ all -> 0x0177 }
            java.lang.String r11 = "Fragment cannot use Event annotations with null view!"
            r10.<init>(r11)     // Catch:{ all -> 0x0177 }
            throw r10     // Catch:{ all -> 0x0177 }
        L_0x0175:
            r10 = 0
            throw r10     // Catch:{ all -> 0x0177 }
        L_0x0177:
            r10 = move-exception
            if (r0 == 0) goto L_0x0182
            r0.close()     // Catch:{ all -> 0x017e }
            goto L_0x0182
        L_0x017e:
            r11 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r10, (java.lang.Throwable) r11)
        L_0x0182:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bts.mo2630a(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
    }

    /* renamed from: c */
    public final void mo1834c() {
        hlq c = hnb.m11779c();
        try {
            mo7265Y();
            this.f3564Z = true;
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
            btw R = mo2635n();
            ihg.m13038a((C0147fh) this, cjw.class, (hol) new btx(R));
            ihg.m13038a((C0147fh) this, dvr.class, (hol) new bty(R));
            ihg.m13038a((C0147fh) this, dwz.class, (hol) new btz(R));
            ihg.m13038a((C0147fh) this, cpj.class, (hol) new bua(R));
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
    public final btw mo2635n() {
        btw btw = this.f3565b;
        if (btw == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f3564Z) {
            return btw;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
