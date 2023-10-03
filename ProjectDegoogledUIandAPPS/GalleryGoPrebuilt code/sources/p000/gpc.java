package p000;

import java.io.Closeable;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: gpc */
/* compiled from: PG */
public final class gpc {

    /* renamed from: a */
    public final idb f11782a;

    private gpc(idb idb) {
        this.f11782a = idb;
    }

    /* renamed from: a */
    public static gpc m10575a(gpd gpd, Executor executor) {
        icv a = idb.m12696a((Iterable) hso.m12033a((Object) idb.m12699a(ife.m12820a((Object) null))));
        ics ics = new ics(a, new gov(gpd));
        hsa a2 = hsa.m11995a(a.f13893b);
        hpr hpr = icv.f13891c;
        Iterable a3 = a2.mo7868a();
        ife.m12898e((Object) a3);
        ife.m12898e((Object) hpr);
        idb idb = new idb(ife.m12883c((Iterable) hso.m12032a(hsa.m11995a(new hts(a3, hpr)).mo7868a())).mo8442a((ice) ics, executor), (byte[]) null);
        idb.f13906c.mo8381a(a.f13892a, idh.f13918a);
        return m10576a(idb);
    }

    /* renamed from: b */
    public final ieh mo6899b() {
        return this.f11782a.mo8401b();
    }

    /* renamed from: a */
    public static gpc m10580a(Callable callable, Executor executor) {
        return new gpc(idb.m12697a((icq) new gou(callable), executor));
    }

    /* renamed from: b */
    public static gpc m10581b(ieh ieh) {
        return new gpc(idb.m12700a((ieh) ife.m12898e((Object) ieh), (Executor) idh.f13918a));
    }

    /* renamed from: a */
    public static gpc m10576a(idb idb) {
        return new gpc(idb);
    }

    /* renamed from: a */
    public static gpc m10577a(ieh ieh) {
        return new gpc(idb.m12699a((ieh) ife.m12898e((Object) ieh)));
    }

    /* renamed from: a */
    public static gpc m10578a(ieh ieh, Closeable... closeableArr) {
        ife.m12898e((Object) ieh);
        return m10576a(idb.m12697a((icq) new gox(closeableArr), (Executor) idh.f13918a).mo8396a((ico) new goy(ieh), (Executor) idh.f13918a));
    }

    /* renamed from: c */
    public final ieh mo6900c() {
        return this.f11782a.mo8398a();
    }

    /* renamed from: a */
    public static gpc m10579a(Object obj) {
        return m10577a(ife.m12820a(obj));
    }

    /* renamed from: b */
    public final gpc mo6897b(hpr hpr, Executor executor) {
        return new gpc(this.f11782a.mo8397a((icr) new goz(hmq.m11742a(hpr)), executor));
    }

    /* renamed from: a */
    public final ieh mo6894a() {
        return ibv.m12657a((ieh) this.f11782a.mo8401b(), ife.m12906g((Object) null), (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public final ieh mo6895a(hpr hpr, Executor executor) {
        return mo6897b(hpr, executor).mo6899b();
    }

    /* renamed from: b */
    public final gpc mo6898b(icf icf, Executor executor) {
        return new gpc(this.f11782a.mo8396a((ico) new gpa(hmq.m11744a(icf)), executor));
    }

    /* renamed from: a */
    public final ieh mo6896a(icf icf, Executor executor) {
        return mo6898b(icf, executor).mo6899b();
    }

    /* renamed from: a */
    public final gpc mo6893a(gpe gpe, Executor executor) {
        return m10576a(this.f11782a.mo8396a((ico) new gpb(new gow(hmu.m11754a(), gpe)), executor));
    }
}
