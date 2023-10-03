package p000;

import java.util.concurrent.ExecutionException;

/* renamed from: ion */
/* compiled from: PG */
public final class ion extends iop {

    /* renamed from: a */
    private final Throwable f14603a;

    public /* synthetic */ ion(Throwable th) {
        super((byte[]) null);
        this.f14603a = (Throwable) ife.m12898e((Object) th);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ion) {
            return this.f14603a.equals(((ion) obj).f14603a);
        }
        return false;
    }

    /* renamed from: a */
    public final Object mo9037a() {
        throw new ExecutionException(this.f14603a);
    }

    public final int hashCode() {
        return this.f14603a.hashCode();
    }

    public final String toString() {
        String canonicalName = this.f14603a.getClass().getCanonicalName();
        StringBuilder sb = new StringBuilder(String.valueOf(canonicalName).length() + 22);
        sb.append("Produced[failed with ");
        sb.append(canonicalName);
        sb.append("]");
        return sb.toString();
    }
}
