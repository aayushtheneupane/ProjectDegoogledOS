package p000;

import java.util.Iterator;
import java.util.Map;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: ijk */
/* compiled from: PG */
final class ijk implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    private final Iterator f14350a;

    public ijk(Iterator it) {
        this.f14350a = it;
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        return this.f14350a.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.f14350a.next();
        return entry.getValue() instanceof ijl ? new ijj(entry) : entry;
    }

    public final void remove() {
        this.f14350a.remove();
    }
}
