package p000;

import java.io.Serializable;
import java.nio.charset.Charset;

/* renamed from: hzr */
/* compiled from: PG */
final class hzr extends hze implements Serializable {

    /* renamed from: a */
    public static final hzj f13697a = new hzr(0);
    public static final long serialVersionUID = 0;

    /* renamed from: b */
    private final int f13698b;

    static {
        new hzr(hzm.f13681a);
    }

    /* renamed from: a */
    public static long m12543a(char c) {
        return (long) ((((c & '?') | 128) << 16) | (((c >>> 12) | 480) & 255) | ((((c >>> 6) & 63) | 128) << 8));
    }

    /* renamed from: b */
    public static long m12545b(char c) {
        return (long) ((((c & '?') | 128) << 8) | (((c >>> 6) | 960) & 255));
    }

    /* renamed from: b */
    public static long m12546b(int i) {
        return ((((long) (i >>> 18)) | 240) & 255) | ((((long) ((i >>> 12) & 63)) | 128) << 8) | ((((long) ((i >>> 6) & 63)) | 128) << 16) | ((((long) (i & 63)) | 128) << 24);
    }

    private hzr(int i) {
        this.f13698b = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof hzr) || this.f13698b != ((hzr) obj).f13698b) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public static hzi m12547b(int i, int i2) {
        int i3 = i ^ i2;
        int i4 = (i3 ^ (i3 >>> 16)) * -2048144789;
        int i5 = (i4 ^ (i4 >>> 13)) * -1028477387;
        return hzi.m12515a(i5 ^ (i5 >>> 16));
    }

    /* renamed from: b */
    public static int m12544b(byte[] bArr, int i) {
        return (bArr[i] & 255) | (bArr[i + 3] << 24) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 1] & 255) << 8);
    }

    /* renamed from: a */
    public final hzi mo8288a(byte[] bArr, int i) {
        int i2 = 0;
        ife.m12874b(0, i, bArr.length);
        int i3 = this.f13698b;
        int i4 = 0;
        while (true) {
            int i5 = i4 + 4;
            if (i5 > i) {
                break;
            }
            i3 = m12542a(i3, m12541a(m12544b(bArr, i4)));
            i4 = i5;
        }
        int i6 = i4;
        int i7 = 0;
        while (i6 < i) {
            i2 ^= (bArr[i6] & 255) << i7;
            i6++;
            i7 += 8;
        }
        return m12547b(m12541a(i2) ^ i3, i);
    }

    public final int hashCode() {
        return getClass().hashCode() ^ this.f13698b;
    }

    /* renamed from: a */
    public final hzi mo8286a(CharSequence charSequence, Charset charset) {
        int i;
        long j;
        if (!hpo.f13229a.equals(charset)) {
            return mo8287a(charSequence.toString().getBytes(charset));
        }
        int length = charSequence.length();
        int i2 = this.f13698b;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            int i6 = i4 + 4;
            j = 0;
            if (i6 <= length) {
                char charAt = charSequence.charAt(i4);
                char charAt2 = charSequence.charAt(i4 + 1);
                char charAt3 = charSequence.charAt(i4 + 2);
                char charAt4 = charSequence.charAt(i4 + 3);
                if (charAt >= 128 || charAt2 >= 128 || charAt3 >= 128 || charAt4 >= 128) {
                    break;
                }
                i2 = m12542a(i2, m12541a((int) (charAt2 << 8) | charAt | (charAt3 << 16) | (charAt4 << 24)));
                i5 = i + 4;
                i4 = i6;
            } else {
                break;
            }
        }
        while (i4 < length) {
            char charAt5 = charSequence.charAt(i4);
            if (charAt5 < 128) {
                j |= ((long) charAt5) << i3;
                i3 += 8;
                i++;
            } else if (charAt5 < 2048) {
                j |= m12545b(charAt5) << i3;
                i3 += 16;
                i += 2;
            } else if (charAt5 >= 55296 && charAt5 <= 57343) {
                int codePointAt = Character.codePointAt(charSequence, i4);
                if (codePointAt == charAt5) {
                    return mo8287a(charSequence.toString().getBytes(charset));
                }
                i4++;
                j |= m12546b(codePointAt) << i3;
                i += 4;
            } else {
                j |= m12543a(charAt5) << i3;
                i3 += 24;
                i += 3;
            }
            if (i3 >= 32) {
                i2 = m12542a(i2, m12541a((int) j));
                i3 -= 32;
                j >>>= 32;
            }
            i4++;
        }
        return m12547b(m12541a((int) j) ^ i2, i);
    }

    /* renamed from: a */
    public static int m12542a(int i, int i2) {
        return (Integer.rotateLeft(i ^ i2, 13) * 5) - 430675100;
    }

    /* renamed from: a */
    public static int m12541a(int i) {
        return Integer.rotateLeft(i * -862048943, 15) * 461845907;
    }

    /* renamed from: a */
    public final hzk mo8299a() {
        return new hzq(this.f13698b);
    }

    public final String toString() {
        int i = this.f13698b;
        StringBuilder sb = new StringBuilder(31);
        sb.append("Hashing.murmur3_32(");
        sb.append(i);
        sb.append(")");
        return sb.toString();
    }
}
