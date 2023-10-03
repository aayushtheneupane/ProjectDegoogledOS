package p000;

import java.io.InputStream;

/* renamed from: azt */
/* compiled from: PG */
final class azt implements azs {

    /* renamed from: a */
    public final InputStream f1914a;

    public azt(InputStream inputStream) {
        this.f1914a = inputStream;
    }

    /* renamed from: b */
    public final int mo1743b() {
        return (mo1742a() << 8) | mo1742a();
    }

    /* renamed from: a */
    public final short mo1742a() {
        int read = this.f1914a.read();
        if (read != -1) {
            return (short) read;
        }
        throw new azr();
    }

    /* renamed from: a */
    public final long mo1741a(long j) {
        if (j < 0) {
            return 0;
        }
        long j2 = j;
        while (j2 > 0) {
            long skip = this.f1914a.skip(j2);
            if (skip <= 0) {
                if (this.f1914a.read() == -1) {
                    break;
                }
                j2--;
            } else {
                j2 -= skip;
            }
        }
        return j - j2;
    }
}
