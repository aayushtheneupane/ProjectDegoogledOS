package p000;

import java.io.Serializable;

/* renamed from: hzg */
/* compiled from: PG */
final class hzg extends hzi implements Serializable {
    public static final long serialVersionUID = 0;

    /* renamed from: a */
    private final byte[] f13677a;

    public hzg(byte[] bArr) {
        this.f13677a = (byte[]) ife.m12898e((Object) bArr);
    }

    /* renamed from: a */
    public final int mo8290a() {
        return this.f13677a.length << 3;
    }

    /* renamed from: e */
    public final byte[] mo8295e() {
        return this.f13677a;
    }

    /* renamed from: b */
    public final byte[] mo8292b() {
        return (byte[]) this.f13677a.clone();
    }

    /* renamed from: c */
    public final int mo8293c() {
        int length = this.f13677a.length;
        ife.m12877b(length >= 4, "HashCode#asInt() requires >= 4 bytes (it only has %s bytes).", length);
        byte[] bArr = this.f13677a;
        return ((bArr[3] & 255) << 24) | ((bArr[1] & 255) << 8) | (bArr[0] & 255) | ((bArr[2] & 255) << 16);
    }

    /* renamed from: d */
    public final long mo8294d() {
        int length = this.f13677a.length;
        ife.m12877b(length >= 8, "HashCode#asLong() requires >= 8 bytes (it only has %s bytes).", length);
        long j = (long) (this.f13677a[0] & 255);
        for (int i = 1; i < Math.min(this.f13677a.length, 8); i++) {
            j |= (((long) this.f13677a[i]) & 255) << (i << 3);
        }
        return j;
    }

    /* renamed from: a */
    public final boolean mo8291a(hzi hzi) {
        if (this.f13677a.length != hzi.mo8295e().length) {
            return false;
        }
        int i = 0;
        boolean z = true;
        while (true) {
            byte[] bArr = this.f13677a;
            if (i >= bArr.length) {
                return z;
            }
            z &= bArr[i] == hzi.mo8295e()[i];
            i++;
        }
    }
}
