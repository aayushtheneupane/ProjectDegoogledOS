package p000;

import android.net.Uri;
import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: dla */
/* compiled from: PG */
final /* synthetic */ class dla implements Function {

    /* renamed from: a */
    public static final Function f6764a = new dla();

    private dla() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return ((Uri) obj).toString();
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
