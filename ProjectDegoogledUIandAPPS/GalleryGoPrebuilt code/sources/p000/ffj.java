package p000;

import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: ffj */
/* compiled from: PG */
final /* synthetic */ class ffj implements C0293kq {

    /* renamed from: a */
    private final ffl f9456a;

    /* renamed from: b */
    private final List f9457b;

    /* renamed from: c */
    private final String f9458c;

    /* renamed from: d */
    private final fcz f9459d;

    /* renamed from: e */
    private final ieh f9460e;

    public ffj(ffl ffl, List list, String str, fcz fcz, ieh ieh) {
        this.f9456a = ffl;
        this.f9457b = list;
        this.f9458c = str;
        this.f9459d = fcz;
        this.f9460e = ieh;
    }

    /* renamed from: a */
    public final void mo5576a(Object obj) {
        ffl ffl = this.f9456a;
        List list = this.f9457b;
        String str = this.f9458c;
        fcz fcz = this.f9459d;
        ieh ieh = this.f9460e;
        ieh a = ife.m12819a((Iterable) (List) obj);
        list.add(ife.m12884c(ieh, a).mo8442a((ice) new ffk(ffl, ieh, a, str, fcz), (Executor) ffl.f9466a));
    }
}
