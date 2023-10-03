package p000;

/* renamed from: hmo */
/* compiled from: PG */
final class hmo implements hpr {

    /* renamed from: a */
    private final /* synthetic */ hlp f13043a;

    /* renamed from: b */
    private final /* synthetic */ hpr f13044b;

    public hmo(hlp hlp, hpr hpr) {
        this.f13043a = hlp;
        this.f13044b = hpr;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        hlp b = hnb.m11776b(this.f13043a);
        try {
            return this.f13044b.mo1484a(obj);
        } finally {
            hnb.m11776b(b);
        }
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13044b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14);
        sb.append("propagating=[");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }
}
