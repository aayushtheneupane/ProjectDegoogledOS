package p000;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* renamed from: gln */
/* compiled from: PG */
final /* synthetic */ class gln implements Callable {

    /* renamed from: a */
    private final ieh f11579a;

    /* renamed from: b */
    private final ieh f11580b;

    public gln(ieh ieh, ieh ieh2) {
        this.f11579a = ieh;
        this.f11580b = ieh2;
    }

    public final Object call() {
        ieh ieh = this.f11579a;
        ieh ieh2 = this.f11580b;
        glz glz = (glz) ife.m12871b((Future) ieh);
        return (glz.f11604a & 1) != 0 ? guc.m10815a((List) ife.m12871b((Future) ieh2), glz.f11605b) : guc.f12067a;
    }
}
