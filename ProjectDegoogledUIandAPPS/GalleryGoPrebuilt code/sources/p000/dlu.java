package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: dlu */
/* compiled from: PG */
final /* synthetic */ class dlu implements Function {

    /* renamed from: a */
    public static final Function f6803a = new dlu();

    private dlu() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(C0637xj.m15904a((dik) obj));
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
