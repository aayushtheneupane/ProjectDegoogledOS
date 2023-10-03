package com.google.android.apps.photosgo.assassin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import com.google.android.material.button.MaterialButton;

/* compiled from: PG */
public final class AssassinActivity extends bln implements hbf, hbe, hbs {

    /* renamed from: e */
    private blk f4804e;

    /* renamed from: f */
    private final hkn f4805f = new hkn(this);

    /* renamed from: g */
    private boolean f4806g;

    /* renamed from: h */
    private Context f4807h;

    /* renamed from: i */
    private final long f4808i = SystemClock.elapsedRealtime();

    /* renamed from: j */
    private C0002ab f4809j;

    /* renamed from: k */
    private boolean f4810k;

    /* renamed from: l */
    public final long mo3320l() {
        return this.f4808i;
    }

    public final void applyOverrideConfiguration(Configuration configuration) {
        Context baseContext = getBaseContext();
        if (baseContext == null) {
            baseContext = this.f4807h;
        }
        super.applyOverrideConfiguration(hov.m11853a(baseContext, configuration));
    }

    /* access modifiers changed from: protected */
    public final void attachBaseContext(Context context) {
        this.f4807h = context;
        super.attachBaseContext(hov.m11852a(context));
        this.f4807h = null;
    }

    /* renamed from: m */
    public final /* bridge */ /* synthetic */ ftl mo2566m() {
        return hck.m11206a((Activity) this);
    }

    /* renamed from: o */
    private final void m4729o() {
        if (this.f4804e != null) {
            return;
        }
        if (!this.f4806g) {
            throw new IllegalStateException("createPeer() called outside of onCreate");
        } else if (this.f4810k && !isFinishing()) {
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
                    this.f4804e = ((bll) mo2453b()).mo2356b();
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
        if (this.f4809j == null) {
            this.f4809j = new hbt(this);
        }
        return this.f4809j;
    }

    /* access modifiers changed from: protected */
    public final void onActivityResult(int i, int i2, Intent intent) {
        hlq l = this.f4805f.mo7529l();
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
        hlq h = this.f4805f.mo7525h();
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
        hlq m = this.f4805f.mo7530m();
        try {
            this.f4806g = true;
            m4729o();
            ((hbt) mo5ad()).mo7270a(this.f4805f);
            ((hco) mo2453b()).mo2369o().mo7280a();
            super.onCreate(bundle);
            m4729o();
            blk blk = this.f4804e;
            blk.f3107a.setContentView((int) R.layout.assassin_layout);
            MaterialButton materialButton = (MaterialButton) blk.f3107a.findViewById(R.id.assassin_button);
            String str = blk.f3109c;
            if (str.hashCode() == 711171229 && str.equals("force_update")) {
                c = 0;
            } else {
                c = 65535;
            }
            if (c != 0) {
                materialButton.setText(R.string.assassin_quit_button);
                materialButton.setOnClickListener(blk.f3108b.mo7575a((View.OnClickListener) new blj(blk), "Quit"));
            } else {
                materialButton.setText(R.string.assassin_update_button);
                materialButton.setOnClickListener(blk.f3108b.mo7575a((View.OnClickListener) new bli(blk), "Update"));
            }
            ((fea) ((fea) blk.f3110d.f9364c.mo5563a(74305).mo5513a(fej.m8698a())).mo5513a(ffh.f9451a)).mo5561a((Activity) blk.f3107a);
            String string = blk.f3107a.getString(R.string.app_name);
            ((TextView) blk.f3107a.findViewById(R.id.assassin_update_request)).setText(blk.f3107a.getString(R.string.assassin_update_request, new Object[]{string}));
            this.f4806g = false;
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
        hlq g = this.f4805f.mo7524g();
        try {
            super.onDestroy();
            this.f4810k = true;
            g.close();
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    public final void onNewIntent(Intent intent) {
        hlq a = this.f4805f.mo7516a(intent);
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
        hlq n = this.f4805f.mo7531n();
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
        hlq d = this.f4805f.mo7521d();
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
        hlq o = this.f4805f.mo7532o();
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
        hlq c = this.f4805f.mo7520c();
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
        hlq p = this.f4805f.mo7533p();
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
        hlq b = this.f4805f.mo7519b();
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
        this.f4805f.mo7523f().close();
    }

    /* access modifiers changed from: protected */
    public final void onSaveInstanceState(Bundle bundle) {
        hlq q = this.f4805f.mo7534q();
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
        hlq a = this.f4805f.mo7515a();
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
        hlq e = this.f4805f.mo7522e();
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
        hlq i = this.f4805f.mo7526i();
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
        blk blk = this.f4804e;
        if (blk == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f4810k) {
            return blk;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
