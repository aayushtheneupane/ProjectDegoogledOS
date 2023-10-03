package androidx.lifecycle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: androidx.lifecycle.a */
class C0444a {

    /* renamed from: Dp */
    final Map f434Dp = new HashMap();

    /* renamed from: Ep */
    final Map f435Ep;

    C0444a(Map map) {
        this.f435Ep = map;
        for (Map.Entry entry : map.entrySet()) {
            Lifecycle$Event lifecycle$Event = (Lifecycle$Event) entry.getValue();
            List list = (List) this.f434Dp.get(lifecycle$Event);
            if (list == null) {
                list = new ArrayList();
                this.f434Dp.put(lifecycle$Event, list);
            }
            list.add(entry.getKey());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo4447a(C0453j jVar, Lifecycle$Event lifecycle$Event, Object obj) {
        m441a((List) this.f434Dp.get(lifecycle$Event), jVar, lifecycle$Event, obj);
        m441a((List) this.f434Dp.get(Lifecycle$Event.ON_ANY), jVar, lifecycle$Event, obj);
    }

    /* renamed from: a */
    private static void m441a(List list, C0453j jVar, Lifecycle$Event lifecycle$Event, Object obj) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                ((C0445b) list.get(size)).mo4448b(jVar, lifecycle$Event, obj);
            }
        }
    }
}
