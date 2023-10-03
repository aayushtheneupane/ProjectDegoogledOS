package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* renamed from: gzf */
/* compiled from: PG */
final /* synthetic */ class gzf implements Callable {

    /* renamed from: a */
    private final ieh f12345a;

    /* renamed from: b */
    private final ieh f12346b;

    public gzf(ieh ieh, ieh ieh2) {
        this.f12345a = ieh;
        this.f12346b = ieh2;
    }

    public final Object call() {
        ieh ieh = this.f12345a;
        ieh ieh2 = this.f12346b;
        return Boolean.valueOf(((gyu) ife.m12871b((Future) ieh)).mo7195b(((gwz) ife.m12871b((Future) ieh2)).mo7153b(), ((gwz) ife.m12871b((Future) ieh2)).mo7152a()));
    }
}
