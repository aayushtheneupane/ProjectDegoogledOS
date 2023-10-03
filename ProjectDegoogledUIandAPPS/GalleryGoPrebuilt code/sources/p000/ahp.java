package p000;

import android.os.Build;
import android.util.Log;
import java.util.concurrent.TimeUnit;

/* renamed from: ahp */
/* compiled from: PG */
public final class ahp extends ahr {
    public ahp(Class cls, long j, TimeUnit timeUnit) {
        super(cls);
        alg alg = this.f497b;
        long millis = timeUnit.toMillis(j);
        if (millis < 900000) {
            iol.m14231a();
            Log.w(alg.f712a, String.format("Interval duration lesser than minimum allowed value; Changed to %s", new Object[]{900000L}));
            millis = 900000;
        }
        alg.mo594a(millis, millis);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ ahr mo484a() {
        return this;
    }

    public ahp(Class cls, long j, TimeUnit timeUnit, long j2, TimeUnit timeUnit2) {
        super(cls);
        this.f497b.mo594a(timeUnit.toMillis(j), timeUnit2.toMillis(j2));
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ahs mo485b() {
        if (this.f497b.f727p) {
            int i = Build.VERSION.SDK_INT;
            if (this.f497b.f721j.f476c) {
                throw new IllegalArgumentException("Cannot run in foreground with an idle mode constraint");
            }
        }
        return new ahs(this);
    }
}
