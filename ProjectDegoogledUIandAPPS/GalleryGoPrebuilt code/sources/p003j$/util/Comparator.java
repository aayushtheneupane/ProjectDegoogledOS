package p003j$.util;

import p003j$.util.function.Function;

/* renamed from: j$.util.Comparator */
public interface Comparator {
    java.util.Comparator thenComparing(Function function);

    java.util.Comparator thenComparing(java.util.Comparator comparator);
}
