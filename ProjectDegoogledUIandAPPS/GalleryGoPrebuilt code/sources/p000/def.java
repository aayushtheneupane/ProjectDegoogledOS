package p000;

import java.util.Arrays;

/* renamed from: def */
/* compiled from: PG */
final class def extends deh {
    static {
        Arrays.asList(new Integer[]{0, 90, 180, 270});
    }

    public def() {
        super(cxh.IMAGE);
    }

    /* renamed from: a */
    public final deh mo4084a(long j) {
        this.f6385a.put("datetaken", Long.valueOf(j));
        return this;
    }

    /* renamed from: a */
    public final void mo4085a(double d, double d2) {
        this.f6385a.put("latitude", Double.valueOf(d));
        this.f6385a.put("longitude", Double.valueOf(d2));
    }
}
