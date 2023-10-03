package p000;

import android.support.p002v7.widget.RecyclerView;
import java.io.FilterInputStream;
import java.io.InputStream;

/* renamed from: bfm */
/* compiled from: PG */
public final class bfm extends FilterInputStream {

    /* renamed from: a */
    private int f2214a = RecyclerView.UNDEFINED_DURATION;

    public bfm(InputStream inputStream) {
        super(inputStream);
    }

    /* renamed from: a */
    private final long m2421a(long j) {
        int i = this.f2214a;
        if (i == 0) {
            return -1;
        }
        if (i != Integer.MIN_VALUE) {
            long j2 = (long) i;
            if (j > j2) {
                return j2;
            }
        }
        return j;
    }

    public final int available() {
        int i = this.f2214a;
        if (i != Integer.MIN_VALUE) {
            return Math.min(i, super.available());
        }
        return super.available();
    }

    public final synchronized void mark(int i) {
        super.mark(i);
        this.f2214a = i;
    }

    public final int read() {
        if (m2421a(1) == -1) {
            return -1;
        }
        int read = super.read();
        m2422b(1);
        return read;
    }

    public final int read(byte[] bArr, int i, int i2) {
        int a = (int) m2421a((long) i2);
        if (a == -1) {
            return -1;
        }
        int read = super.read(bArr, i, a);
        m2422b((long) read);
        return read;
    }

    public final synchronized void reset() {
        super.reset();
        this.f2214a = RecyclerView.UNDEFINED_DURATION;
    }

    public final long skip(long j) {
        long a = m2421a(j);
        if (a == -1) {
            return 0;
        }
        long skip = super.skip(a);
        m2422b(skip);
        return skip;
    }

    /* renamed from: b */
    private final void m2422b(long j) {
        int i = this.f2214a;
        if (i != Integer.MIN_VALUE && j != -1) {
            this.f2214a = (int) (((long) i) - j);
        }
    }
}
