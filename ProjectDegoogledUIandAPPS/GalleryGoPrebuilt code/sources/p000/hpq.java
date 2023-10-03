package p000;

/* renamed from: hpq */
/* compiled from: PG */
public abstract class hpq {
    protected hpq() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract boolean mo4294a(Object obj, Object obj2);

    /* renamed from: b */
    public final boolean mo7661b(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return mo4294a(obj, obj2);
    }

    /* renamed from: a */
    public final hpq mo7660a(hpr hpr) {
        return new hps(hpr, this);
    }
}
