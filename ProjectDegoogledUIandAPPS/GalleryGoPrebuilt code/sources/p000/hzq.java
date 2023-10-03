package p000;

import java.nio.charset.Charset;

/* renamed from: hzq */
/* compiled from: PG */
final class hzq extends hzf {

    /* renamed from: a */
    private int f13692a;

    /* renamed from: b */
    private long f13693b;

    /* renamed from: c */
    private int f13694c;

    /* renamed from: d */
    private int f13695d = 0;

    /* renamed from: e */
    private boolean f13696e = false;

    public hzq(int i) {
        this.f13692a = i;
    }

    /* renamed from: a */
    public final hzi mo8300a() {
        ife.m12896d(!this.f13696e);
        this.f13696e = true;
        int a = this.f13692a ^ hzr.m12541a((int) this.f13693b);
        this.f13692a = a;
        return hzr.m12547b(a, this.f13695d);
    }

    /* renamed from: a */
    public final hzk mo8284a(byte[] bArr, int i) {
        int i2 = 0;
        ife.m12874b(0, i, bArr.length);
        while (true) {
            int i3 = i2 + 4;
            if (i3 > i) {
                break;
            }
            m12537a(4, (long) hzr.m12544b(bArr, i2));
            i2 = i3;
        }
        while (i2 < i) {
            m12537a(1, (long) (bArr[i2] & 255));
            i2++;
        }
        return this;
    }

    /* renamed from: a */
    public final hzk mo8289a(CharSequence charSequence, Charset charset) {
        if (!hpo.f13229a.equals(charset)) {
            return super.mo8289a(charSequence, charset);
        }
        int length = charSequence.length();
        int i = 0;
        while (true) {
            int i2 = i + 4;
            if (i2 > length) {
                break;
            }
            char charAt = charSequence.charAt(i);
            char charAt2 = charSequence.charAt(i + 1);
            char charAt3 = charSequence.charAt(i + 2);
            char charAt4 = charSequence.charAt(i + 3);
            if (charAt >= 128 || charAt2 >= 128 || charAt3 >= 128 || charAt4 >= 128) {
                break;
            }
            m12537a(4, (long) ((charAt2 << 8) | charAt | (charAt3 << 16) | (charAt4 << 24)));
            i = i2;
        }
        while (i < length) {
            char charAt5 = charSequence.charAt(i);
            if (charAt5 < 128) {
                m12537a(1, (long) charAt5);
            } else if (charAt5 < 2048) {
                m12537a(2, hzr.m12545b(charAt5));
            } else if (charAt5 < 55296 || charAt5 > 57343) {
                m12537a(3, hzr.m12543a(charAt5));
            } else {
                int codePointAt = Character.codePointAt(charSequence, i);
                if (codePointAt != charAt5) {
                    i++;
                    m12537a(4, hzr.m12546b(codePointAt));
                } else {
                    mo8283a(charSequence.subSequence(i, length).toString().getBytes(charset));
                    return this;
                }
            }
            i++;
        }
        return this;
    }

    /* renamed from: a */
    private final void m12537a(int i, long j) {
        long j2 = this.f13693b;
        int i2 = this.f13694c;
        long j3 = ((j & 4294967295L) << i2) | j2;
        this.f13693b = j3;
        int i3 = i2 + (i << 3);
        this.f13694c = i3;
        this.f13695d += i;
        if (i3 >= 32) {
            this.f13692a = hzr.m12542a(this.f13692a, hzr.m12541a((int) j3));
            this.f13693b >>>= 32;
            this.f13694c -= 32;
        }
    }
}
