package p000;

/* renamed from: hzi */
/* compiled from: PG */
public abstract class hzi {

    /* renamed from: a */
    private static final char[] f13679a = "0123456789abcdef".toCharArray();

    /* renamed from: a */
    public abstract int mo8290a();

    /* renamed from: a */
    public abstract boolean mo8291a(hzi hzi);

    /* renamed from: b */
    public abstract byte[] mo8292b();

    /* renamed from: c */
    public abstract int mo8293c();

    /* renamed from: d */
    public abstract long mo8294d();

    public final boolean equals(Object obj) {
        if (obj instanceof hzi) {
            hzi hzi = (hzi) obj;
            if (mo8290a() != hzi.mo8290a() || !mo8291a(hzi)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    static hzi m12516a(byte[] bArr) {
        return new hzg(bArr);
    }

    /* renamed from: a */
    public static hzi m12515a(int i) {
        return new hzh(i);
    }

    /* renamed from: e */
    public byte[] mo8295e() {
        return mo8292b();
    }

    public final int hashCode() {
        if (mo8290a() >= 32) {
            return mo8293c();
        }
        byte[] e = mo8295e();
        byte b = e[0] & 255;
        for (int i = 1; i < e.length; i++) {
            b |= (e[i] & 255) << (i << 3);
        }
        return b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(r2 + r2);
        for (byte b : mo8295e()) {
            sb.append(f13679a[(b >> 4) & 15]);
            sb.append(f13679a[b & 15]);
        }
        return sb.toString();
    }
}
