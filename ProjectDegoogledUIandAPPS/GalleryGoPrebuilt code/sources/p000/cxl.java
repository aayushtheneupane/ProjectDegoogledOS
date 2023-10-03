package p000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import p003j$.util.Comparator$$CC;

/* renamed from: cxl */
/* compiled from: PG */
final /* synthetic */ class cxl implements hpr {

    /* renamed from: a */
    public static final hpr f5936a = new cxl();

    private cxl() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        List<cxi> list;
        List<cxi> list2 = (List) obj;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        for (cxi cxi : list2) {
            if ((131072 & cxi.f5909a) != 0) {
                bmf a = bmf.m3230a(cxi);
                List list3 = (List) hashMap.get(a);
                if (list3 == null) {
                    list3 = new ArrayList();
                    hashMap.put(a, list3);
                }
                list3.add(cxi);
                cxi cxi2 = (cxi) hashMap2.get(a);
                if (cxi2 == null || flw.m9197b(cxi2, cxi) < 0) {
                    hashMap2.put(a, cxi);
                }
            }
        }
        if (hashMap.isEmpty()) {
            return list2;
        }
        ArrayList arrayList = new ArrayList();
        for (cxi cxi3 : list2) {
            if ((cxi3.f5909a & 131072) == 0) {
                arrayList.add(cxi3);
            } else {
                bmf a2 = bmf.m3230a(cxi3);
                if (((cxi) ife.m12898e((Object) (cxi) hashMap2.get(a2))).f5911c == cxi3.f5911c && (list = (List) hashMap.get(a2)) != null) {
                    long j = 0;
                    for (cxi cxi4 : list) {
                        j += cxi4.f5920l;
                    }
                    Collections.sort(list, Comparator$$CC.comparing$$STATIC$$(cxm.f5937a));
                    iir iir = (iir) cxi3.mo8790b(5);
                    iir.mo8503a((iix) cxi3);
                    iir.mo8749a((Iterable) list);
                    if (iir.f14319c) {
                        iir.mo8751b();
                        iir.f14319c = false;
                    }
                    cxi cxi5 = (cxi) iir.f14318b;
                    cxi5.f5909a |= 1024;
                    cxi5.f5920l = j;
                    arrayList.add((cxi) iir.mo8770g());
                }
            }
        }
        return arrayList;
    }
}
