package p003j$.util;

import p003j$.util.stream.Stream;
import p003j$.util.stream.StreamSupport;

/* renamed from: j$.util.DesugarArrays */
public abstract class DesugarArrays {
    public static Spliterator spliterator(Object[] objArr, int i, int i2) {
        return Spliterators.spliterator(objArr, i, i2, 1040);
    }

    public static Stream stream(Object[] objArr) {
        return stream(objArr, 0, objArr.length);
    }

    public static Stream stream(Object[] objArr, int i, int i2) {
        return StreamSupport.stream(spliterator(objArr, i, i2), false);
    }
}
