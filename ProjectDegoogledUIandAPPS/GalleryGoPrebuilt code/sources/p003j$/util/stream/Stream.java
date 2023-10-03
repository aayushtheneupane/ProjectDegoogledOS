package p003j$.util.stream;

import p003j$.util.function.Predicate;

/* renamed from: j$.util.stream.Stream */
public interface Stream extends AutoCloseable {
    Object collect(Collector collector);

    Stream filter(Predicate predicate);
}
