package com.google.android.apps.photosgo.picker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.ActionMode;
import android.view.MenuItem;
import com.google.android.apps.photosgo.R;
import p003j$.util.function.Supplier;

/* compiled from: PG */
public final class ExternalPickerActivity extends eag implements hbf, hbe, hbs {

    /* renamed from: e */
    private dzy f4907e;

    /* renamed from: f */
    private final hkn f4908f = new hkn(this);

    /* renamed from: g */
    private boolean f4909g;

    /* renamed from: h */
    private Context f4910h;

    /* renamed from: i */
    private final long f4911i = SystemClock.elapsedRealtime();

    /* renamed from: j */
    private C0002ab f4912j;

    /* renamed from: k */
    private boolean f4913k;

    /* renamed from: l */
    public final long mo3320l() {
        return this.f4911i;
    }

    public final void applyOverrideConfiguration(Configuration configuration) {
        Context baseContext = getBaseContext();
        if (baseContext == null) {
            baseContext = this.f4910h;
        }
        super.applyOverrideConfiguration(hov.m11853a(baseContext, configuration));
    }

    /* access modifiers changed from: protected */
    public final void attachBaseContext(Context context) {
        this.f4910h = context;
        super.attachBaseContext(hov.m11852a(context));
        this.f4910h = null;
    }

    /* renamed from: m */
    public final /* bridge */ /* synthetic */ ftl mo2566m() {
        return hck.m11206a((Activity) this);
    }

    /* renamed from: o */
    private final void m4864o() {
        if (this.f4907e != null) {
            return;
        }
        if (!this.f4909g) {
            throw new IllegalStateException("createPeer() called outside of onCreate");
        } else if (this.f4913k && !isFinishing()) {
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
                    this.f4907e = ((ead) mo2453b()).mo2361g();
                    if (a2 != null) {
                        a2.close();
                    }
                    this.f4907e.f7762e = this;
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
        if (this.f4912j == null) {
            this.f4912j = new hbt(this);
        }
        return this.f4912j;
    }

    /* renamed from: p */
    private final dzy m4865p() {
        m4864o();
        return this.f4907e;
    }

    public final void onActionModeFinished(ActionMode actionMode) {
        super.onActionModeFinished(actionMode);
        cns.m4636a(m4865p().f7754c.mo3283g());
    }

    public final void onActionModeStarted(ActionMode actionMode) {
        super.onActionModeStarted(actionMode);
        cns.m4638b(m4865p().f7754c.mo3283g());
    }

    /* access modifiers changed from: protected */
    public final void onActivityResult(int i, int i2, Intent intent) {
        hlq l = this.f4908f.mo7529l();
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
        hlq h = this.f4908f.mo7525h();
        try {
            m4865p().mo4621c();
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
    public final void onCreate(Bundle bundle) {
        int i;
        hlq m = this.f4908f.mo7530m();
        try {
            this.f4909g = true;
            m4864o();
            ((hbt) mo5ad()).mo7270a(this.f4908f);
            ((hco) mo2453b()).mo2369o().mo7280a();
            super.onCreate(bundle);
            dzy p = m4865p();
            if (bundle == null) {
                Intent intent = p.f7753b.getIntent();
                boolean booleanExtra = intent.getBooleanExtra("android.intent.extra.ALLOW_MULTIPLE", false);
                ExternalPickerActivity externalPickerActivity = p.f7753b;
                if (!booleanExtra) {
                    i = R.string.external_picker_single_title;
                } else {
                    i = R.string.select_photos;
                }
                externalPickerActivity.setTitle(i);
                p.f7754c.mo3281a((Supplier) new dzx(intent));
            } else {
                p.f7754c.mo3280a(bundle);
            }
            ((fea) ((fea) p.f7755d.f9364c.mo5563a(74303).mo5513a(fej.m8698a())).mo5513a(ffh.f9451a)).mo5561a((Activity) p.f7753b);
            hok.m11838a((Context) this).f13171d = findViewById(16908290);
            dzy dzy = this.f4907e;
            ihg.m13033a((Activity) this, blv.class, (hol) new dzz(dzy));
            ihg.m13033a((Activity) this, dxp.class, (hol) new eaa(dzy));
            ihg.m13033a((Activity) this, dwd.class, (hol) new eab(dzy));
            this.f4909g = false;
            if (m != null) {
                m.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public final void onDestroy() {
        hlq g = this.f4908f.mo7524g();
        try {
            super.onDestroy();
            this.f4913k = true;
            g.close();
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final boolean onNavigateUp() {
        super.onNavigateUp();
        return m4865p().f7754c.mo3274e();
    }

    /* access modifiers changed from: protected */
    public final void onNewIntent(Intent intent) {
        hlq a = this.f4908f.mo7516a(intent);
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
        hlq n = this.f4908f.mo7531n();
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
        hlq d = this.f4908f.mo7521d();
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
        hlq o = this.f4908f.mo7532o();
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
        hlq c = this.f4908f.mo7520c();
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
        hlq p = this.f4908f.mo7533p();
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
        hlq b = this.f4908f.mo7519b();
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
        this.f4908f.mo7523f().close();
    }

    /* access modifiers changed from: protected */
    public final void onSaveInstanceState(Bundle bundle) {
        hlq q = this.f4908f.mo7534q();
        try {
            super.onSaveInstanceState(bundle);
            m4865p().f7754c.mo3282b(bundle);
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
        hlq a = this.f4908f.mo7515a();
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
        hlq e = this.f4908f.mo7522e();
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
        hlq i = this.f4908f.mo7526i();
        try {
            super.mo3319h();
            boolean e = m4865p().f7754c.mo3274e();
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
        dzy p = m4865p();
        super.onWindowFocusChanged(z);
        if (z) {
            p.f7754c.mo3284h();
        }
    }

    /* renamed from: n */
    public final /* bridge */ /* synthetic */ Object mo2635n() {
        dzy dzy = this.f4907e;
        if (dzy == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f4913k) {
            return dzy;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
