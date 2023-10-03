package p000;

import com.google.android.apps.photosgo.face.cluster.ClusterManager;
import java.util.concurrent.Executor;

/* renamed from: biz */
/* compiled from: PG */
final class biz implements iqk {

    /* renamed from: a */
    private final int f2515a;

    /* renamed from: b */
    private final /* synthetic */ bja f2516b;

    public biz(bja bja, int i) {
        this.f2516b = bja;
        this.f2515a = i;
    }

    /* renamed from: a */
    public final Object mo2097a() {
        Object obj;
        Object obj2;
        Object obj3;
        int i = this.f2515a;
        if (i == 0) {
            bja bja = this.f2516b;
            Object obj4 = bja.f2519a;
            if (obj4 instanceof iok) {
                synchronized (obj4) {
                    obj = bja.f2519a;
                    if (obj instanceof iok) {
                        obj = inl.m14192a(bja.f2524f.mo2329f(), bja.mo2107a());
                        bja.f2519a = iog.m14219a(bja.f2519a, obj);
                    }
                }
                obj4 = obj;
            }
            return (Executor) obj4;
        } else if (i == 1) {
            bja bja2 = this.f2516b;
            Object obj5 = bja2.f2522d;
            if (obj5 instanceof iok) {
                synchronized (obj5) {
                    obj2 = bja2.f2522d;
                    if (obj2 instanceof iok) {
                        iqk iqk = bja2.f2523e;
                        iqk iqk2 = bja2.f2521c;
                        if (iqk2 == null) {
                            iqk2 = new biz(bja2, 2);
                            bja2.f2521c = iqk2;
                        }
                        obj2 = (ipl) iol.m14229a((Object) ips.m14309a(iqk, iqk2), "Cannot return null from a non-@Nullable @Provides method");
                        bja2.f2522d = iog.m14219a(bja2.f2522d, obj2);
                    }
                }
                obj5 = obj2;
            }
            return (ipl) obj5;
        } else if (i != 2) {
            bja bja3 = this.f2516b;
            new ClusterManager(bja3.f2524f.mo2229ay());
            return new cfj(bja3.f2524f.mo2201aW(), bja3.f2524f.mo2314cn());
        } else {
            bja bja4 = this.f2516b;
            ipk b = hld.m11682b();
            inc a = bja4.mo2107a();
            imr b2 = bja4.mo2108b();
            Object obj6 = bja4.f2520b;
            if (obj6 instanceof iok) {
                synchronized (obj6) {
                    obj3 = bja4.f2520b;
                    if (obj3 instanceof iok) {
                        obj3 = Long.valueOf(bja4.mo2108b().mo9002a());
                        bja4.f2520b = iog.m14219a(bja4.f2520b, obj3);
                    }
                }
                obj6 = obj3;
            }
            ((Long) obj6).longValue();
            return hto.m12121a((Object) b, (Object) inb.m14167a(a, b2));
        }
    }
}
