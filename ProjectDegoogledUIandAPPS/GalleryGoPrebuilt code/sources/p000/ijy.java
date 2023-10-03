package p000;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import p003j$.util.Map;
import p003j$.util.Map$$CC;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BiFunction;
import p003j$.util.function.Function;

/* renamed from: ijy */
/* compiled from: PG */
public final class ijy extends LinkedHashMap implements Map {

    /* renamed from: b */
    public static final ijy f14368b;

    /* renamed from: a */
    public boolean f14369a = true;

    static {
        ijy ijy = new ijy();
        f14368b = ijy;
        ijy.mo8832b();
    }

    public final Object compute(Object obj, BiFunction biFunction) {
        return Map$$CC.compute$$dflt$$(this, obj, biFunction);
    }

    public final Object computeIfAbsent(Object obj, Function function) {
        return Map$$CC.computeIfAbsent$$dflt$$(this, obj, function);
    }

    public final Object computeIfPresent(Object obj, BiFunction biFunction) {
        return Map$$CC.computeIfPresent$$dflt$$(this, obj, biFunction);
    }

    public final void forEach(BiConsumer biConsumer) {
        Map$$CC.forEach$$dflt$$(this, biConsumer);
    }

    public final Object getOrDefault(Object obj, Object obj2) {
        return Map$$CC.getOrDefault$$dflt$$(this, obj, obj2);
    }

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

    private ijy() {
    }

    private ijy(java.util.Map map) {
        super(map);
    }

    /* renamed from: a */
    private static int m13702a(Object obj) {
        if (obj instanceof byte[]) {
            return ijf.m13653c((byte[]) obj);
        }
        if (!(obj instanceof iiz)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        mo8833c();
        super.clear();
    }

    /* renamed from: c */
    public final void mo8833c() {
        if (!this.f14369a) {
            throw new UnsupportedOperationException();
        }
    }

    public final Set entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    public final boolean equals(Object obj) {
        boolean z;
        if (!(obj instanceof java.util.Map)) {
            return false;
        }
        java.util.Map map = (java.util.Map) obj;
        if (this == map) {
            return true;
        }
        if (size() != map.size()) {
            return false;
        }
        for (Map.Entry entry : entrySet()) {
            if (!map.containsKey(entry.getKey())) {
                return false;
            }
            Object value = entry.getValue();
            Object obj2 = map.get(entry.getKey());
            if (!(value instanceof byte[]) || !(obj2 instanceof byte[])) {
                z = value.equals(obj2);
                continue;
            } else {
                z = Arrays.equals((byte[]) value, (byte[]) obj2);
                continue;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 0;
        for (Map.Entry entry : entrySet()) {
            i += m13702a(entry.getValue()) ^ m13702a(entry.getKey());
        }
        return i;
    }

    /* renamed from: b */
    public final void mo8832b() {
        this.f14369a = false;
    }

    /* renamed from: a */
    public final ijy mo8831a() {
        return isEmpty() ? new ijy() : new ijy(this);
    }

    public final Object put(Object obj, Object obj2) {
        mo8833c();
        ijf.m13650a(obj);
        ijf.m13650a(obj2);
        return super.put(obj, obj2);
    }

    public final void putAll(java.util.Map map) {
        mo8833c();
        for (Object next : map.keySet()) {
            ijf.m13650a(next);
            ijf.m13650a(map.get(next));
        }
        super.putAll(map);
    }

    public final Object remove(Object obj) {
        mo8833c();
        return super.remove(obj);
    }
}
