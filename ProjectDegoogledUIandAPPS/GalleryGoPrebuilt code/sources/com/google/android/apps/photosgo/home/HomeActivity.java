package com.google.android.apps.photosgo.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.ActionMode;
import android.view.MenuItem;
import com.google.android.apps.photosgo.R;
import p003j$.util.Optional;

/* compiled from: PG */
public final class HomeActivity extends crm implements hbf, hbe, hbs {

    /* renamed from: e */
    private cqm f4857e;

    /* renamed from: f */
    private final hkn f4858f = new hkn(this);

    /* renamed from: g */
    private boolean f4859g;

    /* renamed from: h */
    private Context f4860h;

    /* renamed from: i */
    private final long f4861i = SystemClock.elapsedRealtime();

    /* renamed from: j */
    private C0002ab f4862j;

    /* renamed from: k */
    private boolean f4863k;

    /* renamed from: l */
    public final long mo3320l() {
        return this.f4861i;
    }

    public final void applyOverrideConfiguration(Configuration configuration) {
        Context baseContext = getBaseContext();
        if (baseContext == null) {
            baseContext = this.f4860h;
        }
        super.applyOverrideConfiguration(hov.m11853a(baseContext, configuration));
    }

    /* access modifiers changed from: protected */
    public final void attachBaseContext(Context context) {
        this.f4860h = context;
        super.attachBaseContext(hov.m11852a(context));
        this.f4860h = null;
    }

    /* renamed from: m */
    public final /* bridge */ /* synthetic */ ftl mo2566m() {
        return hck.m11206a((Activity) this);
    }

    /* renamed from: o */
    private final void m4790o() {
        if (this.f4857e != null) {
            return;
        }
        if (!this.f4859g) {
            throw new IllegalStateException("createPeer() called outside of onCreate");
        } else if (this.f4863k && !isFinishing()) {
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
                    this.f4857e = ((cqr) mo2453b()).mo2358d();
                    if (a2 != null) {
                        a2.close();
                    }
                    this.f4857e.f5438f = this;
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
        if (this.f4862j == null) {
            this.f4862j = new hbt(this);
        }
        return this.f4862j;
    }

    /* renamed from: p */
    private final cqm m4791p() {
        m4790o();
        return this.f4857e;
    }

    public final void onActionModeFinished(ActionMode actionMode) {
        super.onActionModeFinished(actionMode);
        cns.m4636a(m4791p().f5431b.mo3283g());
    }

    public final void onActionModeStarted(ActionMode actionMode) {
        super.onActionModeStarted(actionMode);
        cns.m4638b(m4791p().f5431b.mo3283g());
    }

    /* access modifiers changed from: protected */
    public final void onActivityResult(int i, int i2, Intent intent) {
        hlq l = this.f4858f.mo7529l();
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
        hlq h = this.f4858f.mo7525h();
        try {
            cqm p = m4791p();
            if (!p.f5431b.mo3274e()) {
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
    public final void onCreate(Bundle bundle) {
        hlq m = this.f4858f.mo7530m();
        try {
            this.f4859g = true;
            m4790o();
            ((hbt) mo5ad()).mo7270a(this.f4858f);
            ((hco) mo2453b()).mo2369o().mo7280a();
            cqm p = m4791p();
            p.f5430a.setTheme(R.style.AppTheme);
            super.onCreate(bundle);
            if (bundle == null) {
                p.f5431b.mo3281a(cqi.f5426a);
                if (p.f5433d.mo3175a()) {
                    if ("com.google.android.apps.photosgo.home.START_JOBS".equals(p.f5430a.getIntent().getAction()) && ((Optional) p.f5434e.mo9034a()).isPresent()) {
                        ((cvy) ((Optional) p.f5434e.mo9034a()).get()).mo3851a();
                    }
                }
            } else {
                p.f5431b.mo3280a(bundle);
            }
            ((fea) ((fea) p.f5432c.f9364c.mo5563a(74301).mo5513a(fej.m8698a())).mo5513a(ffh.f9451a)).mo5561a((Activity) p.f5430a);
            hok.m11838a((Context) this).f13171d = findViewById(16908290);
            cqm cqm = this.f4857e;
            ihg.m13033a((Activity) this, dxp.class, (hol) new cqn(cqm));
            ihg.m13033a((Activity) this, cjw.class, (hol) new cqo(cqm));
            ihg.m13033a((Activity) this, dxo.class, (hol) new cqp(cqm));
            this.f4859g = false;
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
        hlq g = this.f4858f.mo7524g();
        try {
            super.onDestroy();
            this.f4863k = true;
            g.close();
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final boolean onNavigateUp() {
        return m4791p().f5431b.mo3274e();
    }

    /* access modifiers changed from: protected */
    public final void onNewIntent(Intent intent) {
        hlq a = this.f4858f.mo7516a(intent);
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
        hlq n = this.f4858f.mo7531n();
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
        hlq d = this.f4858f.mo7521d();
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
        hlq o = this.f4858f.mo7532o();
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
        hlq c = this.f4858f.mo7520c();
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
        hlq p = this.f4858f.mo7533p();
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
        hlq b = this.f4858f.mo7519b();
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
        this.f4858f.mo7523f().close();
    }

    /* access modifiers changed from: protected */
    public final void onSaveInstanceState(Bundle bundle) {
        hlq q = this.f4858f.mo7534q();
        try {
            cqm p = m4791p();
            p.f5431b.mo3282b(bundle);
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
        hlq a = this.f4858f.mo7515a();
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
        hlq e = this.f4858f.mo7522e();
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
        hlq i = this.f4858f.mo7526i();
        try {
            boolean e = m4791p().f5431b.mo3274e();
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
        cqm p = m4791p();
        super.onWindowFocusChanged(z);
        if (z) {
            p.f5431b.mo3284h();
        }
    }

    /* renamed from: n */
    public final /* bridge */ /* synthetic */ Object mo2635n() {
        cqm cqm = this.f4857e;
        if (cqm == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f4863k) {
            return cqm;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
