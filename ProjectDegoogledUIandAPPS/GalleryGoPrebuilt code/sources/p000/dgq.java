package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: dgq */
/* compiled from: PG */
public final /* synthetic */ class dgq implements Function {

    /* renamed from: a */
    public static final Function f6514a = new dgq();

    private dgq() {
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
