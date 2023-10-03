package p003j$.util.function;

/* renamed from: j$.util.function.Predicate$$Lambda$1 */
final /* synthetic */ class Predicate$$Lambda$1 implements Predicate {
    private final Predicate arg$1;

    Predicate$$Lambda$1(Predicate predicate) {
        this.arg$1 = predicate;
    }

    public boolean test(Object obj) {
        return Predicate$$CC.lambda$negate$1$Predicate$$CC(this.arg$1, obj);
    }
}
