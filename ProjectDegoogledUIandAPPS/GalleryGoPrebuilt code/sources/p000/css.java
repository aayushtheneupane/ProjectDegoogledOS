package p000;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

/* renamed from: css */
/* compiled from: PG */
public final class css extends ctl implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f5592Z;

    /* renamed from: b */
    private csu f5593b;

    /* renamed from: c */
    private Context f5594c;

    /* renamed from: d */
    private final C0002ab f5595d = new C0002ab(this);

    @Deprecated
    public css() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f5595d;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f5594c == null) {
            this.f5594c = new hcf((Context) this.f5633a);
        }
        return this.f5594c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo3805Q() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f5633a != null) {
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
            if (!this.f5592Z) {
                super.mo1832a(context);
                if (this.f5593b == null) {
                    this.f5593b = ((csv) mo2453b()).mo2379A();
                    this.f9583V.mo64a(new hbu(this.f5595d));
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

    /* JADX WARNING: Removed duplicated region for block: B:10:0x006a  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View mo2630a(android.view.LayoutInflater r6, android.view.ViewGroup r7, android.os.Bundle r8) {
        /*
            r5 = this;
            java.lang.String r0 = "android.permission.ACCESS_MEDIA_LOCATION"
            hlq r1 = p000.hnb.m11779c()
            r5.mo7269c(r6, r7, r8)     // Catch:{ all -> 0x006e }
            csu r8 = r5.mo2635n()     // Catch:{ all -> 0x006e }
            r2 = 2131558479(0x7f0d004f, float:1.8742275E38)
            r3 = 0
            android.view.View r6 = r6.inflate(r2, r7, r3)     // Catch:{ all -> 0x006e }
            r7 = 2131362045(0x7f0a00fd, float:1.834386E38)
            android.view.View r7 = r6.findViewById(r7)     // Catch:{ all -> 0x006e }
            com.google.android.apps.photosgo.infosheet.InfoSheetListView r7 = (com.google.android.apps.photosgo.infosheet.InfoSheetListView) r7     // Catch:{ all -> 0x006e }
            r8.f5602e = r7     // Catch:{ all -> 0x006e }
            blu r7 = r8.f5599b     // Catch:{ all -> 0x006e }
            r2 = 2131362046(0x7f0a00fe, float:1.8343862E38)
            android.view.View r2 = r6.findViewById(r2)     // Catch:{ all -> 0x006e }
            android.support.v7.widget.Toolbar r2 = (android.support.p002v7.widget.Toolbar) r2     // Catch:{ all -> 0x006e }
            r7.mo2573a((android.support.p002v7.widget.Toolbar) r2)     // Catch:{ all -> 0x006e }
            int r7 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x006e }
            r2 = 29
            if (r7 < r2) goto L_0x004f
            fh r7 = r8.f5598a     // Catch:{ all -> 0x006e }
            android.content.Context r7 = r7.mo2634k()     // Catch:{ all -> 0x006e }
            r2 = 1
            java.lang.String[] r4 = new java.lang.String[r2]     // Catch:{ all -> 0x006e }
            r4[r3] = r0     // Catch:{ all -> 0x006e }
            boolean r7 = p000.dvg.m6745a((android.content.Context) r7, (java.lang.String[]) r4)     // Catch:{ all -> 0x006e }
            if (r7 != 0) goto L_0x004f
            fh r7 = r8.f5598a     // Catch:{ all -> 0x006e }
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ all -> 0x006e }
            r2[r3] = r0     // Catch:{ all -> 0x006e }
            r7.mo5640a((java.lang.String[]) r2)     // Catch:{ all -> 0x006e }
            goto L_0x0052
        L_0x004f:
            r8.mo3806a()     // Catch:{ all -> 0x006e }
        L_0x0052:
            fee r7 = r8.f5601d     // Catch:{ all -> 0x006e }
            feb r7 = r7.f9364c     // Catch:{ all -> 0x006e }
            r8 = 74313(0x12249, float:1.04135E-40)
            fea r7 = r7.mo5563a(r8)     // Catch:{ all -> 0x006e }
            fdp r8 = p000.ffh.f9451a     // Catch:{ all -> 0x006e }
            fdj r7 = r7.mo5513a((p000.fdp) r8)     // Catch:{ all -> 0x006e }
            fea r7 = (p000.fea) r7     // Catch:{ all -> 0x006e }
            r7.mo5560a((android.view.View) r6)     // Catch:{ all -> 0x006e }
            if (r1 == 0) goto L_0x006d
            r1.close()
        L_0x006d:
            return r6
        L_0x006e:
            r6 = move-exception
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch:{ all -> 0x0075 }
            goto L_0x0079
        L_0x0075:
            r7 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r6, (java.lang.Throwable) r7)
        L_0x0079:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.css.mo2630a(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
    }

    /* renamed from: c */
    public final void mo1834c() {
        hlq c = hnb.m11779c();
        try {
            mo7265Y();
            this.f5592Z = true;
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
        csu R = mo2635n();
        if (i == 1) {
            R.mo3806a();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: R */
    public final csu mo2635n() {
        csu csu = this.f5593b;
        if (csu == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f5592Z) {
            return csu;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
