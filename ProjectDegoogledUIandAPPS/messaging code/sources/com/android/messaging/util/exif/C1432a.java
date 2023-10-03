package com.android.messaging.util.exif;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* renamed from: com.android.messaging.util.exif.a */
class C1432a extends FilterInputStream {

    /* renamed from: YO */
    private final byte[] f2246YO = new byte[8];
    private final ByteBuffer mByteBuffer = ByteBuffer.wrap(this.f2246YO);
    private int mCount = 0;

    protected C1432a(InputStream inputStream) {
        super(inputStream);
    }

    /* renamed from: H */
    public void mo8064H(long j) {
        long j2 = j - ((long) this.mCount);
        if (skip(j2) != j2) {
            throw new EOFException();
        }
    }

    /* renamed from: Il */
    public int mo8065Il() {
        return this.mCount;
    }

    /* renamed from: a */
    public String mo8066a(int i, Charset charset) {
        byte[] bArr = new byte[i];
        mo8067b(bArr, 0, bArr.length);
        return new String(bArr, charset);
    }

    /* renamed from: b */
    public void mo8067b(byte[] bArr, int i, int i2) {
        int read = this.in.read(bArr, i, i2);
        this.mCount += read >= 0 ? read : 0;
        if (read != i2) {
            throw new EOFException();
        }
    }

    public int read(byte[] bArr) {
        int read = this.in.read(bArr);
        this.mCount += read >= 0 ? read : 0;
        return read;
    }

    public int readInt() {
        mo8067b(this.f2246YO, 0, 4);
        this.mByteBuffer.rewind();
        return this.mByteBuffer.getInt();
    }

    public short readShort() {
        mo8067b(this.f2246YO, 0, 2);
        this.mByteBuffer.rewind();
        return this.mByteBuffer.getShort();
    }

    public long readUnsignedInt() {
        return ((long) readInt()) & 4294967295L;
    }

    public int readUnsignedShort() {
        return readShort() & 65535;
    }

    public void setByteOrder(ByteOrder byteOrder) {
        this.mByteBuffer.order(byteOrder);
    }

    /* renamed from: sk */
    public ByteOrder mo8076sk() {
        return this.mByteBuffer.order();
    }

    public long skip(long j) {
        long skip = this.in.skip(j);
        this.mCount = (int) (((long) this.mCount) + skip);
        return skip;
    }

    public int read(byte[] bArr, int i, int i2) {
        int read = this.in.read(bArr, i, i2);
        this.mCount += read >= 0 ? read : 0;
        return read;
    }

    public int read() {
        int read = this.in.read();
        this.mCount += read >= 0 ? 1 : 0;
        return read;
    }
}
