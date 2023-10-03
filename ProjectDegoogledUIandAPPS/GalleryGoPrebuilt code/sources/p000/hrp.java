package p000;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import p003j$.util.Collection;
import p003j$.util.Collection$$CC;
import p003j$.util.Set;
import p003j$.util.Set$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.stream.Stream;

/* renamed from: hrp */
/* compiled from: PG */
final class hrp extends AbstractSet implements Set, Collection {

    /* renamed from: a */
    private final /* synthetic */ hru f13312a;

    public hrp(hru hru) {
        this.f13312a = hru;
    }

    public final Stream parallelStream() {
        return Collection$$CC.parallelStream$$dflt$$(this);
    }

    public final boolean removeIf(Predicate predicate) {
        return Collection$$CC.removeIf$$dflt$$(this, predicate);
    }

    public final Spliterator spliterator() {
        return Set$$CC.spliterator$$dflt$$(this);
    }

    public final Stream stream() {
        return Collection$$CC.stream$$dflt$$(this);
    }

    public final void clear() {
        this.f13312a.clear();
    }

    public final boolean contains(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            hru hru = this.f13312a;
            Object key = entry.getKey();
            Object obj2 = hru.f13322a;
            int a = hru.mo7833a(key);
            if (a == -1 || !ife.m12891c(this.f13312a.f13326e[a], entry.getValue())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final Iterator iterator() {
        return new hrn(this.f13312a);
    }

    public final boolean remove(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            if (!this.f13312a.mo7836b()) {
                hru hru = this.f13312a;
                Object obj2 = hru.f13322a;
                int c = hru.mo7837c();
                Object key = entry.getKey();
                Object value = entry.getValue();
                hru hru2 = this.f13312a;
                int a = ife.m12808a(key, value, c, hru2.f13323b, hru2.f13324c, hru2.f13325d, hru2.f13326e);
                if (a != -1) {
                    this.f13312a.mo7834a(a, c);
                    hru hru3 = this.f13312a;
                    hru3.f13328g--;
                    hru3.mo7844d();
                    return true;
                }
            }
        }
        return false;
    }

    public final int size() {
        hru hru = this.f13312a;
        Object obj = hru.f13322a;
        return hru.f13328g;
    }
}
