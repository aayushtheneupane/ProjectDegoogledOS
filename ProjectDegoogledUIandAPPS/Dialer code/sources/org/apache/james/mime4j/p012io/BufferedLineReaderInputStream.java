package org.apache.james.mime4j.p012io;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.util.ByteArrayBuffer;

/* renamed from: org.apache.james.mime4j.io.BufferedLineReaderInputStream */
public class BufferedLineReaderInputStream extends LineReaderInputStream {
    private byte[] buffer;
    private int buflen;
    private int bufpos;
    private final int maxLineLen;
    private byte[] origBuffer;
    private int origBuflen;
    private int origBufpos;
    boolean tempBuffer = false;
    private boolean truncated;

    public BufferedLineReaderInputStream(InputStream inputStream, int i, int i2) {
        super(inputStream);
        if (inputStream == null) {
            throw new IllegalArgumentException("Input stream may not be null");
        } else if (i > 0) {
            this.buffer = new byte[i];
            this.bufpos = 0;
            this.buflen = 0;
            this.maxLineLen = i2;
            this.truncated = false;
        } else {
            throw new IllegalArgumentException("Buffer size may not be negative or zero");
        }
    }

    private int bufferLen() {
        return this.buflen - this.bufpos;
    }

    /* access modifiers changed from: protected */
    public byte[] buf() {
        return this.buffer;
    }

    public int byteAt(int i) {
        if (i >= this.bufpos && i <= this.buflen) {
            return this.buffer[i] & 255;
        }
        throw new IndexOutOfBoundsException("looking for " + i + " in " + this.bufpos + "/" + this.buflen);
    }

    public void ensureCapacity(int i) {
        if (i > this.buffer.length) {
            byte[] bArr = new byte[i];
            int bufferLen = bufferLen();
            if (bufferLen > 0) {
                byte[] bArr2 = this.buffer;
                int i2 = this.bufpos;
                System.arraycopy(bArr2, i2, bArr, i2, bufferLen);
            }
            this.buffer = bArr;
        }
    }

    public int fillBuffer() throws IOException {
        if (!this.tempBuffer) {
            if (this.bufpos > 0) {
                int bufferLen = bufferLen();
                if (bufferLen > 0) {
                    byte[] bArr = this.buffer;
                    System.arraycopy(bArr, this.bufpos, bArr, 0, bufferLen);
                }
                this.bufpos = 0;
                this.buflen = bufferLen;
            }
            int i = this.buflen;
            int read = this.in.read(this.buffer, i, this.buffer.length - i);
            if (read == -1) {
                return -1;
            }
            this.buflen = i + read;
            return read;
        } else if (this.bufpos == this.buflen) {
            this.buffer = this.origBuffer;
            this.buflen = this.origBuflen;
            this.bufpos = this.origBufpos;
            this.tempBuffer = false;
            return bufferLen();
        } else {
            throw new IllegalStateException("unread only works when a buffer is fully read before the next refill is asked!");
        }
    }

    public boolean hasBufferedData() {
        return bufferLen() > 0;
    }

