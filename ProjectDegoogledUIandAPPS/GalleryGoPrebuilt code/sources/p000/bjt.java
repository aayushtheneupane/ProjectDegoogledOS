package p000;

import java.util.concurrent.Executor;

/* renamed from: bjt */
/* compiled from: PG */
final class bjt implements iqk {

    /* renamed from: a */
    private final int f2652a;

    /* renamed from: b */
    private final /* synthetic */ bju f2653b;

    public bjt(bju bju, int i) {
        this.f2653b = bju;
        this.f2652a = i;
    }

    /* renamed from: a */
    public final Object mo2097a() {
        Object obj;
        Object obj2;
        int i = this.f2652a;
        if (i == 0) {
            bju bju = this.f2653b;
            Object obj3 = bju.f2654a;
            if (obj3 instanceof iok) {
                synchronized (obj3) {
                    obj = bju.f2654a;
                    if (obj instanceof iok) {
                        obj = inl.m14192a(bju.f2658e.mo2329f(), bju.mo2146a());
                        bju.f2654a = iog.m14219a(bju.f2654a, obj);
                    }
                }
                obj3 = obj;
            }
            return (Executor) obj3;
        } else if (i == 1) {
            bju bju2 = this.f2653b;
            Object obj4 = bju2.f2656c;
            if (obj4 instanceof iok) {
                synchronized (obj4) {
                    obj2 = bju2.f2656c;
                    if (obj2 instanceof iok) {
                        iqk iqk = bju2.f2657d;
                        iqk iqk2 = bju2.f2655b;
                        if (iqk2 == null) {
                            iqk2 = new bjt(bju2, 2);
                            bju2.f2655b = iqk2;
                        }
                        obj2 = (ipl) iol.m14229a((Object) ips.m14309a(iqk, iqk2), "Cannot return null from a non-@Nullable @Provides method");
                        bju2.f2656c = iog.m14219a(bju2.f2656c, obj2);
                    }
                }
                obj4 = obj2;
            }
            return (ipl) obj4;
        } else if (i == 2) {
            bju bju3 = this.f2653b;
            ipk b = hld.m11682b();
            inc a = bju3.mo2146a();
            imr b2 = bju3.mo2147b();
            bju3.mo2148c();
            return hto.m12121a((Object) b, (Object) inb.m14167a(a, b2));
        } else if (i == 3) {
            return efv.m7386a(ftz.m9622a(this.f2653b.f2658e.f2702a));
        } else {
            bju bju4 = this.f2653b;
            return ink.m14190a(bju4.f2658e.mo2314cn(), bju4.mo2147b(), bju4.mo2146a(), bju4.mo2148c());
        }
    }
}
