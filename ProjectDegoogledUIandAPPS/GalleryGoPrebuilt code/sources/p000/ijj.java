package p000;

import java.util.Map;
import p003j$.util.Map;

/* renamed from: ijj */
/* compiled from: PG */
final class ijj implements Map.Entry, Map.Entry {

    /* renamed from: a */
    public final Map.Entry f14349a;

    public /* synthetic */ ijj(Map.Entry entry) {
        this.f14349a = entry;
    }

    public final Object getKey() {
        return this.f14349a.getKey();
    }

    public final Object getValue() {
        if (((ijl) this.f14349a.getValue()) == null) {
            return null;
        }
        throw null;
    }

    public final Object setValue(Object obj) {
        if (obj instanceof ikf) {
            ijl ijl = (ijl) this.f14349a.getValue();
            ikf ikf = ijl.f14351a;
            ijl.f14352b = null;
            ijl.f14351a = (ikf) obj;
            return ikf;
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
