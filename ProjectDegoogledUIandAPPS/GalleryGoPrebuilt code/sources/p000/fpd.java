package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: fpd */
/* compiled from: PG */
final /* synthetic */ class fpd implements hqk {

    /* renamed from: a */
    private final List f10190a;

    /* renamed from: b */
    private final List f10191b;

    /* renamed from: c */
    private final iqk f10192c;

    public fpd(List list, List list2, iqk iqk) {
        this.f10190a = list;
        this.f10191b = list2;
        this.f10192c = iqk;
    }

    /* renamed from: a */
    public final Object mo2652a() {
        List list = this.f10190a;
        List list2 = this.f10191b;
        iqk iqk = this.f10192c;
        ArrayList arrayList = new ArrayList(list.size() + list2.size());
        arrayList.addAll(list);
        int size = list2.size();
        for (int i = 0; i < size; i++) {
            hqk d = ife.m12893d((Object) (foy) list2.get(i));
            d.getClass();
            arrayList.add(new fpe(d));
        }
        return new fpf(arrayList, iqk);
    }
}
