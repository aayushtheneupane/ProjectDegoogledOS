package p000;

import android.content.Context;
import java.util.concurrent.Executor;

/* renamed from: biw */
/* compiled from: PG */
final class biw implements iqk {

    /* renamed from: a */
    private final int f2485a;

    /* renamed from: b */
    private final /* synthetic */ bix f2486b;

    public biw(bix bix, int i) {
        this.f2486b = bix;
        this.f2485a = i;
    }

    /* renamed from: a */
    public final Object mo2097a() {
        Object obj;
        Object obj2;
        int i = this.f2485a;
        if (i == 0) {
            bix bix = this.f2486b;
            Object obj3 = bix.f2487a;
            if (obj3 instanceof iok) {
                synchronized (obj3) {
                    obj = bix.f2487a;
                    if (obj instanceof iok) {
                        obj = inl.m14192a(bix.f2491e.mo2329f(), bix.mo2098a());
                        bix.f2487a = iog.m14219a(bix.f2487a, obj);
                    }
                }
                obj3 = obj;
            }
            return (Executor) obj3;
        } else if (i == 1) {
            bix bix2 = this.f2486b;
            Object obj4 = bix2.f2489c;
            if (obj4 instanceof iok) {
                synchronized (obj4) {
                    obj2 = bix2.f2489c;
                    if (obj2 instanceof iok) {
                        iqk iqk = bix2.f2490d;
                        iqk iqk2 = bix2.f2488b;
                        if (iqk2 == null) {
                            iqk2 = new biw(bix2, 2);
                            bix2.f2488b = iqk2;
                        }
                        obj2 = (ipl) iol.m14229a((Object) ips.m14309a(iqk, iqk2), "Cannot return null from a non-@Nullable @Provides method");
                        bix2.f2489c = iog.m14219a(bix2.f2489c, obj2);
                    }
                }
                obj4 = obj2;
            }
            return (ipl) obj4;
        } else if (i == 2) {
            bix bix3 = this.f2486b;
            ipk b = hld.m11682b();
            inc a = bix3.mo2098a();
            imr b2 = bix3.mo2100b();
            bix3.mo2101c();
            return hto.m12121a((Object) b, (Object) inb.m14167a(a, b2));
        } else if (i != 3) {
            bix bix4 = this.f2486b;
            Context a2 = ftz.m9622a(bix4.f2491e.f2702a);
            fzx bF = bix4.f2491e.mo2237bF();
            bix4.f2491e.mo2329f();
            return efs.m7381a(a2, egc.m7397a(bF), bix4.f2491e.mo2314cn(), bix4.f2491e.mo2238bG(), efv.m7386a(ftz.m9622a(bix4.f2491e.f2702a)));
        } else {
            bix bix5 = this.f2486b;
            return ink.m14190a(bix5.f2491e.mo2314cn(), bix5.mo2100b(), bix5.mo2098a(), bix5.mo2101c());
        }
    }
}
