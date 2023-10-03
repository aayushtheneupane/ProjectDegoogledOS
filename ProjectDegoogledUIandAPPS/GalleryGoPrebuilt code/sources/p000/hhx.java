package p000;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/* renamed from: hhx */
/* compiled from: PG */
final /* synthetic */ class hhx implements hpr {

    /* renamed from: a */
    private final hiq f12775a;

    /* renamed from: b */
    private final hgz f12776b;

    public hhx(hiq hiq, hgz hgz) {
        this.f12775a = hiq;
        this.f12776b = hgz;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        hiq hiq = this.f12775a;
        hgz hgz = this.f12776b;
        Void voidR = (Void) obj;
        HashSet hashSet = new HashSet();
        synchronized (hiq.f12816h) {
            for (Map.Entry entry : hiq.f12816h.entrySet()) {
                if (((hhe) entry.getValue()).mo7425b().mo7415c().containsKey(hgz)) {
                    hashSet.add((hjh) entry.getKey());
                }
            }
        }
        hiq.mo7472a((Collection) hashSet);
        return null;
    }
}
