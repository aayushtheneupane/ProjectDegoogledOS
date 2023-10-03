package p000;

/* renamed from: iog */
/* compiled from: PG */
public final class iog implements iqk, inw {

    /* renamed from: a */
    private static final Object f14596a = new Object();

    /* renamed from: b */
    private volatile iqk f14597b;

    /* renamed from: c */
    private volatile Object f14598c = f14596a;

    private iog(iqk iqk) {
        this.f14597b = iqk;
    }

    /* renamed from: a */
    public final Object mo2097a() {
        Object obj = this.f14598c;
        if (obj == f14596a) {
            synchronized (this) {
                obj = this.f14598c;
                if (obj == f14596a) {
                    obj = this.f14597b.mo2097a();
                    this.f14598c = m14219a(this.f14598c, obj);
                    this.f14597b = null;
                }
            }
        }
        return obj;
    }

    /* renamed from: a */
    public static inw m14218a(iqk iqk) {
        if (!(iqk instanceof inw)) {
            return new iog((iqk) iol.m14228a((Object) iqk));
        }
        return (inw) iqk;
    }

    /* renamed from: a */
    public static Object m14219a(Object obj, Object obj2) {
        if (obj == f14596a || (obj instanceof iok) || obj == obj2) {
            return obj2;
        }
        String valueOf = String.valueOf(obj);
        String valueOf2 = String.valueOf(obj2);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 118 + String.valueOf(valueOf2).length());
        sb.append("Scoped provider was invoked recursively returning different results: ");
        sb.append(valueOf);
        sb.append(" & ");
        sb.append(valueOf2);
        sb.append(". This is likely due to a circular dependency.");
        throw new IllegalStateException(sb.toString());
    }
}
