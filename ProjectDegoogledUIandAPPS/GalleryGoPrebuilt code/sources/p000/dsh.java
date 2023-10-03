package p000;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;

/* renamed from: dsh */
/* compiled from: PG */
public final class dsh extends dsk implements hbf, ioe, hbd {

    /* renamed from: Z */
    private boolean f7274Z;

    /* renamed from: b */
    private dsf f7275b;

    /* renamed from: c */
    private Context f7276c;

    /* renamed from: d */
    private final C0002ab f7277d = new C0002ab(this);

    @Deprecated
    public dsh() {
        new hkx(this);
        fxk.m9830b();
    }

    /* renamed from: ad */
    public final C0600w mo5ad() {
        return this.f7277d;
    }

    @Deprecated
    /* renamed from: P */
    public final Context mo2628P() {
        if (this.f7276c == null) {
            this.f7276c = new hcf((Context) this.f7278a);
        }
        return this.f7276c;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Q */
    public final /* bridge */ /* synthetic */ ftr mo4386Q() {
        return new hcl(this, false);
    }

    /* renamed from: k */
    public final Context mo2634k() {
        if (this.f7278a != null) {
            return mo2628P();
        }
        return null;
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
            if (!this.f7274Z) {
                super.mo1832a(context);
                if (this.f7275b == null) {
                    this.f7275b = ((dsi) mo2453b()).mo2449a();
                    this.f9583V.mo64a(new hbu(this.f7277d));
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
            mo7268c(bundle);
            dsf R = mo2635n();
            Activity activity = R.f7268a;
            boolean z = false;
            if (activity.getIntent().getBooleanExtra("com.google.android.apps.photos.api.secure_mode", false)) {
                if (dvg.m6744a((Context) activity)) {
                    z = true;
                }
            }
            R.f7270c = z;
            if (bundle != null && bundle.getBoolean("started_in_secure_mode") && !R.f7270c) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.LAUNCHER");
                intent.setPackage(R.f7268a.getPackageName());
                R.f7268a.startActivity(dvg.m6742a((Context) R.f7268a, intent));
                R.mo4385a();
            } else if (R.f7270c) {
                R.f7268a.getWindow().addFlags(524288);
                R.f7268a.getWindow().addFlags(8388608);
                R.f7269b = new dse(R);
                R.f7268a.registerReceiver(R.f7269b, new IntentFilter("android.intent.action.SCREEN_OFF"));
                R.f7268a.registerReceiver(R.f7269b, new IntentFilter("android.intent.action.USER_PRESENT"));
            } else if (dvg.m6744a((Context) R.f7268a)) {
                R.f7268a.getWindow().addFlags(4194304);
            }
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

    /* renamed from: x */
    public final void mo1836x() {
        hlq c = hnb.m11779c();
        try {
            mo7264X();
            dsf R = mo2635n();
            Object[] objArr = {Boolean.valueOf(R.f7270c), Boolean.valueOf(R.f7271d)};
            dse dse = R.f7269b;
            if (dse != null) {
                R.f7268a.unregisterReceiver(dse);
                R.f7269b = null;
            }
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
            mo7265Y();
            this.f7274Z = true;
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
            LayoutInflater from = LayoutInflater.from(new hcf(LayoutInflater.from(new fts(mo5627H(), (C0147fh) this, false))));
            if (c != null) {
                c.close();
            }
            return from;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    /* renamed from: e */
    public final void mo168e(Bundle bundle) {
        super.mo168e(bundle);
        bundle.putBoolean("started_in_secure_mode", mo2635n().f7270c);
    }

    /* renamed from: d */
    public final void mo210d() {
        hlq c = hnb.m11779c();
        try {
            mo7259S();
            dsf R = mo2635n();
            Object[] objArr = {Boolean.valueOf(R.f7270c), Boolean.valueOf(R.f7271d)};
            if (R.f7271d) {
                R.f7268a.finish();
            }
            R.f7272e = true;
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
            mo7262V();
            mo2635n().f7272e = false;
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
    /* renamed from: R */
    public final dsf mo2635n() {
        dsf dsf = this.f7275b;
        if (dsf == null) {
            throw new IllegalStateException("peer() called before initialized.");
        } else if (!this.f7274Z) {
            return dsf;
        } else {
            throw new IllegalStateException("peer() called after destroyed.");
        }
    }
}
