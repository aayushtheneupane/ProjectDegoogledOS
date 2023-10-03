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
import p003j$.util.function.Supplier;

/* compiled from: PG */
public final class CropAndSetWallpaperActivity extends eif implements hbf, hbe, hbs {

    /* renamed from: e */
    private ehq f4925e;

    /* renamed from: f */
    private final hkn f4926f = new hkn(this);

    /* renamed from: g */
    private boolean f4927g;

    /* renamed from: h */
    private Context f4928h;

    /* renamed from: i */
    private final long f4929i = SystemClock.elapsedRealtime();

    /* renamed from: j */
    private C0002ab f4930j;

    /* renamed from: k */
    private boolean f4931k;

    /* renamed from: l */
    public final long mo3320l() {
        return this.f4929i;
    }

    public final void applyOverrideConfiguration(Configuration configuration) {
        Context baseContext = getBaseContext();
        if (baseContext == null) {
            baseContext = this.f4928h;
        }
        super.applyOverrideConfiguration(hov.m11853a(baseContext, configuration));
    }

    /* access modifiers changed from: protected */
    public final void attachBaseContext(Context context) {
        this.f4928h = context;
        super.attachBaseContext(hov.m11852a(context));
        this.f4928h = null;
    }

    /* renamed from: m */
    public final /* bridge */ /* synthetic */ ftl mo2566m() {
        return hck.m11206a((Activity) this);
    }

    /* renamed from: o */
    private final void m4889o() {
        if (this.f4925e != null) {
            return;
        }
        if (!this.f4927g) {
            throw new IllegalStateException("createPeer() called outside of onCreate");
        } else if (this.f4931k && !isFinishing()) {
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
                    this.f4925e = ((ehr) mo2453b()).mo2363i();
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
        if (this.f4930j == null) {
            this.f4930j = new hbt(this);
        }
        return this.f4930j;
    }

    /* renamed from: p */
    private final ehq m4890p() {
        m4889o();
        return this.f4925e;
    }

    /* access modifiers changed from: protected */
    public final void onActivityResult(int i, int i2, Intent intent) {
        hlq l = this.f4926f.mo7529l();
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
        hlq h = this.f4926f.mo7525h();
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
        char c;
        hlq m = this.f4926f.mo7530m();
        try {
            this.f4927g = true;
            m4889o();
            ((hbt) mo5ad()).mo7270a(this.f4926f);
            ((hco) mo2453b()).mo2369o().mo7280a();
            super.onCreate(bundle);
            ehq p = m4890p();
            if (bundle == null) {
                Intent intent = p.f8304a.getIntent();
                String str = (String) ife.m12869b((Object) intent.getAction(), (Object) "Intent action cannot be null");
                if (str.hashCode() == -1401676846 && str.equals("android.service.wallpaper.CROP_AND_SET_WALLPAPER")) {
                    c = 0;
                } else {
                    c = 65535;
                }
                if (c != 0) {
                    String valueOf = String.valueOf(intent.getAction());
                    throw new IllegalArgumentException(valueOf.length() == 0 ? new String("Unsupported action: ") : "Unsupported action: ".concat(valueOf));
                }
                Uri data = intent.getData();
                if (data == null) {
                    p.f8304a.setResult(0);
                    p.f8304a.finish();
                } else {
                    Optional ofNullable = Optional.ofNullable(intent.getType());
                    iir g = ehu.f8311d.mo8793g();
                    String uri = data.toString();
                    if (g.f14319c) {
                        g.mo8751b();
                        g.f14319c = false;
                    }
                    ehu ehu = (ehu) g.f14318b;
                    uri.getClass();
                    ehu.f8313a = 1 | ehu.f8313a;
                    ehu.f8314b = uri;
                    if (ofNullable.isPresent()) {
                        String str2 = (String) ofNullable.get();
                        if (g.f14319c) {
                            g.mo8751b();
                            g.f14319c = false;
                        }
                        ehu ehu2 = (ehu) g.f14318b;
                        str2.getClass();
                        ehu2.f8313a |= 2;
                        ehu2.f8315c = str2;
                    }
                    p.f8305b.mo3281a((Supplier) new ehp(g));
                }
            } else {
                p.f8305b.mo3280a(bundle);
            }
            this.f4927g = false;
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
        hlq g = this.f4926f.mo7524g();
        try {
            super.onDestroy();
            this.f4931k = true;
            g.close();
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public final void onNewIntent(Intent intent) {
        hlq a = this.f4926f.mo7516a(intent);
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
        hlq n = this.f4926f.mo7531n();
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
        hlq d = this.f4926f.mo7521d();
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
        hlq o = this.f4926f.mo7532o();
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
        hlq c = this.f4926f.mo7520c();
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
        hlq p = this.f4926f.mo7533p();
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
        hlq b = this.f4926f.mo7519b();
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
        this.f4926f.mo7523f().close();
    }

    /* access modifiers changed from: protected */
    public final void onSaveInstanceState(Bundle bundle) {
        hlq q = this.f4926f.mo7534q();
        try {
            super.onSaveInstanceState(bundle);
            m4890p().f8305b.mo3282b(bundle);
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
        hlq a = this.f4926f.mo7515a();
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
        hlq e = this.f4926f.mo7522e();
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
        hlq i = this.f4926f.mo7526i();
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
        super.onWindowFocusChanged(z);
        ehq p = m4890p();
        if (z) {
            p.f8305b.mo3284h();
        }
    }

    /* renamed from: n */
    public final /* bridge */ /* synthetic */ Object mo2635n() {
        ehq ehq = this.f4925e;
        if (ehq == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f4931k) {
            return ehq;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
