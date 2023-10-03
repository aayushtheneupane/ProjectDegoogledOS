package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: efg */
/* compiled from: PG */
final /* synthetic */ class efg implements Function {

    /* renamed from: a */
    public static final Function f8144a = new efg();

    private efg() {
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
