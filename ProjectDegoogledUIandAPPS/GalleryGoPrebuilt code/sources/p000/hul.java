package p000;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Set;
import p003j$.util.Map;
import p003j$.util.Map$$CC;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BiFunction;
import p003j$.util.function.Function;

/* renamed from: hul */
/* compiled from: PG */
abstract class hul extends AbstractMap implements Map {

    /* renamed from: a */
    private transient Set f13415a;

    /* renamed from: b */
    private transient Set f13416b;

    /* renamed from: c */
    private transient Collection f13417c;

    /* renamed from: a */
    public abstract Set mo7701a();

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

    public final Set entrySet() {
        Set set = this.f13415a;
        if (set != null) {
            return set;
        }
        Set a = mo7701a();
        this.f13415a = a;
        return a;
    }

    public Set keySet() {
        Set set = this.f13416b;
        if (set != null) {
            return set;
        }
        huj huj = new huj(this);
        this.f13416b = huj;
        return huj;
    }

    public final Collection values() {
        Collection collection = this.f13417c;
        if (collection != null) {
            return collection;
        }
        huk huk = new huk(this);
        this.f13417c = huk;
        return huk;
    }
}
