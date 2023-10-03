package p003j$.util.function;

/* renamed from: j$.util.function.BiConsumer$$CC */
public abstract /* synthetic */ class BiConsumer$$CC {
    public static BiConsumer andThen$$dflt$$(BiConsumer biConsumer, BiConsumer biConsumer2) {
        biConsumer2.getClass();
        return new BiConsumer$$Lambda$0(biConsumer, biConsumer2);
    }

    static /* synthetic */ void lambda$andThen$0$BiConsumer$$CC(BiConsumer biConsumer, BiConsumer biConsumer2, Object obj, Object obj2) {
        biConsumer.accept(obj, obj2);
        biConsumer2.accept(obj, obj2);
    }
}
