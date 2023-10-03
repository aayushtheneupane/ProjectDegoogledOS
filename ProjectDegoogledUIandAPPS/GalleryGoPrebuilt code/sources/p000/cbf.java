package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: cbf */
/* compiled from: PG */
final /* synthetic */ class cbf implements Function {

    /* renamed from: a */
    public static final Function f4007a = new cbf();

    private cbf() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return ((cbd) obj).mo2973b();
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
