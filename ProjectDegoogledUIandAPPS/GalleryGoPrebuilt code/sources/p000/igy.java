package p000;

import java.io.FilterInputStream;
import java.io.InputStream;

/* renamed from: igy */
/* compiled from: PG */
public final class igy extends FilterInputStream {

    /* renamed from: a */
    private int f14172a;

    public igy(InputStream inputStream, int i) {
        super(inputStream);
        this.f14172a = i;
    }

    public final int available() {
        return Math.min(super.available(), this.f14172a);
    }

    public final int read() {
        if (this.f14172a <= 0) {
            return -1;
        }
        int read = super.read();
        if (read >= 0) {
            this.f14172a--;
        }
        return read;
    }

    public final int read(byte[] bArr, int i, int i2) {
        int i3 = this.f14172a;
        if (i3 <= 0) {
            return -1;
        }
        int read = super.read(bArr, i, Math.min(i2, i3));
        if (read >= 0) {
            this.f14172a -= read;
        }
        return read;
    }

    public final long skip(long j) {
        long skip = super.skip(Math.min(j, (long) this.f14172a));
        if (skip >= 0) {
            this.f14172a = (int) (((long) this.f14172a) - skip);
        }
        return skip;
    }
}
