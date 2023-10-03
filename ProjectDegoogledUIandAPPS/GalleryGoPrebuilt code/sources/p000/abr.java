package p000;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* renamed from: abr */
/* compiled from: PG */
public final class abr {

    /* renamed from: a */
    public final C0306lc f99a = new C0307ld(10);

    /* renamed from: b */
    public final C0309lf f100b = new C0309lf();

    /* renamed from: c */
    public final ArrayList f101c = new ArrayList();

    /* renamed from: d */
    public final HashSet f102d = new HashSet();

    /* renamed from: a */
    public final void mo118a(Object obj) {
        if (!this.f100b.containsKey(obj)) {
            this.f100b.put(obj, (Object) null);
        }
    }

    /* renamed from: a */
    public final void mo119a(Object obj, ArrayList arrayList, HashSet hashSet) {
        if (arrayList.contains(obj)) {
            return;
        }
        if (!hashSet.contains(obj)) {
            hashSet.add(obj);
            ArrayList arrayList2 = (ArrayList) this.f100b.get(obj);
            if (arrayList2 != null) {
                int size = arrayList2.size();
                for (int i = 0; i < size; i++) {
                    mo119a(arrayList2.get(i), arrayList, hashSet);
                }
            }
            hashSet.remove(obj);
            arrayList.add(obj);
            return;
        }
        throw new RuntimeException("This graph contains cyclic dependencies");
    }

    /* renamed from: b */
    public final List mo120b(Object obj) {
        return (List) this.f100b.get(obj);
    }
}
