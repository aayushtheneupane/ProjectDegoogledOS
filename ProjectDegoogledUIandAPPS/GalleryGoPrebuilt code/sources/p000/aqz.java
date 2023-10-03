package p000;

import java.security.MessageDigest;

/* renamed from: aqz */
/* compiled from: PG */
public final class aqz implements aqu {

    /* renamed from: b */
    private final C0290kn f1473b = new bfe();

    public final boolean equals(Object obj) {
        if (obj instanceof aqz) {
            return this.f1473b.equals(((aqz) obj).f1473b);
        }
        return false;
    }

    /* renamed from: a */
    public final Object mo1502a(aqy aqy) {
        return this.f1473b.containsKey(aqy) ? this.f1473b.get(aqy) : aqy.f1469b;
    }

    public final int hashCode() {
        return this.f1473b.hashCode();
    }

    /* renamed from: a */
    public final void mo1504a(aqz aqz) {
        this.f1473b.mo1933a((C0309lf) aqz.f1473b);
    }

    /* renamed from: a */
    public final void mo1503a(aqy aqy, Object obj) {
        this.f1473b.put(aqy, obj);
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f1473b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 16);
        sb.append("Options{values=");
        sb.append(valueOf);
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        int i = 0;
        while (true) {
            C0290kn knVar = this.f1473b;
            if (i < knVar.f15193b) {
                aqy aqy = (aqy) knVar.mo9320b(i);
                Object c = this.f1473b.mo9321c(i);
                aqx aqx = aqy.f1470c;
                if (aqy.f1472e == null) {
                    aqy.f1472e = aqy.f1471d.getBytes(aqu.f1466a);
                }
                aqx.mo1498a(aqy.f1472e, c, messageDigest);
                i++;
            } else {
                return;
            }
        }
    }
}
