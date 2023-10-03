package p000;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: fcr */
/* compiled from: PG */
final /* synthetic */ class fcr implements ice {

    /* renamed from: a */
    private final fcu f9275a;

    /* renamed from: b */
    private final List f9276b;

    /* renamed from: c */
    private final iev f9277c;

    /* renamed from: d */
    private final fcy f9278d;

    public fcr(fcu fcu, List list, iev iev, fcy fcy) {
        this.f9275a = fcu;
        this.f9276b = list;
        this.f9277c = iev;
        this.f9278d = fcy;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        fcu fcu = this.f9275a;
        List<fcx> list = this.f9276b;
        iev iev = this.f9277c;
        fcy fcy = this.f9278d;
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        ArrayList arrayList2 = new ArrayList(size + size + 1);
        arrayList2.add(iev);
        for (fcx fcx : list) {
            try {
                ieh a = fcu.f9284c.mo5487a(fcx);
                fcz fcz = new fcz(fcx, a);
                arrayList.add(fcz);
                List<fcv> a2 = fcu.mo5489a((Class) fcz.f9291a.getClass());
                List<fcv> a3 = fcu.mo5489a(fcx.class);
                ArrayList arrayList3 = new ArrayList(a2.size() + a3.size());
                for (fcv a4 : a2) {
                    arrayList3.add(fcu.m8582a(fcz, a4));
                }
                for (fcv a5 : a3) {
                    arrayList3.add(fcu.m8582a(fcz, a5));
                }
                arrayList2.add(ibv.m12657a(ife.m12819a((Iterable) arrayList3), ife.m12906g((Object) null), (Executor) idh.f13918a));
                arrayList2.add(a);
            } catch (Throwable th) {
                arrayList2.add(ife.m12822a(th));
            }
        }
        ieh a6 = ife.m12883c((Iterable) arrayList2).mo8443a(ife.m12887c(), (Executor) idh.f13918a);
        hvr a7 = ((hvo) fcu.f9283b).iterator();
        while (a7.hasNext()) {
            ((fcw) a7.next()).mo5493a();
        }
        return ife.m12866b((Iterable) arrayList2).mo8442a((ice) new fcs(fcy, iev, a6), (Executor) fcu.f9282a);
    }
}
