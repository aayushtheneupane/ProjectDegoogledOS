package p000;

import java.util.Iterator;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: aoc */
/* compiled from: PG */
final class aoc implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    private final /* synthetic */ Iterator f1245a;

    public aoc(Iterator it) {
        this.f1245a = it;
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        return this.f1245a.hasNext();
    }

    public final Object next() {
        return this.f1245a.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException("remove() is not allowed due to the internal contraints");
    }
}
