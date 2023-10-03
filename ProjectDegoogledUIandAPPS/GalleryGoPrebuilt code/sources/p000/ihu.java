package p000;

import java.io.OutputStream;
import java.util.ArrayList;

/* renamed from: ihu */
/* compiled from: PG */
public final class ihu extends OutputStream {

    /* renamed from: a */
    public final ArrayList f14197a = new ArrayList();

    /* renamed from: b */
    public byte[] f14198b = new byte[128];

    /* renamed from: c */
    public int f14199c;

    /* renamed from: d */
    private final int f14200d = 128;

    /* renamed from: e */
    private int f14201e;

    /* renamed from: a */
    public final synchronized int mo8616a() {
        return this.f14201e + this.f14199c;
    }

    /* renamed from: a */
    private final void m13156a(int i) {
        this.f14197a.add(new iht(this.f14198b));
        int length = this.f14201e + this.f14198b.length;
        this.f14201e = length;
        this.f14198b = new byte[Math.max(this.f14200d, Math.max(i, length >>> 1))];
        this.f14199c = 0;
    }

    public final String toString() {
        return String.format("<ByteString.Output@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(mo8616a())});
    }

    public final synchronized void write(int i) {
        if (this.f14199c == this.f14198b.length) {
            m13156a(1);
        }
        byte[] bArr = this.f14198b;
        int i2 = this.f14199c;
        this.f14199c = i2 + 1;
        bArr[i2] = (byte) i;
    }

    public final synchronized void write(byte[] bArr, int i, int i2) {
        byte[] bArr2 = this.f14198b;
        int length = bArr2.length;
        int i3 = this.f14199c;
        int i4 = length - i3;
        if (i2 <= i4) {
            System.arraycopy(bArr, i, bArr2, i3, i2);
            this.f14199c += i2;
            return;
        }
        System.arraycopy(bArr, i, bArr2, i3, i4);
        int i5 = i2 - i4;
        m13156a(i5);
        System.arraycopy(bArr, i + i4, this.f14198b, 0, i5);
        this.f14199c = i5;
    }
}
