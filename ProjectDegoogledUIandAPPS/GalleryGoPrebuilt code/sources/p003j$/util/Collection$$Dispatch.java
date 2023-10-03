package p003j$.util;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

/* renamed from: j$.util.Collection$$Dispatch */
public abstract /* synthetic */ class Collection$$Dispatch {
    public static Spliterator spliterator(Collection collection) {
        return collection instanceof Collection ? ((Collection) collection).spliterator() : collection instanceof LinkedHashSet ? DesugarLinkedHashSet.spliterator((LinkedHashSet) collection) : collection instanceof List ? List$$CC.spliterator$$dflt$$((List) collection) : collection instanceof SortedSet ? SortedSet$$CC.spliterator$$dflt$$((SortedSet) collection) : collection instanceof Set ? Set$$CC.spliterator$$dflt$$((Set) collection) : Collection$$CC.spliterator$$dflt$$(collection);
    }
}
