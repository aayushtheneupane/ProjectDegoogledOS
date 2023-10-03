package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: bdp */
/* compiled from: PG */
public final class bdp {

    /* renamed from: a */
    private final List f2092a = new ArrayList();

    /* renamed from: a */
    public final synchronized void mo1840a(Class cls, aqk aqk) {
        this.f2092a.add(new bdo(cls, aqk));
    }

    /* renamed from: a */
    public final synchronized aqk mo1839a(Class cls) {
        aqk aqk;
        List list = this.f2092a;
        int size = list.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                aqk = null;
                break;
            }
            bdo bdo = (bdo) list.get(i);
            i++;
            if (bdo.f2090a.isAssignableFrom(cls)) {
                aqk = bdo.f2091b;
                break;
            }
        }
        return aqk;
    }
}
