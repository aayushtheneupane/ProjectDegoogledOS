package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: bnn */
/* compiled from: PG */
final /* synthetic */ class bnn implements hpr {

    /* renamed from: a */
    public static final hpr f3200a = new bnn();

    private bnn() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        List list = (List) obj;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (((Boolean) list.get(i)).booleanValue()) {
                arrayList.add((cxd) bnq.f3203o.get(i));
            }
        }
        return arrayList;
    }
}
