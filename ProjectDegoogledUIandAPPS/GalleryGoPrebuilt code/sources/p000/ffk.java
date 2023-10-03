package p000;

import java.util.List;
import java.util.concurrent.Future;

/* renamed from: ffk */
/* compiled from: PG */
final /* synthetic */ class ffk implements ice {

    /* renamed from: a */
    private final ffl f9461a;

    /* renamed from: b */
    private final ieh f9462b;

    /* renamed from: c */
    private final ieh f9463c;

    /* renamed from: d */
    private final String f9464d;

    /* renamed from: e */
    private final fcz f9465e;

    public ffk(ffl ffl, ieh ieh, ieh ieh2, String str, fcz fcz) {
        this.f9461a = ffl;
        this.f9462b = ieh;
        this.f9463c = ieh2;
        this.f9464d = str;
        this.f9465e = fcz;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        ffl ffl = this.f9461a;
        ieh ieh = this.f9462b;
        ieh ieh2 = this.f9463c;
        String str = this.f9464d;
        fcz fcz = this.f9465e;
        ike m = ((ikf) ife.m12871b((Future) ieh)).mo8796m();
        for (ffr a : (List) ife.m12871b((Future) ieh2)) {
            a.mo3873a(m);
        }
        fdd fdd = ffl.f9467b;
        fdb f = fdc.m8599f();
        f.mo5507a(str);
        f.mo5506a(m.mo8770g());
        return fdd.mo5509a(f.mo5505a(), fcz.f9292b);
    }
}
