package p000;

import java.util.Arrays;

/* renamed from: eln */
/* compiled from: PG */
public final class eln {

    /* renamed from: a */
    public final ekn f8511a;

    /* renamed from: b */
    private final int f8512b;

    private eln(ekn ekn) {
        this.f8511a = ekn;
        this.f8512b = Arrays.hashCode(new Object[]{ekn, null});
    }

    public final int hashCode() {
        return this.f8512b;
    }

    public final boolean equals(Object obj) {
        if (obj != this) {
            return (obj instanceof eln) && C0652xy.m16068a((Object) this.f8511a, (Object) ((eln) obj).f8511a) && C0652xy.m16068a((Object) null, (Object) null);
        }
        return true;
    }

    /* renamed from: a */
    public static eln m7737a(ekn ekn) {
        return new eln(ekn);
    }
}
