package com.android.dialer.callcomposer.camera.exif;

import com.android.dialer.common.Assert;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

class CountedDataInputStream extends FilterInputStream {
    private final byte[] byteArray = new byte[8];
    private final ByteBuffer byteBuffer = ByteBuffer.wrap(this.byteArray);
    private int count = 0;

    CountedDataInputStream(InputStream inputStream) {
        super(inputStream);
    }

    private void readOrThrow(byte[] bArr, int i, int i2) throws IOException {
        int read = this.in.read(bArr, i, i2);
        this.count += read >= 0 ? read : 0;
        if (read != i2) {
            throw new EOFException();
        }
    }

    /* access modifiers changed from: package-private */
    public int getReadByteCount() {
        return this.count;
    }

    public int read(byte[] bArr) throws IOException {
        int read = this.in.read(bArr);
        this.count += read >= 0 ? read : 0;
        return read;
    }

    /* access modifiers changed from: package-private */
    public int readInt() throws IOException {
        readOrThrow(this.byteArray, 0, 4);
        this.byteBuffer.rewind();
        return this.byteBuffer.getInt();
    }

    /* access modifiers changed from: package-private */
    public short readShort() throws IOException {
        readOrThrow(this.byteArray, 0, 2);
        this.byteBuffer.rewind();
        return this.byteBuffer.getShort();
    }

    /* access modifiers changed from: package-private */
    public String readString(int i, Charset charset) throws IOException {
        byte[] bArr = new byte[i];
        readOrThrow(bArr, 0, bArr.length);
        return new String(bArr, charset);
    }

    /* access modifiers changed from: package-private */
    public long readUnsignedInt() throws IOException {
        return ((long) readInt()) & 4294967295L;
    }

    /* access modifiers changed from: package-private */
    public int readUnsignedShort() throws IOException {
        return readShort() & 65535;
    }

    /* access modifiers changed from: package-private */
    public void setByteOrder(ByteOrder byteOrder) {
        this.byteBuffer.order(byteOrder);
    }

    public long skip(long j) throws IOException {
        long skip = this.in.skip(j);
        this.count = (int) (((long) this.count) + skip);
        return skip;
    }

    /* access modifiers changed from: package-private */
    public void skipTo(long j) throws IOException {
        long j2 = j - ((long) this.count);
        Assert.checkArgument(j2 >= 0);
        if (skip(j2) != j2) {
            throw new EOFException();
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.in.read(bArr, i, i2);
        this.count += read >= 0 ? read : 0;
        return read;
    }

    public int read() throws IOException {
        int read = this.in.read();
        this.count += read >= 0 ? 1 : 0;
        return read;
    }
}
