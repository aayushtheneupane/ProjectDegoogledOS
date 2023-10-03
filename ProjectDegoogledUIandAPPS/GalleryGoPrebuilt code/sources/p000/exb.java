package p000;

import java.util.concurrent.Executor;

/* renamed from: exb */
/* compiled from: PG */
public final class exb {

    /* renamed from: a */
    public final Object f9158a;

    /* renamed from: b */
    public final exd f9159b;

    /* renamed from: c */
    public boolean f9160c;

    /* renamed from: d */
    public volatile boolean f9161d;

    /* renamed from: e */
    public Object f9162e;

    /* renamed from: f */
    public Exception f9163f;

    public exb() {
    }

    public exb(byte[] bArr) {
        this();
        this.f9158a = new Object();
        this.f9159b = new exd();
    }

    /* renamed from: a */
    public void mo5357a(Executor executor, eww eww) {
        this.f9159b.mo5363a((exc) new ewv(executor, eww));
        mo5361c();
    }

    /* renamed from: a */
    public void mo5358a(Executor executor, ewz ewz) {
        this.f9159b.mo5363a((exc) new ewy(executor, ewz));
        mo5361c();
    }

    /* renamed from: b */
    public void mo5360b() {
        abj.m108a(!this.f9160c, (Object) "Task is already complete");
    }

    /* renamed from: c */
    public void mo5361c() {
        synchronized (this.f9158a) {
            if (this.f9160c) {
                this.f9159b.mo5362a(this);
            }
        }
    }

    /* renamed from: a */
    public boolean mo5359a() {
        boolean z;
        synchronized (this.f9158a) {
            z = false;
            if (this.f9160c && this.f9163f == null) {
                z = true;
            }
        }
        return z;
    }
}
