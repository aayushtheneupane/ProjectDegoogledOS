package p000;

import android.net.Uri;
import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: dmw */
/* compiled from: PG */
final /* synthetic */ class dmw implements Function {

    /* renamed from: a */
    public static final Function f6852a = new dmw();

    private dmw() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        cxi cxi = (cxi) obj;
        String[] strArr = dnn.f6870a;
        if (!fxk.m9827a(Uri.parse(cxi.f5910b))) {
            return false;
        }
        if (!cxi.f5912d && (cxi.f5909a & 65536) == 0) {
            return false;
        }
        return true;
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
