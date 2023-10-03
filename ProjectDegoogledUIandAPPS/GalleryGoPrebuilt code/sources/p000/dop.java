package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: dop */
/* compiled from: PG */
final /* synthetic */ class dop implements Function {

    /* renamed from: a */
    public static final Function f6949a = new dop();

    private dop() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return ((dik) obj).f6611c;
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
