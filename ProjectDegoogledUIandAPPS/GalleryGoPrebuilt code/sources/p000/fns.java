package p000;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: fns */
/* compiled from: PG */
public abstract class fns {
    /* renamed from: a */
    public abstract ikf mo5984a(ikf ikf, ikf ikf2);

    /* renamed from: a */
    public abstract ikf mo5985a(String str, Object obj);

    /* renamed from: a */
    public abstract String mo5986a(ikf ikf);

    /* renamed from: a */
    public final List mo5988a(Map map) {
        ikf a;
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : map.entrySet()) {
            if (!(entry.getValue() == null || (a = mo5985a((String) entry.getKey(), entry.getValue())) == null)) {
                arrayList.add(a);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public final List mo5987a(List list, List list2) {
        ikf ikf;
        if (list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ikf ikf2 = (ikf) list.get(i);
            String a = mo5986a(ikf2);
            int size2 = list2.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size2) {
                    ikf = null;
                    break;
                }
                ikf = (ikf) list2.get(i2);
                i2++;
                if (a.equals(mo5986a(ikf))) {
                    break;
                }
            }
            ikf a2 = mo5984a(ikf2, ikf);
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }
}
