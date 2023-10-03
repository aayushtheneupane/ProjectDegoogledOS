package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: doa */
/* compiled from: PG */
final /* synthetic */ class doa implements Function {

    /* renamed from: a */
    public static final Function f6928a = new doa();

    private doa() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return ((dls) obj).f6797b;
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
