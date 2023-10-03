package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: cbg */
/* compiled from: PG */
final /* synthetic */ class cbg implements Function {

    /* renamed from: a */
    public static final Function f4008a = new cbg();

    private cbg() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return new cba((car) obj);
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
