package p000;

import java.security.MessageDigest;

/* renamed from: bfa */
/* compiled from: PG */
public final class bfa implements aqu {

    /* renamed from: b */
    private final Object f2194b;

    public bfa(Object obj) {
        this.f2194b = cns.m4632a(obj);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof bfa) {
            return this.f2194b.equals(((bfa) obj).f2194b);
        }
        return false;
    }

    public final int hashCode() {
        return this.f2194b.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f2194b);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18);
        sb.append("ObjectKey{object=");
        sb.append(valueOf);
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        messageDigest.update(this.f2194b.toString().getBytes(f1466a));
    }
}
