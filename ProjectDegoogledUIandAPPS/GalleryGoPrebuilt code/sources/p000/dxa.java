package p000;

import p003j$.util.function.Function;
import p003j$.util.function.Function$$CC;

/* renamed from: dxa */
/* compiled from: PG */
final /* synthetic */ class dxa implements Function {

    /* renamed from: a */
    private final cjr f7550a;

    public dxa(cjr cjr) {
        this.f7550a = cjr;
    }

    public final Function andThen(Function function) {
        return Function$$CC.andThen$$dflt$$(this, function);
    }

    public final Object apply(Object obj) {
        dxy dxy = (dxy) obj;
        if (!this.f7550a.mo3175a()) {
            hpq a = hpp.f13230a.mo7660a(dxc.f7552a);
            a.getClass();
            return gwa.m10928a(new dxd(a));
        }
        dxy dxy2 = dxy.DEFAULT_SORT_METHOD;
        dwu dwu = dwu.MEDIA;
        int ordinal = dxy.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return bqa.m3349a(new dvq());
        }
        if (ordinal == 2) {
            return bqa.m3349a(new dvx());
        }
        if (ordinal == 3) {
            return bqa.m3349a(new dyf());
        }
        int i = dxy.f7618e;
        StringBuilder sb = new StringBuilder(32);
        sb.append("Unknown sort method: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    public final Function compose(Function function) {
        return Function$$CC.compose$$dflt$$(this, function);
    }
}
