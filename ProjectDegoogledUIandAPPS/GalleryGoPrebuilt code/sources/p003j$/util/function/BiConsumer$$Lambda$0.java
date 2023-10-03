package p003j$.util.function;

/* renamed from: j$.util.function.BiConsumer$$Lambda$0 */
final /* synthetic */ class BiConsumer$$Lambda$0 implements BiConsumer {
    private final BiConsumer arg$1;
    private final BiConsumer arg$2;

    BiConsumer$$Lambda$0(BiConsumer biConsumer, BiConsumer biConsumer2) {
        this.arg$1 = biConsumer;
        this.arg$2 = biConsumer2;
    }

    public void accept(Object obj, Object obj2) {
        BiConsumer$$CC.lambda$andThen$0$BiConsumer$$CC(this.arg$1, this.arg$2, obj, obj2);
    }
}
