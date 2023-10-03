package p000;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import p003j$.util.Map$$CC;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BiFunction;
import p003j$.util.function.Function;

/* renamed from: kn */
/* compiled from: PG */
public class C0290kn extends C0309lf implements Map, p003j$.util.Map {

    /* renamed from: c */
    private C0304la f15136c;

    public C0290kn() {
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

    public final Object merge(Object obj, Object obj2, BiFunction biFunction) {
        return Map$$CC.merge$$dflt$$(this, obj, obj2, biFunction);
    }

    public final void replaceAll(BiFunction biFunction) {
        Map$$CC.replaceAll$$dflt$$(this, biFunction);
    }

    public C0290kn(int i) {
        super(i);
    }

    public C0290kn(C0309lf lfVar) {
        super(lfVar);
    }

    public final Set entrySet() {
        C0304la a = m14517a();
        if (a.f15182a == null) {
            a.f15182a = new C0299kw(a);
        }
        return a.f15182a;
    }

    /* renamed from: a */
    private final C0304la m14517a() {
        if (this.f15136c == null) {
            this.f15136c = new C0289km(this);
        }
        return this.f15136c;
    }

    public final Set keySet() {
        return m14517a().mo9313d();
    }

    public final void putAll(Map map) {
        mo9318a(this.f15193b + map.size());
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public final Collection values() {
        C0304la a = m14517a();
        if (a.f15183b == null) {
            a.f15183b = new C0302kz(a);
        }
        return a.f15183b;
    }
}
