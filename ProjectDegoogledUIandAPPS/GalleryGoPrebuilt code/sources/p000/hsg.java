package p000;

import java.io.Serializable;

/* renamed from: hsg */
/* compiled from: PG */
final class hsg extends hrh implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final Object f13342a;

    /* renamed from: b */
    private final Object f13343b;

    public hsg(Object obj, Object obj2) {
        this.f13342a = obj;
        this.f13343b = obj2;
    }

    public final Object getKey() {
        return this.f13342a;
    }

    public final Object getValue() {
        return this.f13343b;
    }

    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }
}
