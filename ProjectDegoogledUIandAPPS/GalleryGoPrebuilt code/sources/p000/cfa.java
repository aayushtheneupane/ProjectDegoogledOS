package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: cfa */
/* compiled from: PG */
final /* synthetic */ class cfa implements Function {

    /* renamed from: a */
    public static final Function f4222a = new cfa();

    private cfa() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return ((dhu) obj).f6572f;
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
