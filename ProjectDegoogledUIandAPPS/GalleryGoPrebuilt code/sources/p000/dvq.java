package p000;

/* renamed from: dvq */
/* compiled from: PG */
final class dvq extends dww {
    /* renamed from: a */
    private static long m6768a(cxi cxi) {
        ehf ehf = cxi.f5917i;
        if (ehf == null) {
            ehf = ehf.f8283d;
        }
        return ehf.f8286b + ehf.f8287c;
    }

    /* renamed from: a */
    public final int mo4511a(cxi cxi, cxi cxi2) {
        long a = m6768a(cxi);
        long a2 = m6768a(cxi2);
        if (a < a2) {
            return 4;
        }
        if (a > a2) {
            return 1;
        }
        long j = cxi.f5916h;
        long j2 = cxi2.f5916h;
        if (j < j2) {
            return 4;
        }
        if (j <= j2) {
            return cxi.equals(cxi2) ? 2 : 3;
        }
        return 1;
    }

    /* renamed from: a */
    public final int mo4512a(cxi cxi, dvs dvs) {
        return m6768a(cxi) > dvs.f7462a ? 1 : 4;
    }
}
