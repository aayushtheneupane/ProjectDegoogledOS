package p000;

import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: fuf */
/* compiled from: PG */
final class fuf implements fuc {

    /* renamed from: a */
    private final ConcurrentHashMap f10610a = new ConcurrentHashMap();

    /* renamed from: a */
    public final Object mo6186a(Object obj) {
        Object obj2 = this.f10610a.get(obj);
        if (obj2 != null) {
            return obj2;
        }
        this.f10610a.putIfAbsent(obj, new Object());
        return this.f10610a.get(obj);
    }
}
