package p000;

import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: gtg */
/* compiled from: PG */
public final class gtg implements gtj {

    /* renamed from: a */
    private static final AtomicBoolean f12013a = new AtomicBoolean(false);

    /* renamed from: b */
    private final hcr f12014b;

    public gtg(hcr hcr) {
        this.f12014b = hcr;
    }

    /* renamed from: a */
    public final void mo7033a() {
        if (gte.m10775a() && !f12013a.getAndSet(true)) {
            hlj a = hnb.m11765a("AndroidLoggerConfig");
            try {
                hxq hxq = new hxq();
                hxq.f13592a = this.f12014b;
                if (hxr.f13593a.compareAndSet(false, true)) {
                    Object obj = hxq.f13592a;
                    if (obj == null) {
                        obj = new hxy();
                    }
                    if (hxx.f13600b.compareAndSet((Object) null, obj)) {
                        hxx.m12427b();
                        if (a != null) {
                            a.close();
                            return;
                        }
                        return;
                    }
                    throw new IllegalStateException("Logger backends can only be configured once.");
                }
                throw new IllegalStateException("Logger backend configuration may only occur once.");
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        } else {
            return;
        }
        throw th;
    }
}
