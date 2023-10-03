package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: dod */
/* compiled from: PG */
final /* synthetic */ class dod implements Function {

    /* renamed from: a */
    public static final Function f6931a = new dod();

    private dod() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return ((dls) obj).f6798c;
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
