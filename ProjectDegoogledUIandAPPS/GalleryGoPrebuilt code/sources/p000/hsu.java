package p000;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import p003j$.util.Map$$CC;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BiFunction;
import p003j$.util.function.Function;

/* renamed from: hsu */
/* compiled from: PG */
public abstract class hsu implements Map, Serializable, p003j$.util.Map {

    /* renamed from: a */
    private transient hto f13358a;

    /* renamed from: b */
    private transient hto f13359b;

    /* renamed from: c */
    private transient hsf f13360c;

    /* renamed from: a */
    public hvr mo7897a() {
        throw null;
    }

    /* renamed from: c */
    public abstract boolean mo7899c();

    public final Object compute(Object obj, BiFunction biFunction) {
        return Map$$CC.compute$$dflt$$(this, obj, biFunction);
    }

    public final Object computeIfAbsent(Object obj, Function function) {
        return Map$$CC.computeIfAbsent$$dflt$$(this, obj, function);
    }

    public final Object computeIfPresent(Object obj, BiFunction biFunction) {
        return Map$$CC.computeIfPresent$$dflt$$(this, obj, biFunction);
    }

    /* renamed from: d */
    public abstract hto mo7935d();

    /* renamed from: e */
    public abstract hto mo7936e();

    /* renamed from: f */
    public abstract hsf mo7937f();

    public final void forEach(BiConsumer biConsumer) {
        Map$$CC.forEach$$dflt$$(this, biConsumer);
    }

    public abstract Object get(Object obj);

    public final Object merge(Object obj, Object obj2, BiFunction biFunction) {
        return Map$$CC.merge$$dflt$$(this, obj, obj2, biFunction);
    }

    public final Object putIfAbsent(Object obj, Object obj2) {
        return Map$$CC.putIfAbsent$$dflt$$(this, obj, obj2);
    }

    public final boolean remove(Object obj, Object obj2) {
        return Map$$CC.remove$$dflt$$(this, obj, obj2);
    }

    public final Object replace(Object obj, Object obj2) {
        return Map$$CC.replace$$dflt$$(this, obj, obj2);
    }

    public final boolean replace(Object obj, Object obj2, Object obj3) {
        return Map$$CC.replace$$dflt$$(this, obj, obj2, obj3);
    }

    public final void replaceAll(BiFunction biFunction) {
        Map$$CC.replaceAll$$dflt$$(this, biFunction);
    }

    /* renamed from: g */
    public static hsq m12070g() {
        return new hsq();
    }

    /* renamed from: a */
    public static hsq m12065a(int i) {
        ife.m12839a(i, "expectedSize");
        return new hsq(i);
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    public final boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    /* renamed from: a */
    public static hsu m12069a(Map map) {
        int i;
        if (!(map instanceof hsu) || (map instanceof SortedMap)) {
            Set<Map.Entry> entrySet = map.entrySet();
            boolean z = entrySet instanceof Collection;
            if (!z) {
                i = 4;
            } else {
                i = entrySet.size();
            }
            hsq hsq = new hsq(i);
            if (z) {
                hsq.mo7931a(hsq.f13353a + entrySet.size());
            }
            for (Map.Entry a : entrySet) {
                hsq.mo7933a(a);
            }
            return hsq.mo7930a();
        }
        hsu hsu = (hsu) map;
        hsu.mo7899c();
        return hsu;
    }

    /* renamed from: h */
    public final hto entrySet() {
        hto hto = this.f13358a;
        if (hto != null) {
            return hto;
        }
        hto e = mo7936e();
        this.f13358a = e;
        return e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        return entrySet().equals(((Map) obj).entrySet());
    }

    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 == null ? obj2 : obj3;
    }

    public final int hashCode() {
        return ife.m12809a((Set) entrySet());
    }

    public final boolean isEmpty() {
        return size() == 0;
    }

    /* renamed from: i */
    public final hto keySet() {
        hto hto = this.f13359b;
        if (hto != null) {
            return hto;
        }
        hto d = mo7935d();
        this.f13359b = d;
        return d;
    }

    /* renamed from: a */
    public static hsu m12066a(Object obj, Object obj2) {
        ife.m12843a(obj, obj2);
        return hvb.m12214a(1, new Object[]{obj, obj2});
    }

    /* renamed from: a */
    public static hsu m12067a(Object obj, Object obj2, Object obj3, Object obj4) {
        ife.m12843a(obj, obj2);
        ife.m12843a(obj3, obj4);
        return hvb.m12214a(2, new Object[]{obj, obj2, obj3, obj4});
    }

    /* renamed from: a */
    public static hsu m12068a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        ife.m12843a(obj, obj2);
        ife.m12843a(obj3, obj4);
        ife.m12843a(obj5, obj6);
        return hvb.m12214a(3, new Object[]{obj, obj2, obj3, obj4, obj5, obj6});
    }

    @Deprecated
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        ife.m12839a(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(((long) size) << 3, 1073741824));
        sb.append('{');
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z = false;
        }
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: j */
    public final hsf values() {
        hsf hsf = this.f13360c;
        if (hsf != null) {
            return hsf;
        }
        hsf f = mo7937f();
        this.f13360c = f;
        return f;
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new hst(this);
    }
}
