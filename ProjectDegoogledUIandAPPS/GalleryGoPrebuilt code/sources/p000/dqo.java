package p000;

import android.view.View;
import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: dqo */
/* compiled from: PG */
final /* synthetic */ class dqo implements Function {

    /* renamed from: a */
    public static final Function f7124a = new dqo();

    private dqo() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return Integer.valueOf(((View) obj).getSystemUiVisibility());
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
