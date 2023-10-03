package org.apache.james.mime4j.util;

public final class ByteArrayBuffer implements ByteSequence {
    private byte[] buffer;
    private int len;

    public ByteArrayBuffer(int i) {
        if (i >= 0) {
            this.buffer = new byte[i];
            return;
        }
        throw new IllegalArgumentException("Buffer capacity may not be negative");
    }

    private void expand(int i) {
        byte[] bArr = new byte[Math.max(this.buffer.length << 1, i)];
        System.arraycopy(this.buffer, 0, bArr, 0, this.len);
        this.buffer = bArr;
    }

    public void append(byte[] bArr, int i, int i2) {
        int i3;
        if (bArr != null) {
            if (i < 0 || i > bArr.length || i2 < 0 || (i3 = i + i2) < 0 || i3 > bArr.length) {
                throw new IndexOutOfBoundsException();
            } else if (i2 != 0) {
                int i4 = this.len + i2;
                if (i4 > this.buffer.length) {
                    expand(i4);
                }
                System.arraycopy(bArr, i, this.buffer, this.len, i2);
                this.len = i4;
            }
        }
    }

    public byte[] buffer() {
        return this.buffer;
    }

    public byte byteAt(int i) {
        if (i >= 0 && i < this.len) {
            return this.buffer[i];
        }
        throw new IndexOutOfBoundsException();
    }

    public void clear() {
        this.len = 0;
    }

    public int length() {
        return this.len;
    }

    public void remove(int i, int i2) {
        int i3;
        int i4;
        if (i < 0 || i > (i3 = this.len) || i2 < 0 || (i4 = i + i2) < 0 || i4 > i3) {
            throw new IndexOutOfBoundsException();
        } else if (i2 != 0) {
            int i5 = (i3 - i) - i2;
            if (i5 > 0) {
                byte[] bArr = this.buffer;
                System.arraycopy(bArr, i4, bArr, i, i5);
            }
            this.len -= i2;
        }
    }

    public String toString() {
        int i = this.len;
        byte[] bArr = new byte[i];
        if (i > 0) {
            System.arraycopy(this.buffer, 0, bArr, 0, i);
        }
        return new String(bArr);
    }

    public ByteArrayBuffer(byte[] bArr, int i, boolean z) {
        if (bArr == null) {
            throw new IllegalArgumentException();
        } else if (i < 0 || i > bArr.length) {
            throw new IllegalArgumentException();
        } else {
            if (z) {
                this.buffer = bArr;
            } else {
                this.buffer = new byte[i];
                System.arraycopy(bArr, 0, this.buffer, 0, i);
            }
            this.len = i;
        }
    }

    public void append(int i) {
        int i2 = this.len + 1;
        if (i2 > this.buffer.length) {
            expand(i2);
        }
        this.buffer[this.len] = (byte) i;
        this.len = i2;
    }
}
