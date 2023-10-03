package p000;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: fyq */
/* compiled from: PG */
public final class fyq implements fxq {
    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo6315a(fxp fxp) {
        OutputStream g = fxp.f10676a.mo6328g(fxp.f10679d);
        ArrayList arrayList = new ArrayList();
        arrayList.add(g);
        if (!fxp.f10678c.isEmpty()) {
            List list = fxp.f10678c;
            fxn fxn = new fxn(g);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                fyu b = ((fyv) list.get(i)).mo6344b();
                if (b != null) {
                    fxn.f10669a.add(b);
                }
            }
            arrayList.add(fxn);
        }
        List list2 = fxp.f10677b;
        int size2 = list2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            OutputStream outputStream = (OutputStream) ife.m12907g((Iterable) arrayList);
            arrayList.add(((fyw) list2.get(i2)).mo6348d());
        }
        Collections.reverse(arrayList);
        for (fxl a : fxp.f10680e) {
            a.mo6306a(arrayList);
        }
        return (OutputStream) arrayList.get(0);
    }
}
