package p000;

import java.util.Iterator;
import java.util.logging.Logger;

/* renamed from: icv */
/* compiled from: PG */
public final class icv {

    /* renamed from: c */
    public static final hpr f13891c = new ict();

    /* renamed from: a */
    public final icp f13892a = new icp((byte[]) null);

    /* renamed from: b */
    public final hso f13893b;

    public /* synthetic */ icv(Iterable iterable) {
        this.f13893b = hso.m12032a(iterable);
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            icp icp = this.f13892a;
            Logger logger = idb.f13904a;
            ((idb) it.next()).mo8399a(icp);
        }
    }
}
