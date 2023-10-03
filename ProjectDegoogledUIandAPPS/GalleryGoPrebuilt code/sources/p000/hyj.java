package p000;

import java.util.concurrent.TimeUnit;

/* renamed from: hyj */
/* compiled from: PG */
public final class hyj extends hyd {

    /* renamed from: a */
    public static final hyj f13618a = new hyj();

    public final String toString() {
        return "Default millisecond precision clock";
    }

    private hyj() {
    }

    /* renamed from: a */
    public final long mo8264a() {
        return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
    }
}
