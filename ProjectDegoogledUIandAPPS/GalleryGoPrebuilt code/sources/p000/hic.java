package p000;

import java.util.HashSet;
import java.util.List;

/* renamed from: hic */
/* compiled from: PG */
final /* synthetic */ class hic implements hpr {

    /* renamed from: a */
    public static final hpr f12782a = new hic();

    private hic() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        HashSet hashSet = new HashSet();
        for (gkx gkx : (List) obj) {
            if (!gkx.mo6817b().f11575h.equals("incognito")) {
                hashSet.add(gkx.mo6816a());
            }
        }
        return hashSet;
    }
}
