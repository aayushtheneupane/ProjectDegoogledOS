package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: cew */
/* compiled from: PG */
final /* synthetic */ class cew implements Function {

    /* renamed from: a */
    public static final Function f4214a = new cew();

    private cew() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(((String) obj).isEmpty());
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
