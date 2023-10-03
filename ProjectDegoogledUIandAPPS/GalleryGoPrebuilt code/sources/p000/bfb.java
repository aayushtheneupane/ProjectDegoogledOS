package p000;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* renamed from: bfb */
/* compiled from: PG */
final class bfb extends InputStream {

    /* renamed from: a */
    private final ByteBuffer f2195a;

    /* renamed from: b */
    private int f2196b = -1;

    public bfb(ByteBuffer byteBuffer) {
        this.f2195a = byteBuffer;
    }

    public final boolean markSupported() {
        return true;
    }

    public final int available() {
        return this.f2195a.remaining();
    }

    public final synchronized void mark(int i) {
        this.f2196b = this.f2195a.position();
    }

    public final int read() {
        if (this.f2195a.hasRemaining()) {
            return this.f2195a.get() & 255;
        }
        return -1;
    }

    public final int read(byte[] bArr, int i, int i2) {
        if (!this.f2195a.hasRemaining()) {
            return -1;
        }
        int min = Math.min(i2, available());
        this.f2195a.get(bArr, i, min);
        return min;
    }

    public final synchronized void reset() {
        int i = this.f2196b;
        if (i != -1) {
            this.f2195a.position(i);
        } else {
            throw new IOException("Cannot reset to unset mark position");
        }
    }

    public final long skip(long j) {
        if (!this.f2195a.hasRemaining()) {
            return -1;
        }
        long min = Math.min(j, (long) available());
        ByteBuffer byteBuffer = this.f2195a;
        byteBuffer.position((int) (((long) byteBuffer.position()) + min));
        return min;
    }
}
