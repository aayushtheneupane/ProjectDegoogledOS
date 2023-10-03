package p000;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: cva */
/* compiled from: PG */
public final class cva implements gsw {

    /* renamed from: a */
    public final Map f5706a;

    /* renamed from: b */
    public final iel f5707b;

    /* renamed from: c */
    public final cui f5708c;

    /* renamed from: d */
    public final blw f5709d;

    /* renamed from: e */
    public final bkv f5710e;

    /* renamed from: f */
    private final inw f5711f;

    public cva(iel iel, Map map, cui cui, blw blw, bkv bkv, inw inw) {
        this.f5707b = iel;
        this.f5706a = map;
        this.f5708c = cui;
        this.f5709d = blw;
        this.f5710e = bkv;
        this.f5711f = inw;
    }

    /* renamed from: a */
    public final boolean mo3845a(boolean z, int i, String str) {
        if (!z) {
            cwn.m5510a(str, new Object[0]);
            ((cwq) this.f5711f.mo9034a()).mo3869a(i);
        }
        return z;
    }

    /* renamed from: a */
    public final ieh mo3844a() {
        cui cui = this.f5708c;
        cuh cuh = cuh.PLUGGED_IN_IDLE_WORKER;
        Iterator it = cui.f5670a.entrySet().iterator();
        while (true) {
            if (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (((AtomicBoolean) entry.getValue()).get()) {
                    new Object[1][0] = entry.getKey();
                    cwn.m5510a("GlobalJobState: Lock[%s] NOT acquired because other jobs were running.", cuh);
                    break;
                }
            } else if (!cui.mo3834a(cuh)) {
                cwn.m5510a("GlobalJobState: Lock[%s] NOT acquired because it was already taken.", cuh);
            } else {
                cuu cuu = new cuu(this);
                iel iel = this.f5707b;
                iev f = iev.m12774f();
                try {
                    f.getClass();
                    ieh a = cuu.mo2538a(new bpp(f));
                    a.mo53a(hmq.m11748a((Runnable) new bpq(f, a)), iel);
                } catch (Exception e) {
                    f.mo6952a((Throwable) e);
                }
                return f;
            }
        }
        return ife.m12820a((Object) ihg.m13029a());
    }
}
