package androidx.work;

import java.util.HashMap;
import java.util.List;

/* compiled from: PG */
public final class OverwritingInputMerger extends ahg {
    /* renamed from: a */
    public final ahf mo474a(List list) {
        ahe ahe = new ahe();
        HashMap hashMap = new HashMap();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            hashMap.putAll(((ahf) list.get(i)).mo470a());
        }
        ahe.mo469a(hashMap);
        return ahe.mo468a();
    }
}
