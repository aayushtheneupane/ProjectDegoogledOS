package p000;

import java.util.ArrayDeque;

/* renamed from: hzb */
/* compiled from: PG */
final class hzb extends hqq {

    /* renamed from: a */
    private final ArrayDeque f13674a;

    /* renamed from: b */
    private final /* synthetic */ hzc f13675b;

    public hzb(hzc hzc, Iterable iterable) {
        this.f13675b = hzc;
        ArrayDeque arrayDeque = new ArrayDeque();
        this.f13674a = arrayDeque;
        arrayDeque.addLast(new hza((Object) null, iterable));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo7687a() {
        while (!this.f13674a.isEmpty()) {
            hza hza = (hza) this.f13674a.getLast();
            if (hza.f13673b.hasNext()) {
                Object next = hza.f13673b.next();
                this.f13674a.addLast(new hza(next, this.f13675b.f13676a.mo8281a(next)));
            } else {
                this.f13674a.removeLast();
                Object obj = hza.f13672a;
                if (obj != null) {
                    return obj;
                }
            }
        }
        return mo7688b();
    }
}
