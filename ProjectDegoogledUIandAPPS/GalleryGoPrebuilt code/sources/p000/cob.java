package p000;

/* renamed from: cob */
/* compiled from: PG */
public final class cob {

    /* renamed from: a */
    public final byte[] f4768a;

    /* renamed from: b */
    public final ihw f4769b;

    /* renamed from: c */
    private final Object f4770c;

    public cob(Object obj, byte[] bArr, ihw ihw) {
        this.f4770c = obj;
        this.f4768a = bArr;
        this.f4769b = ihw;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof cob) || !this.f4770c.equals(((cob) obj).f4770c)) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static cob m4677a(Object obj, ihw ihw) {
        return new cob(obj, (byte[]) null, ihw);
    }

    public final int hashCode() {
        return this.f4770c.hashCode();
    }

    public final String toString() {
        return String.format("InMemoryJpg[%s]", new Object[]{this.f4770c});
    }
}
