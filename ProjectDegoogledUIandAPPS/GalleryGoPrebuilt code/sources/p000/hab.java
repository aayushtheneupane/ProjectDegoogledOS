package p000;

import java.util.Map;

/* renamed from: hab */
/* compiled from: PG */
final class hab implements gyu {

    /* renamed from: a */
    private hsu f12394a = hvb.f13454a;

    /* renamed from: b */
    private volatile Object f12395b;

    /* renamed from: c */
    private boolean f12396c = false;

    /* renamed from: d */
    private hsu f12397d;

    /* renamed from: e */
    private Object f12398e;

    private hab() {
    }

    /* renamed from: c */
    public final Object mo7196c() {
        return this.f12395b;
    }

    /* renamed from: b */
    public final void mo7194b() {
        fxk.m9830b();
        ife.m12876b(mo7193a(), (Object) "No pending values to set");
        this.f12394a = this.f12397d;
        this.f12395b = this.f12398e;
        this.f12396c = false;
    }

    /* renamed from: a */
    static hab m11103a(Map map, Object obj) {
        hab hab = new hab();
        ife.m12896d(hab.m11104c(map, obj));
        return hab;
    }

    /* renamed from: a */
    public final Object mo7192a(Object obj) {
        fxk.m9830b();
        Object a = ife.m12830a(this.f12394a.get(obj), "Unregistered experiment: %s. Registered experiments are: %s", obj, (Object) this.f12394a);
        this.f12396c = true;
        return a;
    }

    /* renamed from: a */
    public final boolean mo7193a() {
        fxk.m9830b();
        return this.f12397d != null;
    }

    /* renamed from: b */
    public final boolean mo7195b(Map map, Object obj) {
        fxk.m9830b();
        return m11104c(map, obj);
    }

    /* renamed from: c */
    private final boolean m11104c(Map map, Object obj) {
        hsu a = hsu.m12069a(map);
        if (this.f12396c) {
            this.f12397d = a;
            this.f12398e = obj;
            return false;
        }
        this.f12394a = a;
        this.f12395b = obj;
        return true;
    }
}
