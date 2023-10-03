package p000;

import java.util.ArrayList;
import java.util.concurrent.Executor;

/* renamed from: djk */
/* compiled from: PG */
final /* synthetic */ class djk implements icf {

    /* renamed from: a */
    private final djn f6666a;

    public djk(djn djn) {
        this.f6666a = djn;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        djn djn = this.f6666a;
        hso hso = (hso) obj;
        if (hso.isEmpty()) {
            return ife.m12820a((Object) hvb.f13454a);
        }
        ArrayList arrayList = new ArrayList();
        int size = hso.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(djn.f6670b.mo4154a((String) hso.get(i)));
        }
        return gte.m10770a(ife.m12819a((Iterable) arrayList), djl.f6667a, (Executor) idh.f13918a);
    }
}
