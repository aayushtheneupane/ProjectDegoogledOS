package p000;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* renamed from: htd */
/* compiled from: PG */
public final class htd {

    /* renamed from: a */
    public final Map f13370a = hru.m11983a();

    public htd(byte[] bArr) {
    }

    /* renamed from: a */
    public final void mo7978a(Object obj, Object obj2) {
        ife.m12843a(obj, obj2);
        Collection collection = (Collection) this.f13370a.get(obj);
        if (collection == null) {
            Map map = this.f13370a;
            ArrayList arrayList = new ArrayList();
            map.put(obj, arrayList);
            collection = arrayList;
        }
        collection.add(obj2);
    }

    public htd() {
    }
}
