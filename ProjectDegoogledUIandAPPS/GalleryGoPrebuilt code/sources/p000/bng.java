package p000;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import p003j$.util.Optional;

/* renamed from: bng */
/* compiled from: PG */
final /* synthetic */ class bng implements Callable {

    /* renamed from: a */
    private final List f3190a;

    /* renamed from: b */
    private final ieh f3191b;

    /* renamed from: c */
    private final Optional f3192c;

    /* renamed from: d */
    private final ieh f3193d;

    public bng(List list, ieh ieh, Optional optional, ieh ieh2) {
        this.f3190a = list;
        this.f3191b = ieh;
        this.f3192c = optional;
        this.f3193d = ieh2;
    }

    public final Object call() {
        List list = this.f3190a;
        ieh ieh = this.f3191b;
        Optional optional = this.f3192c;
        ieh ieh2 = this.f3193d;
        bnj bnj = new bnj(list);
        ((Optional) ife.m12871b((Future) ieh)).ifPresent(new bnh(bnj, optional));
        bnj.putAll((Map) ife.m12871b((Future) ieh2));
        ArrayList arrayList = new ArrayList(bnj.keySet());
        bnj bnj2 = new bnj(arrayList);
        int size = bnj.size();
        if (size != 0) {
            List list2 = (List) bnj.get(arrayList.get(0));
            list2.getClass();
            bnj2.put((cxd) arrayList.get(0), (String) list2.get(0));
            for (int i = 1; i < size; i++) {
                List list3 = (List) bnj.get(arrayList.get(i));
                list3.getClass();
                ArrayList arrayList2 = new ArrayList(list3);
                for (int i2 = 0; i2 < i && arrayList2.size() > 1; i2++) {
                    arrayList2.remove(bnj2.get(arrayList.get(i2)));
                }
                bnj2.put((cxd) arrayList.get(i), (String) arrayList2.get(0));
            }
        }
        return bnj2;
    }
}
