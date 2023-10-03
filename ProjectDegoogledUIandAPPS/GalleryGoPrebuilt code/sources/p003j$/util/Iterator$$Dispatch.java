package p003j$.util;

import java.util.Iterator;
import p003j$.util.function.Consumer;

/* renamed from: j$.util.Iterator$$Dispatch */
public /* synthetic */ class Iterator$$Dispatch {
    public static void forEachRemaining(Iterator it, Consumer consumer) {
        if (it instanceof Iterator) {
            ((Iterator) it).forEachRemaining(consumer);
        } else {
            Iterator$$CC.forEachRemaining$$dflt$$(it, consumer);
        }
    }
}
