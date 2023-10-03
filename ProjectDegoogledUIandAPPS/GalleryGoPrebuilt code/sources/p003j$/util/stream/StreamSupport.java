package p003j$.util.stream;

import p003j$.util.Spliterator;
import p003j$.util.stream.ReferencePipeline;

/* renamed from: j$.util.stream.StreamSupport */
public abstract class StreamSupport {
    public static Stream stream(Spliterator spliterator, boolean z) {
        spliterator.getClass();
        return new ReferencePipeline.Head(spliterator, StreamOpFlag.fromCharacteristics(spliterator), z);
    }
}
