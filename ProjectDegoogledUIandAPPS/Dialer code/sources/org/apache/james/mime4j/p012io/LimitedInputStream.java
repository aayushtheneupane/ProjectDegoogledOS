package org.apache.james.mime4j.p012io;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: org.apache.james.mime4j.io.LimitedInputStream */
public class LimitedInputStream extends PositionInputStream {
    private final long limit;

    public LimitedInputStream(InputStream inputStream, long j) {
        super(inputStream);
        if (j >= 0) {
            this.limit = j;
            return;
        }
        throw new IllegalArgumentException("Limit may not be negative");
    }

    private void enforceLimit() throws IOException {
        if (this.position >= this.limit) {
            throw new IOException("Input stream limit exceeded");
        }
    }

    public int read() throws IOException {
        enforceLimit();
        return super.read();
    }

    public long skip(long j) throws IOException {
        enforceLimit();
        return super.skip(Math.min(j, (long) ((int) Math.min(2147483647L, this.limit - this.position))));
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        enforceLimit();
        return super.read(bArr, i, Math.min(i2, (int) Math.min(2147483647L, this.limit - this.position)));
    }
}
