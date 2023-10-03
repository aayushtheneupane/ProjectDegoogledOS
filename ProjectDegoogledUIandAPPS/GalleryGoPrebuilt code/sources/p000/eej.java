package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: eej */
/* compiled from: PG */
final /* synthetic */ class eej implements Function {

    /* renamed from: a */
    public static final Function f8102a = new eej();

    private eej() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return ((CharSequence) obj).toString();
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
