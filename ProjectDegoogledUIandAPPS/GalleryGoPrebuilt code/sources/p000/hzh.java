package p000;

import java.io.Serializable;

/* renamed from: hzh */
/* compiled from: PG */
final class hzh extends hzi implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final int f13678a;

    public hzh(int i) {
        this.f13678a = i;
    }

    /* renamed from: a */
    public final int mo8290a() {
        return 32;
    }

    /* renamed from: c */
    public final int mo8293c() {
        return this.f13678a;
    }

    /* renamed from: b */
    public final byte[] mo8292b() {
        int i = this.f13678a;
        return new byte[]{(byte) i, (byte) (i >> 8), (byte) (i >> 16), (byte) (i >> 24)};
    }

    /* renamed from: d */
    public final long mo8294d() {
        throw new IllegalStateException("this HashCode only has 32 bits; cannot create a long");
    }

    /* renamed from: a */
    public final boolean mo8291a(hzi hzi) {
        return this.f13678a == hzi.mo8293c();
    }
}
