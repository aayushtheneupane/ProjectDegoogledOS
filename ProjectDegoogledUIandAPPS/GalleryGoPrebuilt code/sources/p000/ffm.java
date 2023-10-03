package p000;

import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: ffm */
/* compiled from: PG */
final /* synthetic */ class ffm implements C0293kq {

    /* renamed from: a */
    private final ffo f9471a;

    /* renamed from: b */
    private final List f9472b;

    /* renamed from: c */
    private final String f9473c;

    /* renamed from: d */
    private final fcz f9474d;

    /* renamed from: e */
    private final ieh f9475e;

    public ffm(ffo ffo, List list, String str, fcz fcz, ieh ieh) {
        this.f9471a = ffo;
        this.f9472b = list;
        this.f9473c = str;
        this.f9474d = fcz;
        this.f9475e = ieh;
    }

    /* renamed from: a */
    public final void mo5576a(Object obj) {
        ffo ffo = this.f9471a;
        List list = this.f9472b;
        String str = this.f9473c;
        fcz fcz = this.f9474d;
        ieh ieh = this.f9475e;
        ieh a = ife.m12819a((Iterable) (List) obj);
        list.add(ife.m12884c(ieh, a).mo8442a((ice) new ffn(ffo, ieh, a, str, fcz), (Executor) ffo.f9481a));
    }
}
