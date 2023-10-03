package p000;

import java.util.concurrent.Executor;

/* renamed from: bjc */
/* compiled from: PG */
final class bjc implements iqk {

    /* renamed from: a */
    private final int f2543a;

    /* renamed from: b */
    private final /* synthetic */ bjd f2544b;

    public bjc(bjd bjd, int i) {
        this.f2544b = bjd;
        this.f2543a = i;
    }

    /* renamed from: a */
    public final Object mo2097a() {
        Object obj;
        Object obj2;
        int i = this.f2543a;
        if (i == 0) {
            bjd bjd = this.f2544b;
            Object obj3 = bjd.f2545a;
            if (obj3 instanceof iok) {
                synchronized (obj3) {
                    obj = bjd.f2545a;
                    if (obj instanceof iok) {
                        obj = inl.m14192a(bjd.f2549e.mo2329f(), bjd.mo2112a());
                        bjd.f2545a = iog.m14219a(bjd.f2545a, obj);
                    }
                }
                obj3 = obj;
            }
            return (Executor) obj3;
        } else if (i == 1) {
            bjd bjd2 = this.f2544b;
            Object obj4 = bjd2.f2547c;
            if (obj4 instanceof iok) {
                synchronized (obj4) {
                    obj2 = bjd2.f2547c;
                    if (obj2 instanceof iok) {
                        iqk iqk = bjd2.f2548d;
                        iqk iqk2 = bjd2.f2546b;
                        if (iqk2 == null) {
                            iqk2 = new bjc(bjd2, 2);
                            bjd2.f2546b = iqk2;
                        }
                        obj2 = (ipl) iol.m14229a((Object) ips.m14309a(iqk, iqk2), "Cannot return null from a non-@Nullable @Provides method");
                        bjd2.f2547c = iog.m14219a(bjd2.f2547c, obj2);
                    }
                }
                obj4 = obj2;
            }
            return (ipl) obj4;
        } else if (i != 2) {
            bjd bjd3 = this.f2544b;
            return ink.m14190a(bjd3.f2549e.mo2314cn(), bjd3.mo2113b(), bjd3.mo2112a(), bjd3.mo2114c());
        } else {
            bjd bjd4 = this.f2544b;
            ipk b = hld.m11682b();
            inc a = bjd4.mo2112a();
            imr b2 = bjd4.mo2113b();
            bjd4.mo2114c();
            return hto.m12121a((Object) b, (Object) inb.m14167a(a, b2));
        }
    }
}
