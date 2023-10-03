package p000;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: ifj */
/* compiled from: PG */
final class ifj {

    /* renamed from: a */
    private final ConcurrentHashMap f13998a = new ConcurrentHashMap(16, 0.75f, 10);

    /* renamed from: b */
    private final ReferenceQueue f13999b = new ReferenceQueue();

    /* renamed from: a */
    public final List mo8491a(Throwable th, boolean z) {
        ReferenceQueue referenceQueue = this.f13999b;
        while (true) {
            Reference poll = referenceQueue.poll();
            if (poll == null) {
                break;
            }
            this.f13998a.remove(poll);
            referenceQueue = this.f13999b;
        }
        List list = (List) this.f13998a.get(new ifi(th, (ReferenceQueue) null));
        if (!z || list != null) {
            return list;
        }
        Vector vector = new Vector(2);
        List list2 = (List) this.f13998a.putIfAbsent(new ifi(th, this.f13999b), vector);
        return list2 != null ? list2 : vector;
    }
}
