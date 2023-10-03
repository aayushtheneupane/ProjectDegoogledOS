package com.google.android.apps.photosgo.wallpaper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import p003j$.util.Optional;

/* compiled from: PG */
public final class SetWallpaperActivity extends eih implements hbf, hbe, hbs {

    /* renamed from: e */
    private eib f4932e;

    /* renamed from: f */
    private final hkn f4933f = new hkn(this);

    /* renamed from: g */
    private boolean f4934g;

    /* renamed from: h */
    private Context f4935h;

    /* renamed from: i */
    private final long f4936i = SystemClock.elapsedRealtime();

    /* renamed from: j */
    private C0002ab f4937j;

    /* renamed from: k */
    private boolean f4938k;

    /* renamed from: l */
    public final long mo3320l() {
        return this.f4936i;
    }

    public final void applyOverrideConfiguration(Configuration configuration) {
        Context baseContext = getBaseContext();
        if (baseContext == null) {
            baseContext = this.f4935h;
        }
        super.applyOverrideConfiguration(hov.m11853a(baseContext, configuration));
    }

    /* access modifiers changed from: protected */
    public final void attachBaseContext(Context context) {
        this.f4935h = context;
        super.attachBaseContext(hov.m11852a(context));
        this.f4935h = null;
    }

    /* renamed from: m */
    public final /* bridge */ /* synthetic */ ftl mo2566m() {
        return hck.m11206a((Activity) this);
    }

    /* renamed from: o */
    private final void m4897o() {
        if (this.f4932e != null) {
            return;
        }
        if (!this.f4934g) {
            throw new IllegalStateException("createPeer() called outside of onCreate");
        } else if (this.f4938k && !isFinishing()) {
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
                    this.f4932e = ((eid) mo2453b()).mo2364j();
                    if (a2 != null) {
                        a2.close();
                    }
                    this.f4932e.f8333c = this;
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
        if (this.f4937j == null) {
            this.f4937j = new hbt(this);
        }
        return this.f4937j;
    }

    /* renamed from: p */
    private final eib m4898p() {
        m4897o();
        return this.f4932e;
    }

    /* access modifiers changed from: protected */
    public final void onActivityResult(int i, int i2, Intent intent) {
        hlq l = this.f4933f.mo7529l();
        try {
            super.onActivityResult(i, i2, intent);
            eib p = m4898p();
            if (i != 1) {
                if (i == 2) {
                    p.mo4819a(i2);
                }
            } else if (i2 == -1) {
                Uri data = intent.getData();
                ife.m12869b((Object) data, (Object) "Result of wallpaper picker cannot be null");
                p.mo4820a(p.f8332b.mo3173a(data), Optional.ofNullable(intent.getType()));
            } else {
                p.mo4819a(0);
            }
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
        hlq h = this.f4933f.mo7525h();
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
        hlq m = this.f4933f.mo7530m();
        try {
            this.f4934g = true;
            m4897o();
            ((hbt) mo5ad()).mo7270a(this.f4933f);
            ((hco) mo2453b()).mo2369o().mo7280a();
            super.onCreate(bundle);
            eib p = m4898p();
            if (bundle == null) {
                Intent intent = p.f8331a.getIntent();
                String str = (String) ife.m12869b((Object) intent.getAction(), (Object) "Intent action cannot be null");
                if (ife.m12854a((CharSequence) "android.intent.action.ATTACH_DATA", (CharSequence) str)) {
                    Uri data = intent.getData();
                    if (data != null) {
                        p.mo4820a(p.f8332b.mo3173a(data), Optional.ofNullable(intent.getType()));
                    } else {
                        p.f8331a.finish();
                    }
                } else if (ife.m12854a((CharSequence) "android.intent.action.SET_WALLPAPER", (CharSequence) str)) {
                    p.f8331a.startActivityForResult(new Intent().setAction("android.intent.action.GET_CONTENT").setType("image/*").setPackage(p.f8331a.getPackageName()), 1);
                } else {
                    throw new IllegalArgumentException(str.length() == 0 ? new String("Unsupported action: ") : "Unsupported action: ".concat(str));
                }
            }
            this.f4934g = false;
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
        hlq g = this.f4933f.mo7524g();
        try {
            super.onDestroy();
            this.f4938k = true;
            g.close();
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public final void onNewIntent(Intent intent) {
        hlq a = this.f4933f.mo7516a(intent);
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
        hlq n = this.f4933f.mo7531n();
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
        hlq d = this.f4933f.mo7521d();
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
        hlq o = this.f4933f.mo7532o();
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
        hlq c = this.f4933f.mo7520c();
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
        hlq p = this.f4933f.mo7533p();
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
        hlq b = this.f4933f.mo7519b();
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
        this.f4933f.mo7523f().close();
    }

    /* access modifiers changed from: protected */
    public final void onSaveInstanceState(Bundle bundle) {
        hlq q = this.f4933f.mo7534q();
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
        hlq a = this.f4933f.mo7515a();
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
        hlq e = this.f4933f.mo7522e();
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
        hlq i = this.f4933f.mo7526i();
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
        eib eib = this.f4932e;
        if (eib == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f4938k) {
            return eib;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
