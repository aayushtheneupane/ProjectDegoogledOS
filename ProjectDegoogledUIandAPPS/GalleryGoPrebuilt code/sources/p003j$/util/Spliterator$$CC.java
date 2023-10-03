package p003j$.util;

import java.util.Comparator;

/* renamed from: j$.util.Spliterator$$CC */
public abstract /* synthetic */ class Spliterator$$CC {
    public static long getExactSizeIfKnown$$dflt$$(Spliterator spliterator) {
        if ((spliterator.characteristics() & 64) == 0) {
            return -1;
        }
        return spliterator.estimateSize();
    }

    public static boolean hasCharacteristics$$dflt$$(Spliterator spliterator, int i) {
        return (spliterator.characteristics() & i) == i;
    }

    public static Comparator getComparator$$dflt$$(Spliterator spliterator) {
        throw new IllegalStateException();
    }
}
