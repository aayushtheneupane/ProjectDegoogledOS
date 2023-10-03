package p000;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.preference.PreferenceScreen;

/* renamed from: ebq */
/* compiled from: PG */
public final class ebq extends ecd implements hbf, ioe, hbd {

    /* renamed from: af */
    private eby f7857af;

    /* renamed from: ag */
    private Context f7858ag;

    /* renamed from: ah */
    private final hkx f7859ah = new hkx(this);

    /* renamed from: ai */
    private final C0002ab f7860ai = new C0002ab(this);

    /* renamed from: aj */
    private boolean f7861aj;

    @Deprecated
    public ebq() {
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f7860ai;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f7858ag == null) {
            this.f7858ag = new hcf((Context) this.f7913ad);
        }
        return this.f7858ag;
    }

    /* access modifiers changed from: protected */
    /* renamed from: T */
    public final /* bridge */ /* synthetic */ ftr mo4666T() {
        return hcl.m11211d(this);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f7913ad != null) {
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
        hlq a = this.f7859ah.mo7537a();
        try {
            this.f10650ae.mo6245a(i, i2, intent);
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
            if (!this.f7861aj) {
                super.mo1832a(context);
                if (this.f7857af == null) {
                    this.f7857af = ((eca) mo2453b()).mo2388J();
                    this.f9583V.mo64a(new hbu(this.f7860ai));
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

    /* renamed from: R */
    public final void mo204R() {
        eby U = mo2635n();
        adv adv = U.f7878d.f218b;
        PreferenceScreen a = adv.mo229a(adv.f235a);
        U.f7878d.mo206a(a);
        a.mo1198q();
        gvi gvi = U.f7892r;
        ebp ebp = U.f7879e;
        gvi.mo7113a(ebp.f7855a.mo7085a(new ebn(ebp), "settings_data_service"), guy.DONT_CARE, U.f7874B);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x008b A[Catch:{ all -> 0x00ef, all -> 0x00f6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00df A[Catch:{ all -> 0x00ef, all -> 0x00f6 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View mo2630a(android.view.LayoutInflater r9, android.view.ViewGroup r10, android.os.Bundle r11) {
        /*
            r8 = this;
            hlq r0 = p000.hnb.m11779c()
            fvv r1 = r8.f10650ae     // Catch:{ all -> 0x00ef }
            r1.mo6222b(r11)     // Catch:{ all -> 0x00ef }
            android.content.Context r11 = r8.mo2634k()     // Catch:{ all -> 0x00ef }
            int[] r1 = p000.adz.f257h     // Catch:{ all -> 0x00ef }
            r2 = 0
            r3 = 2130969182(0x7f04025e, float:1.7547039E38)
            r4 = 0
            android.content.res.TypedArray r11 = r11.obtainStyledAttributes(r2, r1, r3, r4)     // Catch:{ all -> 0x00ef }
            int r1 = r8.f215aa     // Catch:{ all -> 0x00ef }
            int r1 = r11.getResourceId(r4, r1)     // Catch:{ all -> 0x00ef }
            r8.f215aa = r1     // Catch:{ all -> 0x00ef }
            r1 = 1
            android.graphics.drawable.Drawable r2 = r11.getDrawable(r1)     // Catch:{ all -> 0x00ef }
            r3 = 2
            r5 = -1
            int r3 = r11.getDimensionPixelSize(r3, r5)     // Catch:{ all -> 0x00ef }
            r6 = 3
            boolean r1 = r11.getBoolean(r6, r1)     // Catch:{ all -> 0x00ef }
            r11.recycle()     // Catch:{ all -> 0x00ef }
            android.content.Context r11 = r8.mo2634k()     // Catch:{ all -> 0x00ef }
            android.view.LayoutInflater r9 = r9.cloneInContext(r11)     // Catch:{ all -> 0x00ef }
            int r11 = r8.f215aa     // Catch:{ all -> 0x00ef }
            android.view.View r10 = r9.inflate(r11, r10, r4)     // Catch:{ all -> 0x00ef }
            r11 = 16908351(0x102003f, float:2.3877406E-38)
            android.view.View r11 = r10.findViewById(r11)     // Catch:{ all -> 0x00ef }
            boolean r6 = r11 instanceof android.view.ViewGroup     // Catch:{ all -> 0x00ef }
            if (r6 == 0) goto L_0x00e7
            android.view.ViewGroup r11 = (android.view.ViewGroup) r11     // Catch:{ all -> 0x00ef }
            android.content.Context r6 = r8.mo2634k()     // Catch:{ all -> 0x00ef }
            android.content.pm.PackageManager r6 = r6.getPackageManager()     // Catch:{ all -> 0x00ef }
            java.lang.String r7 = "android.hardware.type.automotive"
            boolean r6 = r6.hasSystemFeature(r7)     // Catch:{ all -> 0x00ef }
            if (r6 == 0) goto L_0x006b
            r6 = 2131362194(0x7f0a0192, float:1.8344162E38)
            android.view.View r6 = r11.findViewById(r6)     // Catch:{ all -> 0x00ef }
            android.support.v7.widget.RecyclerView r6 = (android.support.p002v7.widget.RecyclerView) r6     // Catch:{ all -> 0x00ef }
            if (r6 != 0) goto L_0x006a
            goto L_0x006b
        L_0x006a:
            goto L_0x0089
        L_0x006b:
            r6 = 2131558564(0x7f0d00a4, float:1.8742447E38)
            android.view.View r9 = r9.inflate(r6, r11, r4)     // Catch:{ all -> 0x00ef }
            r6 = r9
            android.support.v7.widget.RecyclerView r6 = (android.support.p002v7.widget.RecyclerView) r6     // Catch:{ all -> 0x00ef }
            wg r9 = new wg     // Catch:{ all -> 0x00ef }
            r8.mo2634k()     // Catch:{ all -> 0x00ef }
            r9.<init>()     // Catch:{ all -> 0x00ef }
            r6.setLayoutManager(r9)     // Catch:{ all -> 0x00ef }
            adx r9 = new adx     // Catch:{ all -> 0x00ef }
            r9.<init>(r6)     // Catch:{ all -> 0x00ef }
            r6.setAccessibilityDelegateCompat(r9)     // Catch:{ all -> 0x00ef }
            goto L_0x006a
        L_0x0089:
            if (r6 == 0) goto L_0x00df
            r8.f219c = r6     // Catch:{ all -> 0x00ef }
            adg r9 = r8.f214a     // Catch:{ all -> 0x00ef }
            r6.addItemDecoration(r9)     // Catch:{ all -> 0x00ef }
            adg r9 = r8.f214a     // Catch:{ all -> 0x00ef }
            if (r2 == 0) goto L_0x009d
            int r4 = r2.getIntrinsicHeight()     // Catch:{ all -> 0x00ef }
            r9.f210b = r4     // Catch:{ all -> 0x00ef }
            goto L_0x00a0
        L_0x009d:
            r9.f210b = r4     // Catch:{ all -> 0x00ef }
        L_0x00a0:
            r9.f209a = r2     // Catch:{ all -> 0x00ef }
            adk r9 = r9.f212d     // Catch:{ all -> 0x00ef }
            android.support.v7.widget.RecyclerView r9 = r9.f219c     // Catch:{ all -> 0x00ef }
            r9.invalidateItemDecorations()     // Catch:{ all -> 0x00ef }
            if (r3 == r5) goto L_0x00b6
            adg r9 = r8.f214a     // Catch:{ all -> 0x00ef }
            r9.f210b = r3     // Catch:{ all -> 0x00ef }
            adk r9 = r9.f212d     // Catch:{ all -> 0x00ef }
            android.support.v7.widget.RecyclerView r9 = r9.f219c     // Catch:{ all -> 0x00ef }
            r9.invalidateItemDecorations()     // Catch:{ all -> 0x00ef }
        L_0x00b6:
            adg r9 = r8.f214a     // Catch:{ all -> 0x00ef }
            r9.f211c = r1     // Catch:{ all -> 0x00ef }
            android.support.v7.widget.RecyclerView r9 = r8.f219c     // Catch:{ all -> 0x00ef }
            android.view.ViewParent r9 = r9.getParent()     // Catch:{ all -> 0x00ef }
            if (r9 == 0) goto L_0x00c3
            goto L_0x00c8
        L_0x00c3:
            android.support.v7.widget.RecyclerView r9 = r8.f219c     // Catch:{ all -> 0x00ef }
            r11.addView(r9)     // Catch:{ all -> 0x00ef }
        L_0x00c8:
            android.os.Handler r9 = r8.f216ab     // Catch:{ all -> 0x00ef }
            java.lang.Runnable r11 = r8.f217ac     // Catch:{ all -> 0x00ef }
            r9.post(r11)     // Catch:{ all -> 0x00ef }
            if (r10 == 0) goto L_0x00d7
            if (r0 == 0) goto L_0x00d6
            r0.close()
        L_0x00d6:
            return r10
        L_0x00d7:
            java.lang.NullPointerException r9 = new java.lang.NullPointerException     // Catch:{ all -> 0x00ef }
            java.lang.String r10 = "Fragment cannot use Event annotations with null view!"
            r9.<init>(r10)     // Catch:{ all -> 0x00ef }
            throw r9     // Catch:{ all -> 0x00ef }
        L_0x00df:
            java.lang.RuntimeException r9 = new java.lang.RuntimeException     // Catch:{ all -> 0x00ef }
            java.lang.String r10 = "Could not create RecyclerView"
            r9.<init>(r10)     // Catch:{ all -> 0x00ef }
            throw r9     // Catch:{ all -> 0x00ef }
        L_0x00e7:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch:{ all -> 0x00ef }
            java.lang.String r10 = "Content has view with id attribute 'android.R.id.list_container' that is not a ViewGroup class"
            r9.<init>(r10)     // Catch:{ all -> 0x00ef }
            throw r9     // Catch:{ all -> 0x00ef }
        L_0x00ef:
            r9 = move-exception
            if (r0 == 0) goto L_0x00fa
            r0.close()     // Catch:{ all -> 0x00f6 }
            goto L_0x00fa
        L_0x00f6:
            r10 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r9, (java.lang.Throwable) r10)
        L_0x00fa:
            goto L_0x00fc
        L_0x00fb:
            throw r9
        L_0x00fc:
            goto L_0x00fb
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.ebq.mo2630a(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle):android.view.View");
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
            this.f7861aj = true;
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
    public final boolean mo2666a(MenuItem menuItem) {
        hlq b = this.f7859ah.mo7538b();
        try {
            boolean o = this.f10650ae.mo6258o();
            if (b != null) {
                b.close();
            }
            return o;
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
        Bundle bundle2;
        hlq c = hnb.m11779c();
        try {
            hok.m11838a((Context) mo5653m()).f13171d = view;
            ihg.m13038a((C0147fh) this, ecm.class, (hol) new ebz(mo2635n()));
            this.f10650ae.mo6224c(bundle);
            if (!(bundle == null || (bundle2 = bundle.getBundle("android:preferences")) == null)) {
                PreferenceScreen am = mo208am();
                if (am != null) {
                    am.mo1177b(bundle2);
                }
            }
            if (this.f220d) {
                mo203Q();
            }
            this.f213Z = true;
            eby U = mo2635n();
            fdr a = ((fea) U.f7885k.f9364c.mo5563a(72469).mo5513a(ffh.f9451a)).mo5560a(view);
            U.f7898x = fhg.m8901a(a, 72471).mo5510a();
            U.f7899y = fhg.m8901a(a, 78842).mo5510a();
            U.f7900z = fhg.m8901a(a, 78843).mo5510a();
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
    /* renamed from: U */
    public final eby mo2635n() {
        eby eby = this.f7857af;
        if (eby == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f7861aj) {
            return eby;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
