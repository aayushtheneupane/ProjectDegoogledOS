package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: drk */
/* compiled from: PG */
final /* synthetic */ class drk implements Function {

    /* renamed from: a */
    public static final Function f7234a = new drk();

    private drk() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(((dlr) obj).mo4224a());
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
