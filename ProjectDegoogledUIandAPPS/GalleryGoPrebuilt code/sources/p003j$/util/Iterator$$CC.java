package p003j$.util;

import java.util.Iterator;
import p003j$.util.function.Consumer;

/* renamed from: j$.util.Iterator$$CC */
public abstract /* synthetic */ class Iterator$$CC {
    public static void remove$$dflt$$(Iterator it) {
        throw new UnsupportedOperationException("remove");
    }

    public static void forEachRemaining$$dflt$$(Iterator it, Consumer consumer) {
        consumer.getClass();
        while (it.hasNext()) {
            consumer.accept(it.next());
        }
    }
}
