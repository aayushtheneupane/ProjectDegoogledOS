package p000;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import p003j$.util.Map;
import p003j$.util.Map$$CC;
import p003j$.util.function.BiConsumer;
import p003j$.util.function.BiFunction;
import p003j$.util.function.Function;

/* renamed from: ilh */
/* compiled from: PG */
final class ilh extends AbstractMap implements Map {

    /* renamed from: a */
    public List f14442a = Collections.emptyList();

    /* renamed from: b */
    public java.util.Map f14443b = Collections.emptyMap();

    /* renamed from: c */
    public boolean f14444c;

    /* renamed from: d */
    public java.util.Map f14445d = Collections.emptyMap();

    /* renamed from: e */
    private final int f14446e;

    /* renamed from: f */
    private volatile ilg f14447f;

    private ilh(int i) {
        this.f14446e = i;
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

    /* renamed from: a */
    private final int m13959a(Comparable comparable) {
        int size = this.f14442a.size() - 1;
        int i = 0;
        if (size >= 0) {
            int compareTo = comparable.compareTo(((ile) this.f14442a.get(size)).f14434a);
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = comparable.compareTo(((ile) this.f14442a.get(i2)).f14434a);
            if (compareTo2 < 0) {
                size = i2 - 1;
            } else if (compareTo2 <= 0) {
                return i2;
            } else {
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    /* renamed from: c */
    public final void mo8921c() {
        if (this.f14444c) {
            throw new UnsupportedOperationException();
        }
    }

    public final void clear() {
        mo8921c();
        if (!this.f14442a.isEmpty()) {
            this.f14442a.clear();
        }
        if (!this.f14443b.isEmpty()) {
            this.f14443b.clear();
        }
    }

    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return m13959a(comparable) >= 0 || this.f14443b.containsKey(comparable);
    }

    public final Set entrySet() {
        if (this.f14447f == null) {
            this.f14447f = new ilg(this);
        }
        return this.f14447f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ilh)) {
            return super.equals(obj);
        }
        ilh ilh = (ilh) obj;
        int size = size();
        if (size != ilh.size()) {
            return false;
        }
        int a = mo8916a();
        if (a != ilh.mo8916a()) {
            return entrySet().equals(ilh.entrySet());
        }
        for (int i = 0; i < a; i++) {
            if (!mo8919b(i).equals(ilh.mo8919b(i))) {
                return false;
            }
        }
        if (a != size) {
            return this.f14443b.equals(ilh.f14443b);
        }
        return true;
    }

    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int a = m13959a(comparable);
        if (a >= 0) {
            return ((ile) this.f14442a.get(a)).f14435b;
        }
        return this.f14443b.get(comparable);
    }

    /* renamed from: b */
    public final Map.Entry mo8919b(int i) {
        return (Map.Entry) this.f14442a.get(i);
    }

    /* renamed from: a */
    public final int mo8916a() {
        return this.f14442a.size();
    }

    /* renamed from: b */
    public final Iterable mo8918b() {
        return !this.f14443b.isEmpty() ? this.f14443b.entrySet() : ild.f14433b;
    }

    /* renamed from: d */
    private final SortedMap m13961d() {
        mo8921c();
        if (this.f14443b.isEmpty() && !(this.f14443b instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.f14443b = treeMap;
            this.f14445d = treeMap.descendingMap();
        }
        return (SortedMap) this.f14443b;
    }

    public final int hashCode() {
        int a = mo8916a();
        int i = 0;
        for (int i2 = 0; i2 < a; i2++) {
            i += ((ile) this.f14442a.get(i2)).hashCode();
        }
        return this.f14443b.size() > 0 ? i + this.f14443b.hashCode() : i;
    }

    /* renamed from: a */
    static ilh m13960a(int i) {
        return new ilh(i);
    }

    /* renamed from: a */
    public final Object put(Comparable comparable, Object obj) {
        mo8921c();
        int a = m13959a(comparable);
        if (a >= 0) {
            return ((ile) this.f14442a.get(a)).setValue(obj);
        }
        mo8921c();
        if (this.f14442a.isEmpty() && !(this.f14442a instanceof ArrayList)) {
            this.f14442a = new ArrayList(this.f14446e);
        }
        int i = -(a + 1);
        if (i >= this.f14446e) {
            return m13961d().put(comparable, obj);
        }
        int size = this.f14442a.size();
        int i2 = this.f14446e;
        if (size == i2) {
            ile ile = (ile) this.f14442a.remove(i2 - 1);
            m13961d().put(ile.f14434a, ile.f14435b);
        }
        this.f14442a.add(i, new ile(this, comparable, obj));
        return null;
    }

    public final Object remove(Object obj) {
        mo8921c();
        Comparable comparable = (Comparable) obj;
        int a = m13959a(comparable);
        if (a >= 0) {
            return mo8920c(a);
        }
        if (!this.f14443b.isEmpty()) {
            return this.f14443b.remove(comparable);
        }
        return null;
    }

    /* renamed from: c */
    public final Object mo8920c(int i) {
        mo8921c();
        Object obj = ((ile) this.f14442a.remove(i)).f14435b;
        if (!this.f14443b.isEmpty()) {
            Iterator it = m13961d().entrySet().iterator();
            this.f14442a.add(new ile(this, (Map.Entry) it.next()));
            it.remove();
        }
        return obj;
    }

    public final int size() {
        return this.f14442a.size() + this.f14443b.size();
    }
}
