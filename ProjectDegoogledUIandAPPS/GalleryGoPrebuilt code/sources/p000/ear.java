package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: ear */
/* compiled from: PG */
final /* synthetic */ class ear implements Function {

    /* renamed from: a */
    public static final Function f7795a = new ear();

    private ear() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(((eaq) obj).mo4235b());
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
