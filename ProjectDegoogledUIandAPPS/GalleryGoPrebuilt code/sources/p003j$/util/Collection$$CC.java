package p003j$.util;

import java.util.Collection;
import java.util.Iterator;
import p003j$.util.function.Predicate;
import p003j$.util.stream.Stream;
import p003j$.util.stream.StreamSupport;

/* renamed from: j$.util.Collection$$CC */
public abstract /* synthetic */ class Collection$$CC {
    public static boolean removeIf$$dflt$$(Collection collection, Predicate predicate) {
        predicate.getClass();
        Iterator it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (predicate.test(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    public static Spliterator spliterator$$dflt$$(Collection collection) {
        return Spliterators.spliterator(collection, 0);
    }

    public static Stream stream$$dflt$$(Collection collection) {
        return StreamSupport.stream(Collection$$Dispatch.spliterator(collection), false);
    }

    public static Stream parallelStream$$dflt$$(Collection collection) {
        return StreamSupport.stream(Collection$$Dispatch.spliterator(collection), true);
    }
}
