package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: efh */
/* compiled from: PG */
final /* synthetic */ class efh implements Function {

    /* renamed from: a */
    public static final Function f8145a = new efh();

    private efh() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return Long.valueOf(((edw) obj).mo4733g());
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
