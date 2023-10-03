package p000;

import android.content.ContentValues;

/* renamed from: bpf */
/* compiled from: PG */
final /* synthetic */ class bpf implements icf {

    /* renamed from: a */
    private final bph f3293a;

    /* renamed from: b */
    private final cxi f3294b;

    public bpf(bph bph, cxi cxi) {
        this.f3293a = bph;
        this.f3294b = cxi;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        bph bph = this.f3293a;
        cxi cxi = this.f3294b;
        long j = cxi.f5920l - ((cxi) obj).f5920l;
        bow bow = bph.f3296a;
        long j2 = cxi.f5916h;
        ife.m12845a(true, (Object) "CompressionDao: Quality factor should be in [0, 100]");
        if (j <= 0) {
            return ife.m12822a((Throwable) new IllegalArgumentException("CompressionDao: size reduced should be greater than 0"));
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("ao", 80);
        contentValues.put("aq", Long.valueOf(j));
        ieh a = bow.f3281a.mo2656a(new bov(contentValues, j2));
        cwn.m5509a(a, "CompressionDao: Error when marking media as compressed", new Object[0]);
        return a;
    }
}
