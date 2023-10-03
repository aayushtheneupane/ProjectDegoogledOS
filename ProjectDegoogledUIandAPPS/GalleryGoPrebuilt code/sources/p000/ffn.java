package p000;

import java.util.List;
import java.util.concurrent.Future;

/* renamed from: ffn */
/* compiled from: PG */
final /* synthetic */ class ffn implements ice {

    /* renamed from: a */
    private final ffo f9476a;

    /* renamed from: b */
    private final ieh f9477b;

    /* renamed from: c */
    private final ieh f9478c;

    /* renamed from: d */
    private final String f9479d;

    /* renamed from: e */
    private final fcz f9480e;

    public ffn(ffo ffo, ieh ieh, ieh ieh2, String str, fcz fcz) {
        this.f9476a = ffo;
        this.f9477b = ieh;
        this.f9478c = ieh2;
        this.f9479d = str;
        this.f9480e = fcz;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        ffo ffo = this.f9476a;
        ieh ieh = this.f9477b;
        ieh ieh2 = this.f9478c;
        String str = this.f9479d;
        fcz fcz = this.f9480e;
        ike m = ((ikf) ife.m12871b((Future) ieh)).mo8796m();
        for (ffr a : (List) ife.m12871b((Future) ieh2)) {
            a.mo3873a(m);
        }
        fdd fdd = ffo.f9482b;
        fdb f = fdc.m8599f();
        f.mo5507a(str);
        f.mo5506a(m.mo8770g());
        return fdd.mo5509a(f.mo5505a(), fcz.f9292b);
    }
}
