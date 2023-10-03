package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: dgr */
/* compiled from: PG */
public final /* synthetic */ class dgr implements Function {

    /* renamed from: a */
    private final String f6515a;

    public dgr(String str) {
        this.f6515a = str;
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return Boolean.valueOf(this.f6515a.equals((String) obj));
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
