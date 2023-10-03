package p003j$.util;

import java.util.LinkedHashSet;

/* renamed from: j$.util.DesugarLinkedHashSet */
public abstract class DesugarLinkedHashSet {
    public static Spliterator spliterator(LinkedHashSet linkedHashSet) {
        return Spliterators.spliterator(linkedHashSet, 17);
    }
}
