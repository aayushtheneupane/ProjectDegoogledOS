package p000;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: dib */
/* compiled from: PG */
final class dib implements dhv {

    /* renamed from: a */
    public static final String[] f6588a = {"media_store_id", "progress_status", "progress_percentage", "special_type_id"};

    /* renamed from: b */
    public final gtt f6589b;

    /* renamed from: c */
    public final iel f6590c;

    /* renamed from: d */
    private final dhc f6591d;

    public dib(gtt gtt, dhc dhc, iel iel) {
        this.f6589b = gtt;
        this.f6591d = dhc;
        this.f6590c = iel;
    }

    /* renamed from: a */
    public final ieh mo4148a() {
        dhc dhc = this.f6591d;
        new Object[1][0] = 3;
        ArrayList arrayList = new ArrayList();
        hvr a = dhc.f6535c.mo4133a().iterator();
        while (a.hasNext()) {
            String str = (String) a.next();
            new Object[1][0] = str;
            gtt gtt = dhc.f6534b;
            Class<Exception> cls = Exception.class;
            arrayList.add(gte.m10770a(gte.m10772a(gte.m10770a(gtt.f12045b.mo5933a((Callable) new gtq(gtt, dhc.m6104a(str), "version")), dgx.f6526a, (Executor) idh.f13918a), (Class) cls, dgy.f6527a, (Executor) idh.f13918a), (hpr) new dgz(str), (Executor) idh.f13918a));
        }
        return gte.m10771a(gte.m10770a(ife.m12819a((Iterable) arrayList), dha.f6531a, (Executor) idh.f13918a), (icf) new dhx(this), (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public final ieh mo4149a(long j) {
        return gte.m10770a(mo4148a(), (hpr) new dhw(j), (Executor) idh.f13918a);
    }
}
