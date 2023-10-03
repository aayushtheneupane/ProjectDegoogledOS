package p000;

/* renamed from: cub */
/* compiled from: PG */
final /* synthetic */ class cub implements hpr {

    /* renamed from: a */
    private final Object f5659a;

    public cub(Object obj) {
        this.f5659a = obj;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Object obj2 = this.f5659a;
        boolean z = true;
        if (obj != null && obj2 != null) {
            return Boolean.valueOf(!obj.equals(obj2));
        }
        if (obj == obj2) {
            z = false;
        }
        return Boolean.valueOf(z);
    }
}
