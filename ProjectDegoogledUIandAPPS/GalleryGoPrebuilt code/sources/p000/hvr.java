package p000;

import java.util.Iterator;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: hvr */
/* compiled from: PG */
public abstract class hvr implements Iterator, p003j$.util.Iterator {
    protected hvr() {
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    @Deprecated
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
