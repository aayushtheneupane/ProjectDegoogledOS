package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: dnx */
/* compiled from: PG */
final /* synthetic */ class dnx implements Function {

    /* renamed from: a */
    public static final Function f6925a = new dnx();

    private dnx() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        dls dls = (dls) obj;
        if (dls.f6800e.isPresent()) {
            return ((dlr) dls.f6800e.get()).mo4227d();
        }
        return null;
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
