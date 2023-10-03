package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: cev */
/* compiled from: PG */
final /* synthetic */ class cev implements Function {

    /* renamed from: a */
    public static final Function f4213a = new cev();

    private cev() {
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        cxi cxi = (cxi) obj;
        boolean z = true;
        if ((cxi.f5909a & 16) != 0) {
            cxh cxh = cxh.UNKNOWN_MEDIA_TYPE;
            cxh a = cxh.m5592a(cxi.f5913e);
            if (a == null) {
                a = cxh.UNKNOWN_MEDIA_TYPE;
            }
            if (!cxh.equals(a)) {
                z = false;
            }
        }
        return Boolean.valueOf(z);
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
