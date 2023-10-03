package p000;

import java.util.Iterator;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: ilo */
/* compiled from: PG */
final class ilo implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    private final Iterator f14458a = this.f14459b.f14460a.iterator();

    /* renamed from: b */
    private final /* synthetic */ ilp f14459b;

    public ilo(ilp ilp) {
        this.f14459b = ilp;
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        return this.f14458a.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.f14458a.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
