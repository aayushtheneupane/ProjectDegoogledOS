package com.google.android.apps.photosgo.oneup.secure.unlock;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;

/* compiled from: PG */
public final class UnlockActivity extends dsl implements hbf, hbe, hbs {

    /* renamed from: e */
    private dsp f4892e;

    /* renamed from: f */
    private final hkn f4893f = new hkn(this);

    /* renamed from: g */
    private boolean f4894g;

    /* renamed from: h */
    private Context f4895h;

    /* renamed from: i */
    private final long f4896i = SystemClock.elapsedRealtime();

    /* renamed from: j */
    private C0002ab f4897j;

    /* renamed from: k */
    private boolean f4898k;

    /* renamed from: l */
    public final long mo3320l() {
        return this.f4896i;
    }

    public final void applyOverrideConfiguration(Configuration configuration) {
        Context baseContext = getBaseContext();
        if (baseContext == null) {
            baseContext = this.f4895h;
        }
        super.applyOverrideConfiguration(hov.m11853a(baseContext, configuration));
    }

    /* access modifiers changed from: protected */
    public final void attachBaseContext(Context context) {
        this.f4895h = context;
        super.attachBaseContext(hov.m11852a(context));
        this.f4895h = null;
    }

    /* renamed from: m */
    public final /* bridge */ /* synthetic */ ftl mo2566m() {
        return hck.m11206a((Activity) this);
    }

    /* renamed from: o */
    private final void m4833o() {
        if (this.f4892e != null) {
            return;
        }
        if (!this.f4894g) {
            throw new IllegalStateException("createPeer() called outside of onCreate");
        } else if (this.f4898k && !isFinishing()) {
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
                    this.f4892e = ((dsq) mo2453b()).mo2360f();
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
        if (this.f4897j == null) {
            this.f4897j = new hbt(this);
        }
        return this.f4897j;
    }

    /* renamed from: p */
    private final dsp m4834p() {
        m4833o();
        return this.f4892e;
    }

    /* access modifiers changed from: protected */
    public final void onActivityResult(int i, int i2, Intent intent) {
        hlq l = this.f4893f.mo7529l();
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
        hlq h = this.f4893f.mo7525h();
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
        hlq m = this.f4893f.mo7530m();
        try {
            this.f4894g = true;
            m4833o();
            ((hbt) mo5ad()).mo7270a(this.f4893f);
            ((hco) mo2453b()).mo2369o().mo7280a();
            super.onCreate(bundle);
            dsp p = m4834p();
            p.f7289d.mo4387a((Intent) p.f7288c.getIntent().getParcelableExtra("target_intent"));
            p.f7288c.getWindow().addFlags(4194304);
            p.f7288c.registerReceiver(p.f7286a, new IntentFilter("android.intent.action.USER_PRESENT"));
            p.f7288c.registerReceiver(p.f7287b, new IntentFilter("android.intent.action.SCREEN_OFF"));
            this.f4894g = false;
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
        hlq g = this.f4893f.mo7524g();
        try {
            super.onDestroy();
            dsp p = m4834p();
            p.f7288c.unregisterReceiver(p.f7286a);
            p.f7288c.unregisterReceiver(p.f7287b);
            this.f4898k = true;
            g.close();
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public final void onNewIntent(Intent intent) {
        hlq a = this.f4893f.mo7516a(intent);
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
        hlq n = this.f4893f.mo7531n();
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
        hlq d = this.f4893f.mo7521d();
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
        hlq o = this.f4893f.mo7532o();
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
        hlq c = this.f4893f.mo7520c();
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
        hlq p = this.f4893f.mo7533p();
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
        hlq b = this.f4893f.mo7519b();
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
        this.f4893f.mo7523f().close();
    }

    /* access modifiers changed from: protected */
    public final void onSaveInstanceState(Bundle bundle) {
        hlq q = this.f4893f.mo7534q();
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
        hlq a = this.f4893f.mo7515a();
        try {
            super.onStart();
            dsp p = m4834p();
            if (!((KeyguardManager) p.f7288c.getSystemService("keyguard")).isKeyguardLocked()) {
                p.mo4390a();
            }
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
        hlq e = this.f4893f.mo7522e();
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
        hlq i = this.f4893f.mo7526i();
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
        dsp dsp = this.f4892e;
        if (dsp == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f4898k) {
            return dsp;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
