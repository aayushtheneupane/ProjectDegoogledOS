package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: doc */
/* compiled from: PG */
final /* synthetic */ class doc implements Function {

    /* renamed from: a */
    public static final Function f6930a = new doc();

    private doc() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return ((dls) obj).f6796a;
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
