package p000;

import java.util.List;
import java.util.TreeMap;
import p003j$.util.Map;
import p003j$.util.Map$$CC;
import p003j$.util.NavigableMap;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BiFunction;
import p003j$.util.function.Function;

/* renamed from: bnj */
/* compiled from: PG */
final class bnj extends TreeMap implements NavigableMap, Map {
    public /* synthetic */ bnj(List list) {
        super(new bni(list));
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
}
