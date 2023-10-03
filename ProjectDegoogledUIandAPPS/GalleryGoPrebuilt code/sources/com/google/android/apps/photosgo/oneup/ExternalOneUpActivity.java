package com.google.android.apps.photosgo.oneup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;

/* compiled from: PG */
public final class ExternalOneUpActivity extends dok implements hbf, hbe, hbs {

    /* renamed from: e */
    private dlc f4869e;

    /* renamed from: f */
    private final hkn f4870f = new hkn(this);

    /* renamed from: g */
    private boolean f4871g;

    /* renamed from: h */
    private Context f4872h;

    /* renamed from: i */
    private final long f4873i = SystemClock.elapsedRealtime();

    /* renamed from: j */
    private C0002ab f4874j;

    /* renamed from: k */
    private boolean f4875k;

    /* renamed from: l */
    public final long mo3320l() {
        return this.f4873i;
    }

    public final void applyOverrideConfiguration(Configuration configuration) {
        Context baseContext = getBaseContext();
        if (baseContext == null) {
            baseContext = this.f4872h;
        }
        super.applyOverrideConfiguration(hov.m11853a(baseContext, configuration));
    }

    /* access modifiers changed from: protected */
    public final void attachBaseContext(Context context) {
        this.f4872h = context;
        super.attachBaseContext(hov.m11852a(context));
        this.f4872h = null;
    }

    /* renamed from: m */
    public final /* bridge */ /* synthetic */ ftl mo2566m() {
        return hck.m11206a((Activity) this);
    }

    /* renamed from: o */
    private final void m4806o() {
        if (this.f4869e != null) {
            return;
        }
        if (!this.f4871g) {
            throw new IllegalStateException("createPeer() called outside of onCreate");
        } else if (this.f4875k && !isFinishing()) {
            throw new IllegalStateException("createPeer() called after destroyed.");
        } else {
            hlj a = hnb.m11765a("CreateComponent");
            try {
                mo2453b();
                if (a != null) {
                    a.close();
                }
                hlj a2 = hnb.m11765a("CreatePeer");
                try {
                    this.f4869e = ((dlf) mo2453b()).mo2359e();
                    if (a2 != null) {
                        a2.close();
                    }
                    this.f4869e.f6772f = this;
                    return;
                } catch (ClassCastException e) {
                    throw new IllegalStateException("Missing entry point. If you're in a test with explicit entry points specified in your @TestRoot, check that you're not missing the one for this class.", e);
                } catch (Throwable th) {
                    ifn.m12935a(th, th);
                }
            } catch (Throwable th2) {
                ifn.m12935a(th, th2);
            }
        }
        throw th;
        throw th;
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        if (this.f4874j == null) {
            this.f4874j = new hbt(this);
        }
        return this.f4874j;
    }

    /* renamed from: p */
    private final dlc m4807p() {
        m4806o();
        return this.f4869e;
    }

