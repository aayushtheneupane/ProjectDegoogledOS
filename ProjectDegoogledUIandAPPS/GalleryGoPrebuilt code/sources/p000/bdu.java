package p000;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: bdu */
/* compiled from: PG */
public final class bdu {

    /* renamed from: a */
    private final List f2102a = new ArrayList();

    /* renamed from: b */
    private final Map f2103b = new HashMap();

    /* renamed from: a */
    public final synchronized void mo1845a(String str, arb arb, Class cls, Class cls2) {
        m2209a(str).add(new bdt(cls, cls2, arb));
    }

    /* renamed from: a */
    public final synchronized List mo1844a(Class cls, Class cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        List list = this.f2102a;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            List<bdt> list2 = (List) this.f2103b.get((String) list.get(i));
            if (list2 != null) {
                for (bdt bdt : list2) {
                    if (bdt.mo1843a(cls, cls2)) {
                        arrayList.add(bdt.f2100b);
                    }
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private final synchronized List m2209a(String str) {
        List list;
        if (!this.f2102a.contains(str)) {
            this.f2102a.add(str);
        }
        list = (List) this.f2103b.get(str);
        if (list == null) {
            list = new ArrayList();
            this.f2103b.put(str, list);
        }
        return list;
    }

    /* renamed from: b */
    public final synchronized List mo1847b(Class cls, Class cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        List list = this.f2102a;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            List<bdt> list2 = (List) this.f2103b.get((String) list.get(i));
            if (list2 != null) {
                for (bdt bdt : list2) {
                    if (bdt.mo1843a(cls, cls2) && !arrayList.contains(bdt.f2099a)) {
                        arrayList.add(bdt.f2099a);
                    }
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public final synchronized void mo1846a(List list) {
        ArrayList arrayList = new ArrayList(this.f2102a);
        this.f2102a.clear();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            this.f2102a.add((String) list.get(i));
        }
        int size2 = arrayList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            String str = (String) arrayList.get(i2);
            if (!list.contains(str)) {
                this.f2102a.add(str);
            }
        }
    }
}
