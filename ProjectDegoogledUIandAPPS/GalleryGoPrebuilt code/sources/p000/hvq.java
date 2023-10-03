package p000;

import java.util.Iterator;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: hvq */
/* compiled from: PG */
abstract class hvq implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    private final Iterator f13489a;

    public hvq(Iterator it) {
        this.f13489a = (Iterator) ife.m12898e((Object) it);
    }

    /* renamed from: a */
    public abstract Object mo8006a(Object obj);

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        return this.f13489a.hasNext();
    }

    public final Object next() {
        return mo8006a(this.f13489a.next());
    }

    public final void remove() {
        this.f13489a.remove();
    }
}
