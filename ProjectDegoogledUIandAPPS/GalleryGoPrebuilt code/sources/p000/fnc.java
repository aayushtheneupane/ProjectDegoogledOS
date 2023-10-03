package p000;

import android.os.SystemClock;

/* renamed from: fnc */
/* compiled from: PG */
public final class fnc {

    /* renamed from: a */
    public final long f10079a;

    /* renamed from: b */
    public long f10080b = -1;

    static {
        new fnc();
    }

    public fnc() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.f10079a = elapsedRealtime;
    }

    public fnc(long j, long j2) {
        if (j2 >= j) {
            this.f10079a = j;
            this.f10080b = j2;
            return;
        }
        throw new IllegalArgumentException(ife.m12834a("End time %s is before start time %s.", Long.valueOf(j2), Long.valueOf(j)));
    }
}