    public int indexOf(byte[] bArr, int i, int i2) {
        boolean z;
        if (bArr == null) {
            throw new IllegalArgumentException("Pattern may not be null");
        } else if (i < this.bufpos || i2 < 0 || i + i2 > this.buflen) {
            throw new IndexOutOfBoundsException("looking for " + i + "(" + i2 + ")" + " in " + this.bufpos + "/" + this.buflen);
        } else if (i2 < bArr.length) {
            return -1;
        } else {
            int[] iArr = new int[256];
            for (int i3 = 0; i3 < iArr.length; i3++) {
                iArr[i3] = bArr.length + 1;
            }
            for (int i4 = 0; i4 < bArr.length; i4++) {
                iArr[bArr[i4] & 255] = bArr.length - i4;
            }
            int i5 = 0;
            while (i5 <= i2 - bArr.length) {
                int i6 = i + i5;
                int i7 = 0;
                while (true) {
                    if (i7 >= bArr.length) {
                        z = true;
                        break;
                    } else if (this.buffer[i6 + i7] != bArr[i7]) {
                        z = false;
                        break;
                    } else {
                        i7++;
                    }
                }
                if (z) {
                    return i6;
                }
                int length = i6 + bArr.length;
                byte[] bArr2 = this.buffer;
                if (length >= bArr2.length) {
                    break;
                }
                i5 += iArr[bArr2[length] & 255];
            }
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    public int length() {
        return bufferLen();
    }

    /* access modifiers changed from: protected */
    public int limit() {
        return this.buflen;
    }

    public boolean markSupported() {
        return false;
    }

    /* access modifiers changed from: protected */
    public int pos() {
        return this.bufpos;
    }

    public int read() throws IOException {
        if (!(!this.truncated)) {
            return -1;
        }
        while (!hasBufferedData()) {
            if (fillBuffer() == -1) {
                return -1;
            }
        }
        byte[] bArr = this.buffer;
        int i = this.bufpos;
        this.bufpos = i + 1;
        return bArr[i] & 255;
    }

    public int readLine(ByteArrayBuffer byteArrayBuffer) throws MaxLineLimitException, IOException {
        int i;
        if (byteArrayBuffer == null) {
            throw new IllegalArgumentException("Buffer may not be null");
        } else if (!(!this.truncated)) {
            return -1;
        } else {
            boolean z = false;
            int i2 = 0;
            int i3 = 0;
            while (!z && (hasBufferedData() || (i3 = fillBuffer()) != -1)) {
                int indexOf = indexOf((byte) 10, this.bufpos, bufferLen());
                if (indexOf != -1) {
                    i = (indexOf + 1) - this.bufpos;
                    z = true;
                } else {
                    i = length();
                }
                if (i > 0) {
                    byteArrayBuffer.append(buf(), this.bufpos, i);
                    skip(i);
                    i2 += i;
                }
                if (this.maxLineLen > 0 && byteArrayBuffer.length() >= this.maxLineLen) {
                    throw new MaxLineLimitException("Maximum line length limit exceeded");
                }
            }
            if (i2 == 0 && i3 == -1) {
                return -1;
            }
            return i2;
        }
    }

    /* access modifiers changed from: protected */
    public int skip(int i) {
        int min = Math.min(i, bufferLen());
        this.bufpos += min;
        return min;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("[pos: ");
        outline13.append(this.bufpos);
        outline13.append("]");
        outline13.append("[limit: ");
        outline13.append(this.buflen);
        outline13.append("]");
        outline13.append("[");
        for (int i = this.bufpos; i < this.buflen; i++) {
            outline13.append((char) this.buffer[i]);
        }
        outline13.append("]");
        if (this.tempBuffer) {
            outline13.append("-ORIG[pos: ");
            outline13.append(this.origBufpos);
            outline13.append("]");
            outline13.append("[limit: ");
            outline13.append(this.origBuflen);
            outline13.append("]");
            outline13.append("[");
            for (int i2 = this.origBufpos; i2 < this.origBuflen; i2++) {
                outline13.append((char) this.origBuffer[i2]);
            }
            outline13.append("]");
        }
        return outline13.toString();
    }

    public boolean unread(ByteArrayBuffer byteArrayBuffer) {
        if (this.tempBuffer) {
            return false;
        }
        this.origBuffer = this.buffer;
        this.origBuflen = this.buflen;
        this.origBufpos = this.bufpos;
        this.bufpos = 0;
        this.buflen = byteArrayBuffer.length();
        this.buffer = byteArrayBuffer.buffer();
        this.tempBuffer = true;
        return true;
    }

    public int read(byte[] bArr) throws IOException {
        if (!(!this.truncated)) {
            return -1;
        }
        if (bArr == null) {
            return 0;
        }
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (!(!this.truncated)) {
            return -1;
        }
        if (bArr == null) {
            return 0;
        }
        while (!hasBufferedData()) {
            if (fillBuffer() == -1) {
                return -1;
            }
        }
        int bufferLen = bufferLen();
        if (bufferLen <= i2) {
            i2 = bufferLen;
        }
        System.arraycopy(this.buffer, this.bufpos, bArr, i, i2);
        this.bufpos += i2;
        return i2;
    }

    public int indexOf(byte b, int i, int i2) {
        int i3;
        if (i < this.bufpos || i2 < 0 || (i3 = i2 + i) > this.buflen) {
            throw new IndexOutOfBoundsException();
        }
        while (i < i3) {
            if (this.buffer[i] == b) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
