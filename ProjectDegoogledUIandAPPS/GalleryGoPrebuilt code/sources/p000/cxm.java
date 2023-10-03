package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: cxm */
/* compiled from: PG */
final /* synthetic */ class cxm implements Function {

    /* renamed from: a */
    public static final Function f5937a = new cxm();

    private cxm() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return ((cxi) obj).f5921m;
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
