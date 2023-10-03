package p000;

import java.util.Map;

/* renamed from: gxr */
/* compiled from: PG */
final class gxr implements gyu {

    /* renamed from: a */
    public volatile Object f12270a;

    /* renamed from: b */
    private C0309lf f12271b = new C0309lf();

    /* renamed from: c */
    private C0309lf f12272c;

    /* renamed from: d */
    private Object f12273d;

    private gxr() {
    }

    /* renamed from: a */
    public final boolean mo7193a() {
        return this.f12272c != null;
    }

    /* renamed from: c */
    public final Object mo7196c() {
        return this.f12270a;
    }

    /* renamed from: b */
    public final void mo7194b() {
        ife.m12876b(mo7193a(), (Object) "No pending values to set");
        this.f12271b = this.f12272c;
        this.f12270a = this.f12273d;
    }

    /* renamed from: a */
    static gxr m11010a(Map map, Object obj) {
        gxr gxr = new gxr();
        ife.m12896d(gxr.mo7195b(map, obj));
        return gxr;
    }

    /* renamed from: a */
    public final Object mo7192a(Object obj) {
        gxq gxq = (gxq) ife.m12830a((Object) (gxq) this.f12271b.get(obj), "Unregistered experiment: %s. Registered experiments are: %s", obj, (Object) this.f12271b);
        gxq.f12269b = true;
        return gxq.f12268a;
    }

    /* renamed from: b */
    public final boolean mo7195b(Map map, Object obj) {
        int i = 0;
        while (true) {
            C0309lf lfVar = this.f12271b;
            if (i < lfVar.f15193b) {
                Object b = lfVar.mo9320b(i);
                Object obj2 = map.get(b);
                ife.m12829a(obj2, "New experiment config is missing a value we previously had: %s", b);
                gxq gxq = (gxq) this.f12271b.mo9321c(i);
                if (!gxq.f12268a.equals(obj2) && gxq.f12269b) {
                    C0309lf lfVar2 = new C0309lf(map.size());
                    for (Map.Entry entry : map.entrySet()) {
                        lfVar2.put(entry.getKey(), new gxq(entry.getValue()));
                    }
                    this.f12272c = lfVar2;
                    this.f12273d = obj;
                    return false;
                }
                i++;
            } else {
                for (Map.Entry entry2 : map.entrySet()) {
                    Object key = entry2.getKey();
                    gxq gxq2 = (gxq) this.f12271b.get(key);
                    if (gxq2 == null) {
                        this.f12271b.put(key, new gxq(entry2.getValue()));
                    } else {
                        gxq2.f12268a = entry2.getValue();
                    }
                }
                this.f12270a = obj;
                this.f12272c = null;
                this.f12273d = null;
                return true;
            }
        }
    }
}
