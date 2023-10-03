package p000;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: fyp */
/* compiled from: PG */
public final class fyp implements fxq {
    /* renamed from: b */
    public static final InputStream m9885b(fxp fxp) {
        InputStream a = fxp.f10676a.mo6321a(fxp.f10679d);
        ArrayList arrayList = new ArrayList();
        arrayList.add(a);
        if (!fxp.f10678c.isEmpty()) {
            arrayList.add(new fxm(fxp.f10678c, a));
        }
        List list = fxp.f10677b;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            InputStream inputStream = (InputStream) ife.m12907g((Iterable) arrayList);
            arrayList.add(((fyw) list.get(i)).mo6347c());
        }
        Collections.reverse(arrayList);
        for (fxl a2 : fxp.f10680e) {
            a2.mo6305a();
        }
        return (InputStream) arrayList.get(0);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo6315a(fxp fxp) {
        throw null;
    }
}
