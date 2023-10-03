package p000;

import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: hky */
/* compiled from: PG */
public final class hky {

    /* renamed from: a */
    public final UUID f12950a;

    /* renamed from: b */
    private final AtomicLong f12951b;

    public hky() {
        UUID randomUUID = UUID.randomUUID();
        long nextLong = new SecureRandom().nextLong();
        this.f12950a = randomUUID;
        this.f12951b = new AtomicLong((nextLong ^ 25214903917L) & 281474976710655L);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final long mo7540a() {
        long j;
        long j2;
        long j3;
        do {
            j = this.f12951b.get();
            long j4 = ((j * 25214903917L) + 11) & 281474976710655L;
            j2 = ((25214903917L * j4) + 11) & 281474976710655L;
            j3 = (((long) ((int) (j4 >>> 16))) << 32) + ((long) ((int) (j2 >>> 16)));
        } while (!this.f12951b.compareAndSet(j, j2));
        return j3;
    }
}