    /* access modifiers changed from: protected */
    public final void onActivityResult(int i, int i2, Intent intent) {
        hlq l = this.f4870f.mo7529l();
        try {
            super.onActivityResult(i, i2, intent);
            if (l != null) {
                l.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final void onBackPressed() {
        hlq h = this.f4870f.mo7525h();
        try {
            dlc p = m4807p();
            if (!p.f6767b.mo3274e()) {
                super.onBackPressed();
            }
            if (h != null) {
                h.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCreate(android.os.Bundle r8) {
        /*
            r7 = this;
            java.lang.String r0 = "brightness"
            hkn r1 = r7.f4870f
            hlq r1 = r1.mo7530m()
            r2 = 1
            r7.f4871g = r2     // Catch:{ all -> 0x0103 }
            r7.m4806o()     // Catch:{ all -> 0x0103 }
            w r2 = r7.mo5ad()     // Catch:{ all -> 0x0103 }
            hbt r2 = (p000.hbt) r2     // Catch:{ all -> 0x0103 }
            hkn r3 = r7.f4870f     // Catch:{ all -> 0x0103 }
            r2.mo7270a((p000.hkn) r3)     // Catch:{ all -> 0x0103 }
            java.lang.Object r2 = r7.mo2453b()     // Catch:{ all -> 0x0103 }
            hco r2 = (p000.hco) r2     // Catch:{ all -> 0x0103 }
            hcq r2 = r2.mo2369o()     // Catch:{ all -> 0x0103 }
            r2.mo7280a()     // Catch:{ all -> 0x0103 }
            dlc r2 = r7.m4807p()     // Catch:{ all -> 0x0103 }
            com.google.android.apps.photosgo.oneup.ExternalOneUpActivity r3 = r2.f6766a     // Catch:{ all -> 0x0103 }
            r4 = 2131951623(0x7f130007, float:1.9539666E38)
            r3.setTheme(r4)     // Catch:{ all -> 0x0103 }
            com.google.android.apps.photosgo.oneup.ExternalOneUpActivity r3 = r2.f6772f     // Catch:{ all -> 0x0103 }
            super.onCreate(r8)     // Catch:{ all -> 0x0103 }
            com.google.android.apps.photosgo.oneup.ExternalOneUpActivity r3 = r2.f6766a     // Catch:{ all -> 0x0103 }
            android.content.Intent r3 = r3.getIntent()     // Catch:{ all -> 0x0103 }
            java.lang.String r4 = "android.intent.extra.FROM_STORAGE"
            r5 = 0
            boolean r4 = r3.getBooleanExtra(r4, r5)     // Catch:{ all -> 0x0103 }
            if (r4 != 0) goto L_0x0047
            goto L_0x0077
        L_0x0047:
            java.lang.String r4 = "android.intent.action.VIEW"
            java.lang.String r6 = r3.getAction()     // Catch:{ all -> 0x0103 }
            boolean r4 = r4.equals(r6)     // Catch:{ all -> 0x0103 }
            if (r4 == 0) goto L_0x0077
            java.lang.String r4 = "image/*"
            java.lang.String r6 = r3.getType()     // Catch:{ all -> 0x0103 }
            boolean r4 = r4.equals(r6)     // Catch:{ all -> 0x0103 }
            if (r4 == 0) goto L_0x0077
            android.net.Uri r3 = r3.getData()     // Catch:{ all -> 0x0103 }
            if (r3 != 0) goto L_0x0077
            com.google.android.apps.photosgo.oneup.ExternalOneUpActivity r8 = r2.f6766a     // Catch:{ all -> 0x0103 }
            android.content.Intent r0 = new android.content.Intent     // Catch:{ all -> 0x0103 }
            java.lang.Class r3 = r2.f6769d     // Catch:{ all -> 0x0103 }
            r0.<init>(r8, r3)     // Catch:{ all -> 0x0103 }
            r8.startActivity(r0)     // Catch:{ all -> 0x0103 }
            com.google.android.apps.photosgo.oneup.ExternalOneUpActivity r8 = r2.f6766a     // Catch:{ all -> 0x0103 }
            r8.finish()     // Catch:{ all -> 0x0103 }
            goto L_0x00e2
        L_0x0077:
            fee r3 = r2.f6768c     // Catch:{ all -> 0x0103 }
            feb r3 = r3.f9364c     // Catch:{ all -> 0x0103 }
            r4 = 74302(0x1223e, float:1.04119E-40)
            fea r3 = r3.mo5563a(r4)     // Catch:{ all -> 0x0103 }
            fdp r4 = p000.fej.m8698a()     // Catch:{ all -> 0x0103 }
            fdj r3 = r3.mo5513a((p000.fdp) r4)     // Catch:{ all -> 0x0103 }
            fea r3 = (p000.fea) r3     // Catch:{ all -> 0x0103 }
            fdp r4 = p000.ffh.f9451a     // Catch:{ all -> 0x0103 }
            fdj r3 = r3.mo5513a((p000.fdp) r4)     // Catch:{ all -> 0x0103 }
            fea r3 = (p000.fea) r3     // Catch:{ all -> 0x0103 }
            com.google.android.apps.photosgo.oneup.ExternalOneUpActivity r4 = r2.f6766a     // Catch:{ all -> 0x0103 }
            r3.mo5561a((android.app.Activity) r4)     // Catch:{ all -> 0x0103 }
            if (r8 == 0) goto L_0x00a1
            cnp r3 = r2.f6767b     // Catch:{ all -> 0x0103 }
            r3.mo3280a((android.os.Bundle) r8)     // Catch:{ all -> 0x0103 }
            goto L_0x00ab
        L_0x00a1:
            cnp r8 = r2.f6767b     // Catch:{ all -> 0x0103 }
            dkv r3 = new dkv     // Catch:{ all -> 0x0103 }
            r3.<init>(r2)     // Catch:{ all -> 0x0103 }
            r8.mo3281a((p003j$.util.function.Supplier) r3)     // Catch:{ all -> 0x0103 }
        L_0x00ab:
            com.google.android.apps.photosgo.oneup.ExternalOneUpActivity r8 = r2.f6766a     // Catch:{ all -> 0x0103 }
            android.content.Intent r8 = r8.getIntent()     // Catch:{ all -> 0x0103 }
            java.lang.String r3 = "com.android.camera.action.REVIEW"
            java.lang.String r4 = r8.getAction()     // Catch:{ all -> 0x0103 }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x0103 }
            android.os.Bundle r8 = r8.getExtras()     // Catch:{ all -> 0x0103 }
            if (r3 == 0) goto L_0x00e2
            if (r8 == 0) goto L_0x00e2
            boolean r3 = r8.containsKey(r0)     // Catch:{ all -> 0x0103 }
            if (r3 == 0) goto L_0x00e2
            com.google.android.apps.photosgo.oneup.ExternalOneUpActivity r3 = r2.f6766a     // Catch:{ all -> 0x0103 }
            android.view.Window r3 = r3.getWindow()     // Catch:{ all -> 0x0103 }
            android.view.WindowManager$LayoutParams r3 = r3.getAttributes()     // Catch:{ all -> 0x0103 }
            float r8 = r8.getFloat(r0)     // Catch:{ all -> 0x0103 }
            r3.screenBrightness = r8     // Catch:{ all -> 0x0103 }
            com.google.android.apps.photosgo.oneup.ExternalOneUpActivity r8 = r2.f6766a     // Catch:{ all -> 0x0103 }
            android.view.Window r8 = r8.getWindow()     // Catch:{ all -> 0x0103 }
            r8.setAttributes(r3)     // Catch:{ all -> 0x0103 }
        L_0x00e2:
            r8 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r8 = r7.findViewById(r8)     // Catch:{ all -> 0x0103 }
            hos r0 = p000.hok.m11838a((android.content.Context) r7)     // Catch:{ all -> 0x0103 }
            r0.f13171d = r8     // Catch:{ all -> 0x0103 }
            dlc r8 = r7.f4869e     // Catch:{ all -> 0x0103 }
            dld r0 = new dld     // Catch:{ all -> 0x0103 }
            r0.<init>(r8)     // Catch:{ all -> 0x0103 }
            java.lang.Class<blv> r8 = p000.blv.class
            p000.ihg.m13033a((android.app.Activity) r7, (java.lang.Class) r8, (p000.hol) r0)     // Catch:{ all -> 0x0103 }
            r7.f4871g = r5     // Catch:{ all -> 0x0103 }
            if (r1 == 0) goto L_0x0102
            r1.close()
        L_0x0102:
            return
        L_0x0103:
            r8 = move-exception
            if (r1 == 0) goto L_0x010e
            r1.close()     // Catch:{ all -> 0x010a }
            goto L_0x010e
        L_0x010a:
            r0 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r8, (java.lang.Throwable) r0)
        L_0x010e:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.photosgo.oneup.ExternalOneUpActivity.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: protected */
    public final void onDestroy() {
        hlq g = this.f4870f.mo7524g();
        try {
            super.onDestroy();
            this.f4875k = true;
            g.close();
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final boolean onNavigateUp() {
        return m4807p().f6767b.mo3274e();
    }

    /* access modifiers changed from: protected */
    public final void onNewIntent(Intent intent) {
        hlq a = this.f4870f.mo7516a(intent);
        try {
            dlc p = m4807p();
            super.onNewIntent(intent);
            p.f6766a.finish();
            p.f6766a.startActivity(intent);
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

    public final boolean onOptionsItemSelected(MenuItem menuItem) {
        hlq n = this.f4870f.mo7531n();
        try {
            boolean onOptionsItemSelected = super.onOptionsItemSelected(menuItem);
            if (n != null) {
                n.close();
            }
            return onOptionsItemSelected;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public final void onPause() {
        hlq d = this.f4870f.mo7521d();
        try {
            super.onPause();
            if (d != null) {
                d.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public final void onPostCreate(Bundle bundle) {
        hlq o = this.f4870f.mo7532o();
        try {
            super.onPostCreate(bundle);
            if (o != null) {
                o.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public final void onPostResume() {
        hlq c = this.f4870f.mo7520c();
        try {
            super.onPostResume();
            c.close();
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        hlq p = this.f4870f.mo7533p();
        try {
            super.onRequestPermissionsResult(i, strArr, iArr);
            if (p != null) {
                p.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public final void onResume() {
        hlq b = this.f4870f.mo7519b();
        try {
            super.onResume();
            if (b != null) {
                b.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: k */
    public final void mo7k() {
        this.f4870f.mo7523f().close();
    }

    /* access modifiers changed from: protected */
    public final void onSaveInstanceState(Bundle bundle) {
        hlq q = this.f4870f.mo7534q();
        try {
            dlc p = m4807p();
            p.f6767b.mo3282b(bundle);
            super.onSaveInstanceState(bundle);
            if (q != null) {
                q.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public final void onStart() {
        hlq a = this.f4870f.mo7515a();
        try {
            super.onStart();
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

    /* access modifiers changed from: protected */
    public final void onStop() {
        hlq e = this.f4870f.mo7522e();
        try {
            super.onStop();
            if (e != null) {
                e.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: h */
    public final boolean mo3319h() {
        hlq i = this.f4870f.mo7526i();
        try {
            boolean e = m4807p().f6767b.mo3274e();
            if (i != null) {
                i.close();
            }
            return e;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final void onWindowFocusChanged(boolean z) {
        dlc p = m4807p();
        super.onWindowFocusChanged(z);
        if (z) {
            p.f6767b.mo3284h();
        }
    }

    /* renamed from: n */
    public final /* bridge */ /* synthetic */ Object mo2635n() {
        dlc dlc = this.f4869e;
        if (dlc == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f4875k) {
            return dlc;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
