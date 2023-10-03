package p000;

/* renamed from: hqm */
/* compiled from: PG */
public final class hqm implements hqk {

    /* renamed from: a */
    private volatile hqk f13264a;

    /* renamed from: b */
    private volatile boolean f13265b;

    /* renamed from: c */
    private Object f13266c;

    public hqm(hqk hqk) {
        this.f13264a = (hqk) ife.m12898e((Object) hqk);
    }

    /* renamed from: a */
    public final Object mo2652a() {
        if (!this.f13265b) {
            synchronized (this) {
                if (!this.f13265b) {
                    Object a = this.f13264a.mo2652a();
                    this.f13266c = a;
                    this.f13265b = true;
                    this.f13264a = null;
                    return a;
                }
            }
        }
        return this.f13266c;
    }

    public final String toString() {
        Object obj = this.f13264a;
        if (obj == null) {
            String valueOf = String.valueOf(this.f13266c);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25);
            sb.append("<supplier that returned ");
            sb.append(valueOf);
            sb.append(">");
            obj = sb.toString();
        }
        String valueOf2 = String.valueOf(obj);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 19);
        sb2.append("Suppliers.memoize(");
        sb2.append(valueOf2);
        sb2.append(")");
        return sb2.toString();
    }
}
