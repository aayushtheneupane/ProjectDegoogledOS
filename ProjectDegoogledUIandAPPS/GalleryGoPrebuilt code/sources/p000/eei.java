package p000;

import android.content.pm.ResolveInfo;
import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: eei */
/* compiled from: PG */
final /* synthetic */ class eei implements Function {

    /* renamed from: a */
    private final een f8101a;

    public eei(een een) {
        this.f8101a = een;
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        return ((ResolveInfo) obj).loadLabel(this.f8101a.f8109d);
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
