package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: gxe */
/* compiled from: PG */
final /* synthetic */ class gxe implements icf {

    /* renamed from: a */
    private final gxg f12243a;

    /* renamed from: b */
    private final String f12244b;

    public gxe(gxg gxg, String str) {
        this.f12243a = gxg;
        this.f12244b = str;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        gxg gxg = this.f12243a;
        String str = this.f12244b;
        List<gkx> list = (List) obj;
        ArrayList arrayList = new ArrayList(list.size());
        for (gkx gkx : list) {
            gkn a = gkx.mo6816a();
            gkx.mo6817b();
            arrayList.add(gxg.mo7175b(str, a));
        }
        return gxz.m11027a((List) arrayList);
    }
}
