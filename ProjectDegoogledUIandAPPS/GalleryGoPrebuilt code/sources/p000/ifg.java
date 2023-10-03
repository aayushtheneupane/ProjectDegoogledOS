package p000;

import p003j$.util.function.Consumer;

/* renamed from: ifg */
/* compiled from: PG */
public final class ifg implements Consumer {

    /* renamed from: a */
    private final java.util.function.Consumer f13995a;

    private ifg(java.util.function.Consumer consumer) {
        this.f13995a = consumer;
    }

    public final void accept(Object obj) {
        this.f13995a.accept(obj);
    }

    public final Consumer andThen(Consumer consumer) {
        throw new UnsupportedOperationException("Not supported on wrapped consumers");
    }

    /* renamed from: a */
    public static Consumer m12910a(java.util.function.Consumer consumer) {
        if (consumer != null) {
            return new ifg(consumer);
        }
        return null;
    }
}
