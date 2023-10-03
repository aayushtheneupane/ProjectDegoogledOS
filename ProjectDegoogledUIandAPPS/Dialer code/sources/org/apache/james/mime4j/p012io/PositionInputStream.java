package org.apache.james.mime4j.p012io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: org.apache.james.mime4j.io.PositionInputStream */
public class PositionInputStream extends FilterInputStream {
    private long markedPosition = 0;
    protected long position = 0;

    public PositionInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public int available() throws IOException {
        return this.in.available();
    }

    public void close() throws IOException {
        this.in.close();
    }

    public void mark(int i) {
        this.in.mark(i);
        this.markedPosition = this.position;
    }

    public boolean markSupported() {
        return this.in.markSupported();
    }

    public int read() throws IOException {
        int read = this.in.read();
        if (read != -1) {
            this.position++;
        }
        return read;
    }

    public void reset() throws IOException {
        this.in.reset();
        this.position = this.markedPosition;
    }

    public long skip(long j) throws IOException {
        long skip = this.in.skip(j);
        if (skip > 0) {
            this.position += skip;
        }
        return skip;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.in.read(bArr, i, i2);
        if (read > 0) {
            this.position += (long) read;
        }
        return read;
    }
}
