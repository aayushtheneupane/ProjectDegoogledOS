package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: eee */
/* compiled from: PG */
final /* synthetic */ class eee implements Function {

    /* renamed from: a */
    public static final Function f8095a = new eee();

    private eee() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return Integer.valueOf(((edw) obj).mo4732f());
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
