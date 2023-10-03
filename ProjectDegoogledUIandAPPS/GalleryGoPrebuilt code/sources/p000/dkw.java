package p000;

import android.net.Uri;
import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: dkw */
/* compiled from: PG */
final /* synthetic */ class dkw implements Function {

    /* renamed from: a */
    public static final Function f6752a = new dkw();

    private dkw() {
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
