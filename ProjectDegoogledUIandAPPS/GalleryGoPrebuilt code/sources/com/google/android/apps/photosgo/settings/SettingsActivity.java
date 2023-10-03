package com.google.android.apps.photosgo.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.p002v7.widget.Toolbar;
import android.view.MenuItem;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public final class SettingsActivity extends ecc implements hbf, hbe, hbs {

    /* renamed from: e */
    private ebk f4914e;

    /* renamed from: f */
    private final hkn f4915f = new hkn(this);

    /* renamed from: g */
    private boolean f4916g;

    /* renamed from: h */
    private Context f4917h;

    /* renamed from: i */
    private final long f4918i = SystemClock.elapsedRealtime();

    /* renamed from: j */
    private C0002ab f4919j;

    /* renamed from: k */
    private boolean f4920k;

    /* renamed from: l */
    public final long mo3320l() {
        return this.f4918i;
    }

    public final void applyOverrideConfiguration(Configuration configuration) {
        Context baseContext = getBaseContext();
        if (baseContext == null) {
            baseContext = this.f4917h;
        }
        super.applyOverrideConfiguration(hov.m11853a(baseContext, configuration));
    }

    /* access modifiers changed from: protected */
    public final void attachBaseContext(Context context) {
        this.f4917h = context;
        super.attachBaseContext(hov.m11852a(context));
        this.f4917h = null;
    }

    /* renamed from: m */
    public final /* bridge */ /* synthetic */ ftl mo2566m() {
        return hck.m11206a((Activity) this);
    }

    /* renamed from: o */
    private final void m4873o() {
        if (this.f4914e != null) {
            return;
        }
        if (!this.f4916g) {
            throw new IllegalStateException("createPeer() called outside of onCreate");
        } else if (this.f4920k && !isFinishing()) {
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
                    this.f4914e = ((ebl) mo2453b()).mo2362h();
                    if (a2 != null) {
                        a2.close();
                        return;
                    }
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
        if (this.f4919j == null) {
            this.f4919j = new hbt(this);
        }
        return this.f4919j;
    }

    /* renamed from: p */
    private final ebk m4874p() {
        m4873o();
        return this.f4914e;
    }

    /* access modifiers changed from: protected */
    public final void onActivityResult(int i, int i2, Intent intent) {
        hlq l = this.f4915f.mo7529l();
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
        hlq h = this.f4915f.mo7525h();
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
    public final void onCreate(Bundle bundle) {
        hlq m = this.f4915f.mo7530m();
        try {
            this.f4916g = true;
            m4873o();
            ((hbt) mo5ad()).mo7270a(this.f4915f);
            ((hco) mo2453b()).mo2369o().mo7280a();
            super.onCreate(bundle);
            ebk p = m4874p();
            p.f7851a.setContentView((int) R.layout.settings_activity);
            ((fea) ((fea) p.f7852b.f9364c.mo5563a(72468).mo5513a(fej.m8698a())).mo5513a(ffh.f9451a)).mo5561a((Activity) p.f7851a);
            SettingsActivity settingsActivity = p.f7851a;
            settingsActivity.mo9531a((Toolbar) settingsActivity.findViewById(R.id.settings_toolbar));
            C0383nz f = p.f7851a.mo9534f();
            if (f != null) {
                f.mo9488a(true);
            }
            if (bundle == null) {
                C0182gn a = p.f7851a.mo5851d().mo6419a();
                ebq ebq = new ebq();
                ftr.m9611b(ebq);
                ftr.m9610a((C0147fh) ebq);
                a.mo6849a((int) R.id.settings_content, (C0147fh) ebq).mo5244a();
            }
            this.f4916g = false;
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
        hlq g = this.f4915f.mo7524g();
        try {
            super.onDestroy();
            this.f4920k = true;
            g.close();
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public final void onNewIntent(Intent intent) {
        hlq a = this.f4915f.mo7516a(intent);
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
        boolean z;
        hlq n = this.f4915f.mo7531n();
        try {
            super.onOptionsItemSelected(menuItem);
            ebk p = m4874p();
            if (menuItem.getItemId() == 16908332) {
                p.f7851a.finish();
                z = true;
            } else {
                z = false;
            }
            if (n != null) {
                n.close();
            }
            return z;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public final void onPause() {
        hlq d = this.f4915f.mo7521d();
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
        hlq o = this.f4915f.mo7532o();
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
        hlq c = this.f4915f.mo7520c();
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
        hlq p = this.f4915f.mo7533p();
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
        hlq b = this.f4915f.mo7519b();
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
        this.f4915f.mo7523f().close();
    }

    /* access modifiers changed from: protected */
    public final void onSaveInstanceState(Bundle bundle) {
        hlq q = this.f4915f.mo7534q();
        try {
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
        hlq a = this.f4915f.mo7515a();
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
        hlq e = this.f4915f.mo7522e();
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
        hlq i = this.f4915f.mo7526i();
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

    /* renamed from: n */
    public final /* bridge */ /* synthetic */ Object mo2635n() {
        ebk ebk = this.f4914e;
        if (ebk == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f4920k) {
            return ebk;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
