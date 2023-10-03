package p000;

import java.util.AbstractCollection;
import java.util.Iterator;
import p003j$.util.Collection;
import p003j$.util.Collection$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.stream.Stream;

/* renamed from: hrt */
/* compiled from: PG */
final class hrt extends AbstractCollection implements Collection {

    /* renamed from: a */
    private final /* synthetic */ hru f13321a;

    public hrt(hru hru) {
        this.f13321a = hru;
    }

    public final Stream parallelStream() {
        return Collection$$CC.parallelStream$$dflt$$(this);
    }

    public final boolean removeIf(Predicate predicate) {
        return Collection$$CC.removeIf$$dflt$$(this, predicate);
    }

    public final Spliterator spliterator() {
        return Collection$$CC.spliterator$$dflt$$(this);
    }

    public final Stream stream() {
        return Collection$$CC.stream$$dflt$$(this);
    }

    public final void clear() {
        this.f13321a.clear();
    }

    public final Iterator iterator() {
        return new hro(this.f13321a);
    }

    public final int size() {
        hru hru = this.f13321a;
        Object obj = hru.f13322a;
        return hru.f13328g;
    }
}
