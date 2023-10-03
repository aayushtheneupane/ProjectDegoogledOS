package p000;

import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: ihm */
/* compiled from: PG */
abstract class ihm implements ihq {
    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final /* bridge */ /* synthetic */ Object next() {
        return Byte.valueOf(mo8591a());
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
