package com.android.volley.toolbox;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PoolingByteArrayOutputStream extends ByteArrayOutputStream {
    private final ByteArrayPool mPool;

    public PoolingByteArrayOutputStream(ByteArrayPool byteArrayPool, int i) {
        this.mPool = byteArrayPool;
        this.buf = this.mPool.getBuf(Math.max(i, 256));
    }

    private void expand(int i) {
        int i2 = this.count;
        if (i2 + i > this.buf.length) {
            byte[] buf = this.mPool.getBuf((i2 + i) * 2);
            System.arraycopy(this.buf, 0, buf, 0, this.count);
            this.mPool.returnBuf(this.buf);
            this.buf = buf;
        }
    }

    public void close() throws IOException {
        this.mPool.returnBuf(this.buf);
        this.buf = null;
        super.close();
    }

    public void finalize() {
        this.mPool.returnBuf(this.buf);
    }

    public synchronized void write(byte[] bArr, int i, int i2) {
        expand(i2);
        super.write(bArr, i, i2);
    }

    public synchronized void write(int i) {
        expand(1);
        super.write(i);
    }
}
