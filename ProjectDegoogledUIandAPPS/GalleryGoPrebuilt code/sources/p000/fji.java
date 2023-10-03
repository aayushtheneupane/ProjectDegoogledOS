package p000;

import android.app.Application;
import android.content.SharedPreferences;

/* renamed from: fji */
/* compiled from: PG */
final class fji {

    /* renamed from: a */
    public volatile fjd f9805a;

    /* renamed from: b */
    public volatile fis f9806b;

    /* renamed from: c */
    public final hqk f9807c;

    /* renamed from: d */
    public final Application f9808d;

    /* renamed from: e */
    public final iqk f9809e;

    /* renamed from: f */
    public final hqk f9810f;

    /* renamed from: g */
    public final hqk f9811g;

    /* renamed from: h */
    public final fld f9812h;

    /* renamed from: i */
    public final hqk f9813i;

    /* renamed from: j */
    public final SharedPreferences f9814j;

    /* renamed from: k */
    public final fmw f9815k;

    /* renamed from: l */
    private volatile fiz f9816l;

    /* renamed from: m */
    private volatile fni f9817m;

    /* renamed from: n */
    private volatile fjt f9818n;

    public fji(Application application, hqk hqk, fld fld, hqk hqk2, SharedPreferences sharedPreferences, fmw fmw, hpy hpy) {
        this.f9808d = application;
        this.f9812h = fld;
        this.f9811g = hqk;
        this.f9809e = fld.mo5906a();
        this.f9813i = hqk2;
        this.f9814j = sharedPreferences;
        this.f9815k = fmw;
        this.f9810f = ife.m12811a((hqk) new fjh(application, fld));
        this.f9807c = ife.m12811a((hqk) new fjf(this, fld, application, hqk, hpy));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo5872a() {
        return this.f9812h.mo5910e().mo7646a() && ((flf) this.f9812h.mo5910e().mo7647b()).mo5764a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final fiz mo5873b() {
        boolean z;
        if (this.f9816l == null) {
            synchronized (fiz.class) {
                if (this.f9816l == null) {
                    flf flf = (flf) this.f9812h.mo5910e().mo7647b();
                    if (flf.mo5767d()) {
                        if (fom.m9325d(this.f9808d)) {
                            z = true;
                            iqk iqk = this.f9809e;
                            Application application = this.f9808d;
                            this.f9816l = (fiz) mo5871a(new fiz(iqk, flf.mo5768e(), flf.mo5766c(), this.f9810f, this.f9811g, application, flf.mo5765b(), z));
                        }
                    }
                    z = false;
                    iqk iqk2 = this.f9809e;
                    Application application2 = this.f9808d;
                    this.f9816l = (fiz) mo5871a(new fiz(iqk2, flf.mo5768e(), flf.mo5766c(), this.f9810f, this.f9811g, application2, flf.mo5765b(), z));
                }
            }
        }
        return this.f9816l;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final boolean mo5876e() {
        return this.f9812h.mo5908c().mo7646a() && ((fly) this.f9812h.mo5908c().mo7647b()).mo5792a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public final fjt mo5877f() {
        if (this.f9818n == null) {
            synchronized (fjt.class) {
                if (this.f9818n == null) {
                    iqk iqk = this.f9809e;
                    Application application = this.f9808d;
                    hqk hqk = this.f9810f;
                    hqk hqk2 = this.f9811g;
                    fly fly = (fly) this.f9812h.mo5908c().mo7647b();
                    this.f9818n = (fjt) mo5871a(new fjt(iqk, application, hqk, hqk2, fly.mo5793b(), fly.mo5794c(), (fjk) fly.mo5795d().mo7648c(), fly.mo5796e(), fly.mo5798f()));
                }
            }
        }
        return this.f9818n;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final fmx mo5871a(fmx fmx) {
        if (!this.f9815k.mo5978a(fmx)) {
            fmx.mo5727a();
        }
        return fmx;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final boolean mo5874c() {
        return this.f9812h.mo5913h().mo7646a() && ((fmq) this.f9812h.mo5913h().mo7647b()).mo5811a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public final boolean mo5878g() {
        return this.f9812h.mo5909d().mo7646a() && ((fms) this.f9812h.mo5909d().mo7647b()).mo5818a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final fni mo5875d() {
        fni fni;
        if (this.f9817m == null) {
            synchronized (fni.class) {
                if (this.f9817m == null) {
                    if (!mo5874c()) {
                        fni = fni.m9269b(this.f9809e, this.f9808d, this.f9810f, this.f9811g, this.f9812h.mo5914i());
                    } else {
                        fni = fni.m9268a(this.f9809e, this.f9808d, this.f9810f, this.f9811g, this.f9812h.mo5913h());
                    }
                    this.f9817m = (fni) mo5871a(fni);
                }
            }
        }
        return this.f9817m;
    }
}
