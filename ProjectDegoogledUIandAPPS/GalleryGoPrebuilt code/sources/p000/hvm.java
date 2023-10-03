package p000;

import java.util.AbstractSet;
import p003j$.util.Collection;
import p003j$.util.Collection$$CC;
import p003j$.util.Set;
import p003j$.util.Set$$CC;
import p003j$.util.Spliterator;
import p003j$.util.function.Predicate;
import p003j$.util.stream.Stream;

/* renamed from: hvm */
/* compiled from: PG */
abstract class hvm extends AbstractSet implements Set, Collection {
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

    public boolean removeAll(java.util.Collection collection) {
        return ife.m12857a((java.util.Set) this, collection);
    }

    public boolean retainAll(java.util.Collection collection) {
        return super.retainAll((java.util.Collection) ife.m12898e((Object) collection));
    }
}
