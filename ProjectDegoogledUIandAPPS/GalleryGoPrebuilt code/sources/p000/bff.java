package p000;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: bff */
/* compiled from: PG */
public final class bff extends FilterInputStream {

    /* renamed from: a */
    private final long f2202a;

    /* renamed from: b */
    private int f2203b;

    public bff(InputStream inputStream, long j) {
        super(inputStream);
        this.f2202a = j;
    }

    public final synchronized int available() {
        return (int) Math.max(this.f2202a - ((long) this.f2203b), (long) this.in.available());
    }

    /* renamed from: a */
    private final int m2410a(int i) {
        if (i < 0) {
            long j = this.f2202a;
            int i2 = this.f2203b;
            if (j - ((long) i2) > 0) {
                StringBuilder sb = new StringBuilder(87);
                sb.append("Failed to read all expected data, expected: ");
                sb.append(j);
                sb.append(", but read: ");
                sb.append(i2);
                throw new IOException(sb.toString());
            }
        } else {
            this.f2203b += i;
        }
        return i;
    }

    public final synchronized int read() {
        int read;
        int i;
        read = super.read();
        if (read >= 0) {
            i = 1;
        } else {
            i = -1;
        }
        m2410a(i);
        return read;
    }

    public final int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    public final synchronized int read(byte[] bArr, int i, int i2) {
        return m2410a(super.read(bArr, i, i2));
    }
}
