package p000;

import java.io.FilterInputStream;
import java.io.InputStream;

/* renamed from: aro */
/* compiled from: PG */
public final class aro extends FilterInputStream {

    /* renamed from: a */
    private static final byte[] f1489a = {-1, -31, 0, 28, 69, 120, 105, 102, 0, 0, 77, 77, 0, 0, 0, 0, 0, 8, 0, 1, 1, 18, 0, 2, 0, 0, 0, 1, 0};

    /* renamed from: b */
    private final byte f1490b;

    /* renamed from: c */
    private int f1491c;

    public final boolean markSupported() {
        return false;
    }

    public aro(InputStream inputStream, int i) {
        super(inputStream);
        if (i < -1 || i > 8) {
            StringBuilder sb = new StringBuilder(43);
            sb.append("Cannot add invalid orientation: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        this.f1490b = (byte) i;
    }

    public final void mark(int i) {
        throw new UnsupportedOperationException();
    }

    public final int read() {
        int i;
        int i2 = this.f1491c;
        if (i2 >= 2 && i2 <= 31) {
            i = i2 != 31 ? f1489a[i2 - 2] & 255 : this.f1490b;
        } else {
            i = super.read();
        }
        if (i != -1) {
            this.f1491c++;
        }
        return i;
    }

    public final int read(byte[] bArr, int i, int i2) {
        int i3;
        int i4 = this.f1491c;
        if (i4 > 31) {
            i3 = super.read(bArr, i, i2);
        } else if (i4 == 31) {
            bArr[i] = this.f1490b;
            i3 = 1;
        } else if (i4 >= 2) {
            int min = Math.min(31 - i4, i2);
            System.arraycopy(f1489a, this.f1491c - 2, bArr, i, min);
            i3 = min;
        } else {
            i3 = super.read(bArr, i, 2 - i4);
        }
        if (i3 > 0) {
            this.f1491c += i3;
        }
        return i3;
    }

    public final void reset() {
        throw new UnsupportedOperationException();
    }

    public final long skip(long j) {
        long skip = super.skip(j);
        if (skip > 0) {
            this.f1491c = (int) (((long) this.f1491c) + skip);
        }
        return skip;
    }
}
