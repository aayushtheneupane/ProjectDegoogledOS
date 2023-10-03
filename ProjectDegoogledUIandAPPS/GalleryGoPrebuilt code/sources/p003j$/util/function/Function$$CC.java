package p003j$.util.function;

/* renamed from: j$.util.function.Function$$CC */
public abstract /* synthetic */ class Function$$CC {
    public static Function compose$$dflt$$(Function function, Function function2) {
        function2.getClass();
        return new Function$$Lambda$0(function, function2);
    }

    public static Function andThen$$dflt$$(Function function, Function function2) {
        function2.getClass();
        return new Function$$Lambda$1(function, function2);
    }
}
