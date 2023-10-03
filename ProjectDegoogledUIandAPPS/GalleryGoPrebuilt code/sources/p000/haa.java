package p000;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: haa */
/* compiled from: PG */
final class haa implements gyu {

    /* renamed from: a */
    private final AtomicReference f12393a;

    private haa(Map map, Object obj) {
        this.f12393a = new AtomicReference(new gzz(map, obj, false));
    }

    /* renamed from: a */
    public final boolean mo7193a() {
        return false;
    }

    /* renamed from: b */
    public final void mo7194b() {
        throw new UnsupportedOperationException("Can't change observed values");
    }

    /* renamed from: a */
    static gyu m11097a(Map map, Object obj) {
        return new haa(map, obj);
    }

    /* renamed from: a */
    public final Object mo7192a(Object obj) {
        gzz gzz;
        gzz gzz2 = null;
        while (true) {
            gzz = (gzz) this.f12393a.get();
            if (gzz.f12387c) {
                break;
            }
            if (gzz2 == null) {
                gzz2 = new gzz(gzz.f12385a, gzz.f12386b, true);
            } else {
                gzz2.f12385a = gzz.f12385a;
                gzz2.f12386b = gzz.f12386b;
            }
            if (this.f12393a.compareAndSet(gzz, gzz2)) {
                gzz = gzz2;
                break;
            }
        }
        return ife.m12830a(gzz.f12385a.get(obj), "Unregistered experiment: %s. Registered experiments are: %s", obj, (Object) gzz);
    }

    /* renamed from: c */
    public final Object mo7196c() {
        return ((gzz) this.f12393a.get()).f12386b;
    }

    /* renamed from: b */
    public final boolean mo7195b(Map map, Object obj) {
        gzz gzz;
        gzz gzz2 = null;
        do {
            gzz = (gzz) this.f12393a.get();
            if (gzz.f12387c) {
                return false;
            }
            if (gzz2 == null) {
                gzz2 = new gzz(map, obj, false);
            }
        } while (!this.f12393a.compareAndSet(gzz, gzz2));
        return true;
    }
}
