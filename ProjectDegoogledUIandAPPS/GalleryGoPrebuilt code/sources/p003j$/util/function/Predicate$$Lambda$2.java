package p003j$.util.function;

/* renamed from: j$.util.function.Predicate$$Lambda$2 */
final /* synthetic */ class Predicate$$Lambda$2 implements Predicate {
    private final Predicate arg$1;
    private final Predicate arg$2;

    Predicate$$Lambda$2(Predicate predicate, Predicate predicate2) {
        this.arg$1 = predicate;
        this.arg$2 = predicate2;
    }

    public boolean test(Object obj) {
        return Predicate$$CC.lambda$or$2$Predicate$$CC(this.arg$1, this.arg$2, obj);
    }
}
