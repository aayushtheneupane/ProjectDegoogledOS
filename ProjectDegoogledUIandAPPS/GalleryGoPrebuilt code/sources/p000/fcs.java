package p000;

import java.util.concurrent.Future;

/* renamed from: fcs */
/* compiled from: PG */
final /* synthetic */ class fcs implements ice {

    /* renamed from: a */
    private final fcy f9279a;

    /* renamed from: b */
    private final iev f9280b;

    /* renamed from: c */
    private final ieh f9281c;

    public fcs(fcy fcy, iev iev, ieh ieh) {
        this.f9279a = fcy;
        this.f9280b = iev;
        this.f9281c = ieh;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        boolean z;
        fcy fcy = this.f9279a;
        iev iev = this.f9280b;
        ieh ieh = this.f9281c;
        long longValue = ((Long) ife.m12871b((Future) iev)).longValue();
        if (fcy.f9289a == -1) {
            z = true;
        } else {
            z = false;
        }
        ife.m12876b(z, (Object) "Duration set more than once");
        fcy.f9289a = longValue;
        return ieh;
    }
}
