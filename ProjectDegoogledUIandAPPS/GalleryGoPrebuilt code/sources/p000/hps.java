package p000;

import java.io.Serializable;
import java.util.Arrays;

/* renamed from: hps */
/* compiled from: PG */
final class hps extends hpq implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final hpr f13231a;

    /* renamed from: b */
    private final hpq f13232b;

    public hps(hpr hpr, hpq hpq) {
        this.f13231a = (hpr) ife.m12898e((Object) hpr);
        this.f13232b = (hpq) ife.m12898e((Object) hpq);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final boolean mo4294a(Object obj, Object obj2) {
        return this.f13232b.mo7661b(this.f13231a.mo1484a(obj), this.f13231a.mo1484a(obj2));
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof hps) {
            hps hps = (hps) obj;
            return this.f13231a.equals(hps.f13231a) && this.f13232b.equals(hps.f13232b);
        }
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f13231a, this.f13232b});
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f13232b);
        String valueOf2 = String.valueOf(this.f13231a);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 13 + String.valueOf(valueOf2).length());
        sb.append(valueOf);
        sb.append(".onResultOf(");
        sb.append(valueOf2);
        sb.append(")");
        return sb.toString();
    }
}
