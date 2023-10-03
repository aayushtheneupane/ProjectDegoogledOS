package p000;

import android.content.Context;
import java.util.concurrent.Executor;

/* renamed from: bjf */
/* compiled from: PG */
final class bjf implements iqk {

    /* renamed from: a */
    private final int f2578a;

    /* renamed from: b */
    private final /* synthetic */ bjg f2579b;

    public bjf(bjg bjg, int i) {
        this.f2579b = bjg;
        this.f2578a = i;
    }

    /* renamed from: a */
    public final Object mo2097a() {
        Object obj;
        Object obj2;
        int i = this.f2578a;
        if (i == 0) {
            bjg bjg = this.f2579b;
            Object obj3 = bjg.f2602a;
            if (obj3 instanceof iok) {
                synchronized (obj3) {
                    obj = bjg.f2602a;
                    if (obj instanceof iok) {
                        obj = inl.m14192a(bjg.f2606e.mo2329f(), bjg.mo2124a());
                        bjg.f2602a = iog.m14219a(bjg.f2602a, obj);
                    }
                }
                obj3 = obj;
            }
            return (Executor) obj3;
        } else if (i == 1) {
            bjg bjg2 = this.f2579b;
            Object obj4 = bjg2.f2604c;
            if (obj4 instanceof iok) {
                synchronized (obj4) {
                    obj2 = bjg2.f2604c;
                    if (obj2 instanceof iok) {
                        iqk iqk = bjg2.f2605d;
                        iqk iqk2 = bjg2.f2603b;
                        if (iqk2 == null) {
                            iqk2 = new bjf(bjg2, 2);
                            bjg2.f2603b = iqk2;
                        }
                        obj2 = (ipl) iol.m14229a((Object) ips.m14309a(iqk, iqk2), "Cannot return null from a non-@Nullable @Provides method");
                        bjg2.f2604c = iog.m14219a(bjg2.f2604c, obj2);
                    }
                }
                obj4 = obj2;
            }
            return (ipl) obj4;
        } else if (i == 2) {
            bjg bjg3 = this.f2579b;
            ipk b = hld.m11682b();
            inc a = bjg3.mo2124a();
            imr b2 = bjg3.mo2125b();
            bjg3.mo2126c();
            return hto.m12121a((Object) b, (Object) inb.m14167a(a, b2));
        } else if (i == 3) {
            hto.m12124a(new ddw(), new ddy(), new dea(), new deb(), new dec(), new ddx(), new ddv[0]);
            return new ddj(bjw.m2717cP(), exn.m8328b());
        } else if (i == 4) {
            bjg bjg4 = this.f2579b;
            return ink.m14190a(bjg4.f2606e.mo2314cn(), bjg4.mo2125b(), bjg4.mo2124a(), bjg4.mo2126c());
        } else if (i != 5) {
            return new dej(this.f2579b.f2606e.mo2225au(), exn.m8328b());
        } else {
            bjg bjg5 = this.f2579b;
            Context a2 = ftz.m9622a(bjg5.f2606e.f2702a);
            fzx bF = bjg5.f2606e.mo2237bF();
            bjg5.f2606e.mo2329f();
            return efs.m7381a(a2, egc.m7397a(bF), bjg5.f2606e.mo2314cn(), bjg5.f2606e.mo2238bG(), efv.m7386a(ftz.m9622a(bjg5.f2606e.f2702a)));
        }
    }
}
