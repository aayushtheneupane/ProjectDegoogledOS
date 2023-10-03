package p000;

import com.google.android.gms.common.api.internal.LifecycleCallback;

/* renamed from: emh */
/* compiled from: PG */
public final class emh extends elu {

    /* renamed from: e */
    public final C0292kp f8554e = new C0292kp();

    /* renamed from: f */
    public enp f8555f;

    public emh(enw enw) {
        super(enw);
        this.f4997g.mo5069a("ConnectionlessLifecycleHelper", (LifecycleCallback) this);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo4985a(ejq ejq, int i) {
        this.f8555f.mo5063b(ejq, i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo4984a() {
        this.f8555f.mo5062b();
    }

    /* renamed from: d */
    public final void mo3518d() {
        mo5007f();
    }

    /* renamed from: c */
    public final void mo3517c() {
        this.f8526a = true;
        mo5007f();
    }

    /* renamed from: e */
    public final void mo3519e() {
        this.f8526a = false;
        enp enp = this.f8555f;
        synchronized (enp.f8673f) {
            if (enp.f8682k == this) {
                enp.f8682k = null;
                enp.f8683l.clear();
            }
        }
    }

    /* renamed from: f */
    public final void mo5007f() {
        if (!this.f8554e.isEmpty()) {
            this.f8555f.mo5060a(this);
        }
    }
}
