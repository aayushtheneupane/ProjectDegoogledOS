package org.apache.james.mime4j.p012io;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.util.ByteArrayBuffer;

/* renamed from: org.apache.james.mime4j.io.LineReaderInputStreamAdaptor */
public class LineReaderInputStreamAdaptor extends LineReaderInputStream {
    private final LineReaderInputStream bis;
    private boolean eof = false;
    private final int maxLineLen;
    private boolean used = false;

    public LineReaderInputStreamAdaptor(InputStream inputStream, int i) {
        super(inputStream);
        if (inputStream instanceof LineReaderInputStream) {
            this.bis = (LineReaderInputStream) inputStream;
        } else {
            this.bis = null;
        }
        this.maxLineLen = i;
    }

    public boolean eof() {
        return this.eof;
    }

    public boolean isUsed() {
        return this.used;
    }

    public int read() throws IOException {
        int read = this.in.read();
        this.eof = read == -1;
        this.used = true;
        return read;
    }

    public int readLine(ByteArrayBuffer byteArrayBuffer) throws MaxLineLimitException, IOException {
        int i;
        int read;
        LineReaderInputStream lineReaderInputStream = this.bis;
        boolean z = false;
        if (lineReaderInputStream != null) {
            i = lineReaderInputStream.readLine(byteArrayBuffer);
        } else {
            int i2 = 0;
            do {
                read = this.in.read();
                if (read == -1) {
                    break;
                }
                byteArrayBuffer.append(read);
                i2++;
                if (this.maxLineLen > 0 && byteArrayBuffer.length() >= this.maxLineLen) {
                    throw new MaxLineLimitException("Maximum line length limit exceeded");
                }
            } while (read != 10);
            i = (i2 == 0 && read == -1) ? -1 : i2;
        }
        if (i == -1) {
            z = true;
        }
        this.eof = z;
        this.used = true;
        return i;
    }

    public long skip(long j) throws IOException {
        int read;
        if (j <= 0) {
            return 0;
        }
        byte[] bArr = new byte[(j > 8192 ? 8192 : (int) j)];
        long j2 = 0;
        while (j > 0 && (read = read(bArr)) != -1) {
            long j3 = (long) read;
            j2 += j3;
            j -= j3;
        }
        return j2;
    }

    public String toString() {
        return GeneratedOutlineSupport.outline11(GeneratedOutlineSupport.outline13("[LineReaderInputStreamAdaptor: "), this.bis, "]");
    }

    public boolean unread(ByteArrayBuffer byteArrayBuffer) {
        LineReaderInputStream lineReaderInputStream = this.bis;
        if (lineReaderInputStream != null) {
            return lineReaderInputStream.unread(byteArrayBuffer);
        }
        return false;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.in.read(bArr, i, i2);
        this.eof = read == -1;
        this.used = true;
        return read;
    }
}
