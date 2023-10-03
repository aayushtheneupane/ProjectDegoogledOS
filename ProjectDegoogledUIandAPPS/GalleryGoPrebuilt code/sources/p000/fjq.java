package p000;

import android.app.Application;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: fjq */
/* compiled from: PG */
final class fjq {

    /* renamed from: a */
    public final AtomicBoolean f9826a = new AtomicBoolean(false);

    /* renamed from: b */
    public final fjp f9827b;

    /* renamed from: c */
    public ScheduledFuture f9828c;

    /* renamed from: d */
    public ScheduledFuture f9829d;

    /* renamed from: e */
    public final hqk f9830e;

    /* renamed from: f */
    public final fid f9831f;

    /* renamed from: g */
    public final fhz f9832g = new fjm(this);

    /* renamed from: h */
    public final fia f9833h = new fjo(this);

    public fjq(fjp fjp, Application application, hqk hqk) {
        fid a = fid.m8938a(application);
        this.f9827b = fjp;
        this.f9830e = hqk;
        this.f9831f = a;
    }

    /* renamed from: a */
    public final void mo5883a() {
        ScheduledFuture scheduledFuture = this.f9828c;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            this.f9828c = null;
        }
        ScheduledFuture scheduledFuture2 = this.f9829d;
        if (scheduledFuture2 != null) {
            scheduledFuture2.cancel(true);
            this.f9829d = null;
        }
    }
}
