package p000;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: fcl */
/* compiled from: PG */
public final class fcl {

    /* renamed from: a */
    private static final AtomicLong f9266a = new AtomicLong();

    /* renamed from: b */
    private static final AtomicInteger f9267b = new AtomicInteger();

    /* renamed from: a */
    public static ial m8575a() {
        long j;
        long max;
        iak iak = (iak) ial.f13725d.mo8793g();
        long andIncrement = (long) f9267b.getAndIncrement();
        if (iak.f14319c) {
            iak.mo8751b();
            iak.f14319c = false;
        }
        ial ial = (ial) iak.f14318b;
        ial.f13727a |= 2;
        ial.f13729c = andIncrement;
        iam iam = (iam) ian.f13731e.mo8793g();
        if (iam.f14319c) {
            iam.mo8751b();
            iam.f14319c = false;
        }
        ian.m12584a((ian) iam.f14318b);
        if (iam.f14319c) {
            iam.mo8751b();
            iam.f14319c = false;
        }
        ian.m12585b((ian) iam.f14318b);
        long currentTimeMillis = System.currentTimeMillis() * 1000;
        do {
            j = f9266a.get();
            max = Math.max(1 + j, currentTimeMillis);
        } while (!f9266a.compareAndSet(j, max));
        if (iam.f14319c) {
            iam.mo8751b();
            iam.f14319c = false;
        }
        ian ian = (ian) iam.f14318b;
        ian.f13733a |= 1;
        ian.f13734b = max;
        ian ian2 = (ian) iam.mo8770g();
        if (iak.f14319c) {
            iak.mo8751b();
            iak.f14319c = false;
        }
        ial ial2 = (ial) iak.f14318b;
        ian2.getClass();
        ial2.f13728b = ian2;
        ial2.f13727a |= 1;
        return (ial) iak.mo8770g();
    }
}
