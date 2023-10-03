package p000;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: imr */
/* compiled from: PG */
public final class imr {

    /* renamed from: a */
    private final String f14538a;

    /* renamed from: b */
    private final AtomicLong f14539b = new AtomicLong(0);

    /* renamed from: c */
    private final AtomicLong f14540c = new AtomicLong(0);

    public imr(String str) {
        this.f14538a = str;
    }

    /* renamed from: b */
    public final void mo9003b() {
        this.f14540c.incrementAndGet();
    }

    /* renamed from: a */
    public final long mo9002a() {
        return this.f14539b.getAndIncrement();
    }

    public final String toString() {
        String hexString = Integer.toHexString(System.identityHashCode(this));
        String str = this.f14538a;
        StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + 14 + str.length());
        sb.append("TaskGraph@");
        sb.append(hexString);
        sb.append("(\"");
        sb.append(str);
        sb.append("\")");
        return sb.toString();
    }
}
