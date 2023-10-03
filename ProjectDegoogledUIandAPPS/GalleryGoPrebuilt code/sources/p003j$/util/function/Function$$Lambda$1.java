package p003j$.util.function;

/* renamed from: j$.util.function.Function$$Lambda$1 */
final /* synthetic */ class Function$$Lambda$1 implements Function {
    private final Function arg$1;
    private final Function arg$2;

    Function$$Lambda$1(Function function, Function function2) {
        this.arg$1 = function;
        this.arg$2 = function2;
    }

    public Object apply(Object obj) {
        return this.arg$2.apply(this.arg$1.apply(obj));
    }
}
