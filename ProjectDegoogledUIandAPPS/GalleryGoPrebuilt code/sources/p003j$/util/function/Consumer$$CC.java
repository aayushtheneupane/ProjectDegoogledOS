package p003j$.util.function;

/* renamed from: j$.util.function.Consumer$$CC */
public abstract /* synthetic */ class Consumer$$CC {
    public static Consumer andThen$$dflt$$(Consumer consumer, Consumer consumer2) {
        consumer2.getClass();
        return new Consumer$$Lambda$0(consumer, consumer2);
    }

    static /* synthetic */ void lambda$andThen$0$Consumer$$CC(Consumer consumer, Consumer consumer2, Object obj) {
        consumer.accept(obj);
        consumer2.accept(obj);
    }
}
