package p000;

import java.util.Queue;

/* renamed from: axl */
/* compiled from: PG */
public final class axl {

    /* renamed from: a */
    private static final Queue f1826a = bfp.m2429a(0);

    /* renamed from: b */
    private Object f1827b;

    private axl() {
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof axl) || !this.f1827b.equals(((axl) obj).f1827b)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static axl m1853a(Object obj) {
        axl axl;
        synchronized (f1826a) {
            axl = (axl) f1826a.poll();
        }
        if (axl == null) {
            axl = new axl();
        }
        axl.f1827b = obj;
        return axl;
    }

    public final int hashCode() {
        return this.f1827b.hashCode();
    }

    /* renamed from: a */
    public final void mo1714a() {
        synchronized (f1826a) {
            f1826a.offer(this);
        }
    }
}
