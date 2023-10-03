package p000;

import java.util.List;

/* renamed from: dcz */
/* compiled from: PG */
public final class dcz {

    /* renamed from: a */
    private final iqk f6315a;

    /* renamed from: b */
    private final imp f6316b;

    public dcz(iqk iqk, imp imp) {
        this.f6315a = iqk;
        this.f6316b = imp;
    }

    /* renamed from: a */
    public final ieh mo4059a(List list, List list2) {
        if (list.isEmpty() && list2.isEmpty()) {
            return ife.m12820a((Object) hso.m12047f());
        }
        hlj a = hnb.m11765a("storage volumes to request");
        try {
            ieh a2 = a.mo7548a(((dda) this.f6315a.mo2097a()).mo2142a(this.f6316b.mo8999a("StorageVolumeChecker")).mo2145b(list).mo2143a(list2).mo2144a().mo2149d());
            if (a != null) {
                a.close();
            }
            return a2;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
