package com.google.android.apps.photosgo.editor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;

/* compiled from: PG */
public final class ExternalEditorActivity extends bxe implements hbf, hbe, hbs {

    /* renamed from: e */
    private bwh f4817e;

    /* renamed from: f */
    private final hkn f4818f = new hkn(this);

    /* renamed from: g */
    private boolean f4819g;

    /* renamed from: h */
    private Context f4820h;

    /* renamed from: i */
    private final long f4821i = SystemClock.elapsedRealtime();

    /* renamed from: j */
    private C0002ab f4822j;

    /* renamed from: k */
    private boolean f4823k;

    /* renamed from: l */
    public final long mo3320l() {
        return this.f4821i;
    }

    public final void applyOverrideConfiguration(Configuration configuration) {
        Context baseContext = getBaseContext();
        if (baseContext == null) {
            baseContext = this.f4820h;
        }
        super.applyOverrideConfiguration(hov.m11853a(baseContext, configuration));
    }

    /* access modifiers changed from: protected */
    public final void attachBaseContext(Context context) {
        this.f4820h = context;
        super.attachBaseContext(hov.m11852a(context));
        this.f4820h = null;
    }

    /* renamed from: m */
    public final /* bridge */ /* synthetic */ ftl mo2566m() {
        return hck.m11206a((Activity) this);
    }

