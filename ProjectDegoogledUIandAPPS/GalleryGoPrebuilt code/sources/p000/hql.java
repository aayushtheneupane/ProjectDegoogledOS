package p000;

import java.io.Serializable;

/* renamed from: hql */
/* compiled from: PG */
public final class hql implements Serializable, hqk {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final hqk f13261a;

    /* renamed from: b */
    private volatile transient boolean f13262b;

    /* renamed from: c */
    private transient Object f13263c;

    public hql(hqk hqk) {
        this.f13261a = (hqk) ife.m12898e((Object) hqk);
    }

    /* renamed from: a */
    public final Object mo2652a() {
        if (!this.f13262b) {
            synchronized (this) {
                if (!this.f13262b) {
                    Object a = this.f13261a.mo2652a();
                    this.f13263c = a;
                    this.f13262b = true;
                    return a;
                }
            }
        }
        return this.f13263c;
    }

    public final String toString() {
        Object obj;
        if (this.f13262b) {
            String valueOf = String.valueOf(this.f13263c);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25);
            sb.append("<supplier that returned ");
            sb.append(valueOf);
            sb.append(">");
            obj = sb.toString();
        } else {
            obj = this.f13261a;
        }
        String valueOf2 = String.valueOf(obj);
        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 19);
        sb2.append("Suppliers.memoize(");
        sb2.append(valueOf2);
        sb2.append(")");
        return sb2.toString();
    }
}
