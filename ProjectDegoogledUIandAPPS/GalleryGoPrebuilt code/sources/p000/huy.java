package p000;

import java.util.Map;

/* renamed from: huy */
/* compiled from: PG */
final class huy extends hto {

    /* renamed from: a */
    public final transient Object[] f13444a;

    /* renamed from: b */
    public final transient int f13445b;

    /* renamed from: c */
    private final transient hsu f13446c;

    public huy(hsu hsu, Object[] objArr, int i) {
        this.f13446c = hsu;
        this.f13444a = objArr;
        this.f13445b = i;
    }

    /* renamed from: h */
    public final boolean mo7885h() {
        return true;
    }

    public final int size() {
        return this.f13445b;
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value == null || !value.equals(this.f13446c.get(key))) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public final int mo7875a(Object[] objArr, int i) {
        return mo7884g().mo7875a(objArr, i);
    }

    /* renamed from: i */
    public final hso mo7999i() {
        return new hux(this);
    }

    /* renamed from: a */
    public final hvr iterator() {
        return mo7884g().listIterator();
    }
}
