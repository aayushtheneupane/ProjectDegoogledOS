package p000;

/* renamed from: deg */
/* compiled from: PG */
final class deg extends deh {
    public deg() {
        super(cxh.VIDEO);
    }

    /* renamed from: a */
    public final deh mo4084a(long j) {
        this.f6385a.put("datetaken", Long.valueOf(j));
        return this;
    }

    /* renamed from: a */
    public final void mo4086a(int i, int i2) {
        this.f6385a.put("resolution", String.format("%dx%d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        super.mo4086a(i, i2);
    }

    /* renamed from: b */
    public final void mo4087b(long j) {
        this.f6385a.put("duration", Long.valueOf(j));
    }

    /* renamed from: a */
    public final void mo4085a(double d, double d2) {
        this.f6385a.put("latitude", Double.valueOf(d));
        this.f6385a.put("longitude", Double.valueOf(d2));
    }
}
