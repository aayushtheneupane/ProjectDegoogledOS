package p000;

/* renamed from: dyf */
/* compiled from: PG */
final class dyf extends dww {
    /* renamed from: a */
    public final int mo4512a(cxi cxi, dvs dvs) {
        return 3;
    }

    /* renamed from: a */
    public final int mo4511a(cxi cxi, cxi cxi2) {
        long j = cxi.f5931w;
        long j2 = cxi2.f5931w;
        if (j < j2) {
            return 4;
        }
        if (j > j2) {
            return 1;
        }
        long j3 = cxi.f5916h;
        long j4 = cxi2.f5916h;
        if (j3 < j4) {
            return 4;
        }
        if (j3 <= j4) {
            return cxi.equals(cxi2) ? 2 : 3;
        }
        return 1;
    }
}