    /* renamed from: o */
    private final void m4754o() {
        if (this.f4817e != null) {
            return;
        }
        if (!this.f4819g) {
            throw new IllegalStateException("createPeer() called outside of onCreate");
        } else if (this.f4823k && !isFinishing()) {
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
                    this.f4817e = ((bwm) mo2453b()).mo2357c();
                    if (a2 != null) {
                        a2.close();
                    }
                    this.f4817e.f3772e = this;
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
        if (this.f4822j == null) {
            this.f4822j = new hbt(this);
        }
        return this.f4822j;
    }

    /* renamed from: p */
    private final bwh m4755p() {
        m4754o();
        return this.f4817e;
    }

    /* access modifiers changed from: protected */
    public final void onActivityResult(int i, int i2, Intent intent) {
        hlq l = this.f4818f.mo7529l();
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
        hlq h = this.f4818f.mo7525h();
        try {
            super.onBackPressed();
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
    /* JADX WARNING: Removed duplicated region for block: B:27:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCreate(android.os.Bundle r9) {
        /*
            r8 = this;
            hkn r0 = r8.f4818f
            hlq r0 = r0.mo7530m()
            r1 = 1
            r8.f4819g = r1     // Catch:{ all -> 0x011f }
            r8.m4754o()     // Catch:{ all -> 0x011f }
            w r2 = r8.mo5ad()     // Catch:{ all -> 0x011f }
            hbt r2 = (p000.hbt) r2     // Catch:{ all -> 0x011f }
            hkn r3 = r8.f4818f     // Catch:{ all -> 0x011f }
            r2.mo7270a((p000.hkn) r3)     // Catch:{ all -> 0x011f }
            java.lang.Object r2 = r8.mo2453b()     // Catch:{ all -> 0x011f }
            hco r2 = (p000.hco) r2     // Catch:{ all -> 0x011f }
            hcq r2 = r2.mo2369o()     // Catch:{ all -> 0x011f }
            r2.mo7280a()     // Catch:{ all -> 0x011f }
            super.onCreate(r9)     // Catch:{ all -> 0x011f }
            bwh r2 = r8.m4755p()     // Catch:{ all -> 0x011f }
            r3 = 0
            if (r9 != 0) goto L_0x00c3
            com.google.android.apps.photosgo.editor.ExternalEditorActivity r9 = r2.f3766b     // Catch:{ all -> 0x011f }
            android.content.Intent r9 = r9.getIntent()     // Catch:{ all -> 0x011f }
            android.net.Uri r4 = r9.getData()     // Catch:{ all -> 0x011f }
            if (r4 == 0) goto L_0x00b8
            com.google.android.apps.photosgo.editor.ExternalEditorActivity r5 = r2.f3766b     // Catch:{ all -> 0x011f }
            int r6 = android.os.Binder.getCallingPid()     // Catch:{ all -> 0x011f }
            int r7 = android.os.Binder.getCallingUid()     // Catch:{ all -> 0x011f }
            int r5 = r5.checkUriPermission(r4, r6, r7, r1)     // Catch:{ all -> 0x011f }
            if (r5 == 0) goto L_0x0062
            com.google.android.apps.photosgo.editor.ExternalEditorActivity r5 = r2.f3766b     // Catch:{ all -> 0x011f }
            java.lang.String[] r6 = p000.bwh.f3765a     // Catch:{ all -> 0x011f }
            boolean r5 = p000.dvg.m6745a((android.content.Context) r5, (java.lang.String[]) r6)     // Catch:{ all -> 0x011f }
            if (r5 != 0) goto L_0x0062
            java.lang.String r9 = "ExternalEditorActivity Read permission is not granted for the uri[%s]."
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x011f }
            r1[r3] = r4     // Catch:{ all -> 0x011f }
            p000.cwn.m5514b(r9, r1)     // Catch:{ all -> 0x011f }
            r2.mo2833c()     // Catch:{ all -> 0x011f }
            goto L_0x00ea
        L_0x0062:
            java.lang.String r4 = r9.getAction()     // Catch:{ all -> 0x011f }
            java.lang.String r5 = "Intent action cannot be null."
            java.lang.Object r4 = p000.ife.m12869b((java.lang.Object) r4, (java.lang.Object) r5)     // Catch:{ all -> 0x011f }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x011f }
            java.lang.String r5 = "android.intent.action.EDIT"
            boolean r5 = p000.ife.m12854a((java.lang.CharSequence) r5, (java.lang.CharSequence) r4)     // Catch:{ all -> 0x011f }
            if (r5 != 0) goto L_0x00ad
            java.lang.String r5 = "action_nextgen_edit"
            boolean r5 = p000.ife.m12854a((java.lang.CharSequence) r5, (java.lang.CharSequence) r4)     // Catch:{ all -> 0x011f }
            if (r5 != 0) goto L_0x00ad
            java.lang.String r5 = "com.android.camera.action.CROP"
            boolean r5 = p000.ife.m12854a((java.lang.CharSequence) r5, (java.lang.CharSequence) r4)     // Catch:{ all -> 0x011f }
            if (r5 != 0) goto L_0x00ad
            java.lang.String r5 = "com.android.camera.action.TRIM"
            boolean r5 = p000.ife.m12854a((java.lang.CharSequence) r5, (java.lang.CharSequence) r4)     // Catch:{ all -> 0x011f }
            if (r5 != 0) goto L_0x00a2
            java.lang.String r9 = "ExternalEditorActivity: Unsupported intent action: %s. Exiting the activity with RESULT_CANCELED"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x011f }
            r1[r3] = r4     // Catch:{ all -> 0x011f }
            p000.cwn.m5514b(r9, r1)     // Catch:{ all -> 0x011f }
            com.google.android.apps.photosgo.editor.ExternalEditorActivity r9 = r2.f3766b     // Catch:{ all -> 0x011f }
            r9.setResult(r3)     // Catch:{ all -> 0x011f }
            com.google.android.apps.photosgo.editor.ExternalEditorActivity r9 = r2.f3766b     // Catch:{ all -> 0x011f }
            r9.finish()     // Catch:{ all -> 0x011f }
            goto L_0x00ea
        L_0x00a2:
            cnp r1 = r2.f3767c     // Catch:{ all -> 0x011f }
            bwg r4 = new bwg     // Catch:{ all -> 0x011f }
            r4.<init>(r9)     // Catch:{ all -> 0x011f }
            r1.mo3281a((p003j$.util.function.Supplier) r4)     // Catch:{ all -> 0x011f }
            goto L_0x00c8
        L_0x00ad:
            cnp r1 = r2.f3767c     // Catch:{ all -> 0x011f }
            bwf r4 = new bwf     // Catch:{ all -> 0x011f }
            r4.<init>(r9)     // Catch:{ all -> 0x011f }
            r1.mo3281a((p003j$.util.function.Supplier) r4)     // Catch:{ all -> 0x011f }
            goto L_0x00c8
        L_0x00b8:
            java.lang.String r9 = "ExternalEditorActivity: No uri in external editor intent, exiting the activity."
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch:{ all -> 0x011f }
            p000.cwn.m5514b(r9, r1)     // Catch:{ all -> 0x011f }
            r2.mo2833c()     // Catch:{ all -> 0x011f }
            goto L_0x00ea
        L_0x00c3:
            cnp r1 = r2.f3767c     // Catch:{ all -> 0x011f }
            r1.mo3280a((android.os.Bundle) r9)     // Catch:{ all -> 0x011f }
        L_0x00c8:
            fee r9 = r2.f3768d     // Catch:{ all -> 0x011f }
            feb r9 = r9.f9364c     // Catch:{ all -> 0x011f }
            r1 = 74304(0x12240, float:1.04122E-40)
            fea r9 = r9.mo5563a(r1)     // Catch:{ all -> 0x011f }
            fdp r1 = p000.fej.m8698a()     // Catch:{ all -> 0x011f }
            fdj r9 = r9.mo5513a((p000.fdp) r1)     // Catch:{ all -> 0x011f }
            fea r9 = (p000.fea) r9     // Catch:{ all -> 0x011f }
            fdp r1 = p000.ffh.f9451a     // Catch:{ all -> 0x011f }
            fdj r9 = r9.mo5513a((p000.fdp) r1)     // Catch:{ all -> 0x011f }
            fea r9 = (p000.fea) r9     // Catch:{ all -> 0x011f }
            com.google.android.apps.photosgo.editor.ExternalEditorActivity r1 = r2.f3766b     // Catch:{ all -> 0x011f }
            r9.mo5561a((android.app.Activity) r1)     // Catch:{ all -> 0x011f }
        L_0x00ea:
            r9 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r9 = r8.findViewById(r9)     // Catch:{ all -> 0x011f }
            hos r1 = p000.hok.m11838a((android.content.Context) r8)     // Catch:{ all -> 0x011f }
            r1.f13171d = r9     // Catch:{ all -> 0x011f }
            bwh r9 = r8.f4817e     // Catch:{ all -> 0x011f }
            bwi r1 = new bwi     // Catch:{ all -> 0x011f }
            r1.<init>(r9)     // Catch:{ all -> 0x011f }
            java.lang.Class<bwp> r2 = p000.bwp.class
            p000.ihg.m13033a((android.app.Activity) r8, (java.lang.Class) r2, (p000.hol) r1)     // Catch:{ all -> 0x011f }
            bwj r1 = new bwj     // Catch:{ all -> 0x011f }
            r1.<init>(r9)     // Catch:{ all -> 0x011f }
            java.lang.Class<cce> r2 = p000.cce.class
            p000.ihg.m13033a((android.app.Activity) r8, (java.lang.Class) r2, (p000.hol) r1)     // Catch:{ all -> 0x011f }
            bwk r1 = new bwk     // Catch:{ all -> 0x011f }
            r1.<init>(r9)     // Catch:{ all -> 0x011f }
            java.lang.Class<blv> r9 = p000.blv.class
            p000.ihg.m13033a((android.app.Activity) r8, (java.lang.Class) r9, (p000.hol) r1)     // Catch:{ all -> 0x011f }
            r8.f4819g = r3     // Catch:{ all -> 0x011f }
            if (r0 == 0) goto L_0x011e
            r0.close()
        L_0x011e:
            return
        L_0x011f:
            r9 = move-exception
            if (r0 == 0) goto L_0x012a
            r0.close()     // Catch:{ all -> 0x0126 }
            goto L_0x012a
        L_0x0126:
            r0 = move-exception
            p000.ifn.m12935a((java.lang.Throwable) r9, (java.lang.Throwable) r0)
        L_0x012a:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.apps.photosgo.editor.ExternalEditorActivity.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: protected */
    public final void onDestroy() {
        hlq g = this.f4818f.mo7524g();
        try {
            super.onDestroy();
            this.f4823k = true;
            g.close();
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public final void onNewIntent(Intent intent) {
        hlq a = this.f4818f.mo7516a(intent);
        try {
            super.onNewIntent(intent);
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
        hlq n = this.f4818f.mo7531n();
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
        hlq d = this.f4818f.mo7521d();
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
        hlq o = this.f4818f.mo7532o();
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
        hlq c = this.f4818f.mo7520c();
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
        hlq p = this.f4818f.mo7533p();
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
        hlq b = this.f4818f.mo7519b();
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
        this.f4818f.mo7523f().close();
    }

    /* access modifiers changed from: protected */
    public final void onSaveInstanceState(Bundle bundle) {
        hlq q = this.f4818f.mo7534q();
        try {
            bwh p = m4755p();
            p.f3767c.mo3282b(bundle);
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
        hlq a = this.f4818f.mo7515a();
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
        hlq e = this.f4818f.mo7522e();
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
        hlq i = this.f4818f.mo7526i();
        try {
            boolean h = super.mo3319h();
            if (i != null) {
                i.close();
            }
            return h;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final void onWindowFocusChanged(boolean z) {
        bwh p = m4755p();
        super.onWindowFocusChanged(z);
        if (z) {
            p.f3767c.mo3284h();
        }
    }

    /* renamed from: n */
    public final /* bridge */ /* synthetic */ Object mo2635n() {
        bwh bwh = this.f4817e;
        if (bwh == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f4823k) {
            return bwh;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
