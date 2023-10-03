package org.apache.james.mime4j.p012io;

import java.io.IOException;
import org.apache.james.mime4j.MimeException;
import org.apache.james.mime4j.MimeIOException;
import org.apache.james.mime4j.util.ByteArrayBuffer;
import org.apache.james.mime4j.util.CharsetUtil;

/* renamed from: org.apache.james.mime4j.io.MimeBoundaryInputStream */
public class MimeBoundaryInputStream extends LineReaderInputStream {
    private boolean atBoundary;
    private final byte[] boundary;
    private int boundaryLen;
    private BufferedLineReaderInputStream buffer;
    private boolean completed;
    private boolean eof;
    private int initialLength;
    private boolean lastPart;
    private int limit;
    private final boolean strict;

    public MimeBoundaryInputStream(BufferedLineReaderInputStream bufferedLineReaderInputStream, String str, boolean z) throws IOException {
        super(bufferedLineReaderInputStream);
        int length = str.length() * 2;
        bufferedLineReaderInputStream.ensureCapacity(length < 4096 ? 4096 : length);
        this.buffer = bufferedLineReaderInputStream;
        this.eof = false;
        this.limit = -1;
        this.atBoundary = false;
        this.boundaryLen = 0;
        this.lastPart = false;
        this.initialLength = -1;
        this.completed = false;
        this.strict = z;
        this.boundary = new byte[(str.length() + 2)];
        byte[] bArr = this.boundary;
        bArr[0] = 45;
        bArr[1] = 45;
        for (int i = 0; i < str.length(); i++) {
            this.boundary[i + 2] = (byte) str.charAt(i);
        }
        fillBuffer();
    }

    private int fillBuffer() throws IOException {
        int i;
        int indexOf;
        if (this.eof) {
            return -1;
        }
        if (!hasData()) {
            i = this.buffer.fillBuffer();
            if (i == -1) {
                this.eof = true;
            }
        } else {
            i = 0;
        }
        int pos = this.buffer.pos();
        while (true) {
            BufferedLineReaderInputStream bufferedLineReaderInputStream = this.buffer;
            indexOf = bufferedLineReaderInputStream.indexOf(this.boundary, pos, bufferedLineReaderInputStream.limit() - pos);
            if (indexOf == -1) {
                break;
            }
            if (indexOf == this.buffer.pos() || this.buffer.byteAt(indexOf - 1) == 10) {
                int length = this.boundary.length + indexOf;
                if (this.buffer.limit() - length > 0) {
                    char byteAt = (char) this.buffer.byteAt(length);
                    if (CharsetUtil.isWhitespace(byteAt) || byteAt == '-') {
                        break;
                    }
                } else {
                    break;
                }
            }
            pos = indexOf + this.boundary.length;
        }
        if (indexOf != -1) {
            this.limit = indexOf;
            this.atBoundary = true;
            this.boundaryLen = this.boundary.length;
            int pos2 = this.limit - this.buffer.pos();
            if (pos2 >= 0 && this.initialLength == -1) {
                this.initialLength = pos2;
            }
            if (pos2 > 0 && this.buffer.byteAt(this.limit - 1) == 10) {
                this.boundaryLen++;
                this.limit--;
            }
            if (pos2 > 1 && this.buffer.byteAt(this.limit - 1) == 13) {
                this.boundaryLen++;
                this.limit--;
            }
        } else if (this.eof) {
            this.limit = this.buffer.limit();
        } else {
            this.limit = this.buffer.limit() - (this.boundary.length + 2);
        }
        return i;
    }

    private boolean hasData() {
        return this.limit > this.buffer.pos() && this.limit <= this.buffer.limit();
    }

    private void skipBoundary() throws IOException {
        if (!this.completed) {
            this.completed = true;
            this.buffer.skip(this.boundaryLen);
            boolean z = true;
            while (true) {
                if (this.buffer.length() > 1) {
                    BufferedLineReaderInputStream bufferedLineReaderInputStream = this.buffer;
                    int byteAt = bufferedLineReaderInputStream.byteAt(bufferedLineReaderInputStream.pos());
                    BufferedLineReaderInputStream bufferedLineReaderInputStream2 = this.buffer;
                    int byteAt2 = bufferedLineReaderInputStream2.byteAt(bufferedLineReaderInputStream2.pos() + 1);
                    if (z && byteAt == 45 && byteAt2 == 45) {
                        this.lastPart = true;
                        this.buffer.skip(2);
                        z = false;
                    } else if (byteAt == 13 && byteAt2 == 10) {
                        this.buffer.skip(2);
                        return;
                    } else if (byteAt == 10) {
                        this.buffer.skip(1);
                        return;
                    } else {
                        this.buffer.skip(1);
                    }
                } else if (!this.eof) {
                    fillBuffer();
                } else {
                    return;
                }
            }
        }
    }

    private void verifyEndOfStream() throws IOException {
        if (this.strict && this.eof && !this.atBoundary) {
            throw new MimeIOException(new MimeException("Unexpected end of stream"));
        }
    }

    public void close() throws IOException {
    }

    public boolean eof() {
        return this.eof && !this.buffer.hasBufferedData();
    }

    public boolean isEmptyStream() {
        return this.initialLength == 0;
    }

    public boolean isFullyConsumed() {
        return this.completed && !this.buffer.hasBufferedData();
    }

    public boolean isLastPart() {
        return this.lastPart;
    }

    public boolean markSupported() {
        return false;
    }

    public int read() throws IOException {
        while (readAllowed()) {
            if (hasData()) {
                return this.buffer.read();
            }
            fillBuffer();
        }
        return -1;
    }

    public boolean readAllowed() throws IOException {
        if (this.completed) {
            return false;
        }
        if (!(this.eof || this.atBoundary) || hasData()) {
            return true;
        }
        skipBoundary();
        verifyEndOfStream();
        return false;
    }

    public int readLine(ByteArrayBuffer byteArrayBuffer) throws IOException {
        if (byteArrayBuffer == null) {
            throw new IllegalArgumentException("Destination buffer may not be null");
        } else if (!readAllowed()) {
            return -1;
        } else {
            boolean z = false;
            int i = 0;
            int i2 = 0;
            while (true) {
                if (z) {
                    break;
                }
                if (!hasData()) {
                    i2 = fillBuffer();
                    if ((this.eof || this.atBoundary) && !hasData()) {
                        skipBoundary();
                        verifyEndOfStream();
                        i2 = -1;
                        break;
                    }
                }
                int pos = this.limit - this.buffer.pos();
                BufferedLineReaderInputStream bufferedLineReaderInputStream = this.buffer;
                int indexOf = bufferedLineReaderInputStream.indexOf((byte) 10, bufferedLineReaderInputStream.pos(), pos);
                if (indexOf != -1) {
                    pos = (indexOf + 1) - this.buffer.pos();
                    z = true;
                }
                if (pos > 0) {
                    byteArrayBuffer.append(this.buffer.buf(), this.buffer.pos(), pos);
                    this.buffer.skip(pos);
                    i += pos;
                }
            }
            if (i == 0 && i2 == -1) {
                return -1;
            }
            return i;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MimeBoundaryInputStream, boundary ");
        for (byte b : this.boundary) {
            sb.append((char) b);
        }
        return sb.toString();
    }

    public boolean unread(ByteArrayBuffer byteArrayBuffer) {
        return false;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        while (readAllowed()) {
            if (hasData()) {
                return this.buffer.read(bArr, i, Math.min(i2, this.limit - this.buffer.pos()));
            }
            fillBuffer();
        }
        return -1;
    }
}
