package org.apache.james.mime4j.codec;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.io.InputStream;
import org.apache.james.mime4j.util.ByteArrayBuffer;

public class QuotedPrintableInputStream extends InputStream {
    private final ByteArrayBuffer blanks;
    private boolean closed;
    private final ByteArrayBuffer decodedBuf;
    private final byte[] encoded;

    /* renamed from: in */
    private final InputStream f86in;
    private int limit;
    private final DecodeMonitor monitor;
    private int pos;
    private final byte[] singleByte;

    public QuotedPrintableInputStream(InputStream inputStream, DecodeMonitor decodeMonitor) {
        this(2048, inputStream, decodeMonitor);
    }

    private int convert(int i) {
        if (i >= 48 && i <= 57) {
            return i - 48;
        }
        int i2 = 65;
        if (i < 65 || i > 70) {
            i2 = 97;
            if (i < 97 || i > 102) {
                return -1;
            }
        }
        return (i - i2) + 10;
    }

    private int getnext() {
        int i = this.pos;
        if (i >= this.limit) {
            return -1;
        }
        byte b = this.encoded[i];
        this.pos = i + 1;
        return b & 255;
    }

    private int peek(int i) {
        int i2 = this.pos;
        if (i2 + i < this.limit) {
            return this.encoded[i2 + i] & 255;
        }
        return -1;
    }

    private int transfer(int i, byte[] bArr, int i2, int i3, boolean z) throws IOException {
        if (z && this.blanks.length() > 0) {
            int min = Math.min(this.blanks.length(), i3 - i2);
            System.arraycopy(this.blanks.buffer(), 0, bArr, i2, min);
            i2 += min;
            int length = this.blanks.length() - min;
            if (length > 0) {
                this.decodedBuf.append(this.blanks.buffer(), min, length);
            }
            this.blanks.clear();
        } else if (this.blanks.length() > 0 && !z) {
            StringBuilder sb = new StringBuilder(this.blanks.length() * 3);
            for (int i4 = 0; i4 < this.blanks.length(); i4++) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13(" ");
                outline13.append(this.blanks.byteAt(i4));
                sb.append(outline13.toString());
            }
            if (this.monitor.warn("ignored blanks", sb.toString())) {
                throw new IOException("ignored blanks");
            }
        }
        if (i != -1) {
            if (i2 < i3) {
                int i5 = i2 + 1;
                bArr[i2] = (byte) i;
                return i5;
            }
            this.decodedBuf.append(i);
        }
        return i2;
    }

    public void close() throws IOException {
        this.closed = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00de, code lost:
        if (r13 != 10) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e6, code lost:
        if (r6.blanks.length() != 0) goto L_0x00fc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00e8, code lost:
        r0 = r16;
        r2 = r8;
        r4 = r9;
        r0 = transfer(10, r2, transfer(13, r2, r3, r4, false), r4, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0102, code lost:
        if (r6.blanks.byteAt(0) == 61) goto L_0x0118;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0104, code lost:
        r0 = r16;
        r2 = r8;
        r4 = r9;
        r0 = transfer(10, r2, transfer(13, r2, r3, r4, false), r4, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0118, code lost:
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0119, code lost:
        r6.blanks.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0120, code lost:
        if (r13 != 61) goto L_0x0201;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0122, code lost:
        r1 = r6.limit;
        r2 = r6.pos;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0128, code lost:
        if ((r1 - r2) >= 2) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x012a, code lost:
        if (r12 != false) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x012c, code lost:
        r6.pos = r2 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0132, code lost:
        r5 = getnext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0136, code lost:
        if (r5 != 61) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0138, code lost:
        r4 = r9;
        r9 = r5;
        r0 = transfer(r5, r8, r3, r4, true);
        r1 = peek(0);
        r2 = peek(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x014c, code lost:
        if (r1 == 10) goto L_0x015e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x014e, code lost:
        if (r1 != 13) goto L_0x0153;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0150, code lost:
        if (r2 != 10) goto L_0x0153;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0153, code lost:
        r6.monitor.warn("Unexpected == encountered", "==");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x015e, code lost:
        r6.monitor.warn("Unexpected ==EOL encountered", "== 0x" + r1 + " 0x" + r2);
        r6.blanks.append(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0185, code lost:
        r9 = r5;
        r0 = (char) r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x018b, code lost:
        if (java.lang.Character.isWhitespace(r0) == false) goto L_0x01a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x018d, code lost:
        r0 = transfer(-1, r8, r3, 1, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0198, code lost:
        if (r9 == 10) goto L_0x020d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x019a, code lost:
        r6.blanks.append(r13);
        r6.blanks.append(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01a6, code lost:
        r13 = getnext();
        r1 = convert(r9);
        r2 = convert(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01b2, code lost:
        if (r1 < 0) goto L_0x01c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01b4, code lost:
        if (r2 >= 0) goto L_0x01b7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01b7, code lost:
        r0 = transfer((r1 << 4) | r2, r8, r3, 1, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01c6, code lost:
        r6.monitor.warn("Malformed encoded value encountered", "leaving =" + r0 + ((char) r13) + " as is");
        r0 = r16;
        r2 = r8;
        r0 = transfer(r13, r2, transfer(r9, r2, transfer(61, r2, r3, 1, true), 1, false), 1, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0205, code lost:
        if (java.lang.Character.isWhitespace(r13) == false) goto L_0x0210;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0207, code lost:
        r6.blanks.append(r13);
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x020d, code lost:
        r9 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0210, code lost:
        r9 = 1;
        r0 = transfer(r13 & 255, r8, r3, 1, true);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read() throws java.io.IOException {
        /*
            r16 = this;
            r6 = r16
            boolean r0 = r6.closed
            java.lang.String r7 = "Stream has been closed"
            if (r0 != 0) goto L_0x0235
        L_0x0008:
            byte[] r8 = r6.singleByte
            boolean r0 = r6.closed
            if (r0 != 0) goto L_0x022f
            r9 = 1
            org.apache.james.mime4j.util.ByteArrayBuffer r0 = r6.decodedBuf
            int r0 = r0.length()
            r10 = 0
            r11 = 1
            if (r0 <= 0) goto L_0x0033
            org.apache.james.mime4j.util.ByteArrayBuffer r0 = r6.decodedBuf
            int r0 = r0.length()
            int r0 = java.lang.Math.min(r0, r11)
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.decodedBuf
            byte[] r1 = r1.buffer()
            java.lang.System.arraycopy(r1, r10, r8, r10, r0)
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.decodedBuf
            r1.remove(r10, r0)
            int r0 = r0 + r10
            goto L_0x0034
        L_0x0033:
            r0 = r10
        L_0x0034:
            r1 = r10
        L_0x0035:
            r2 = -1
            if (r0 >= r9) goto L_0x0222
            int r3 = r6.limit
            int r4 = r6.pos
            int r5 = r3 - r4
            r12 = 3
            if (r5 >= r12) goto L_0x0073
            if (r4 >= r3) goto L_0x0052
            byte[] r1 = r6.encoded
            java.lang.System.arraycopy(r1, r4, r1, r10, r5)
            int r1 = r6.limit
            int r3 = r6.pos
            int r1 = r1 - r3
            r6.limit = r1
            r6.pos = r10
            goto L_0x0056
        L_0x0052:
            r6.limit = r10
            r6.pos = r10
        L_0x0056:
            byte[] r1 = r6.encoded
            int r3 = r1.length
            int r4 = r6.limit
            int r3 = r3 - r4
            if (r3 <= 0) goto L_0x006c
            java.io.InputStream r5 = r6.f86in
            int r1 = r5.read(r1, r4, r3)
            if (r1 <= 0) goto L_0x006d
            int r3 = r6.limit
            int r3 = r3 + r1
            r6.limit = r3
            goto L_0x006d
        L_0x006c:
            r1 = r10
        L_0x006d:
            if (r1 != r2) goto L_0x0071
            r12 = r11
            goto L_0x0074
        L_0x0071:
            r12 = r10
            goto L_0x0074
        L_0x0073:
            r12 = r1
        L_0x0074:
            int r1 = r6.limit
            int r3 = r6.pos
            int r1 = r1 - r3
            if (r1 != 0) goto L_0x0086
            if (r12 == 0) goto L_0x0086
            if (r0 != 0) goto L_0x0082
            r0 = r2
            goto L_0x0223
        L_0x0082:
            int r0 = r0 + 0
            goto L_0x0223
        L_0x0086:
            r3 = r0
            r0 = r10
        L_0x0088:
            int r1 = r6.pos
            int r2 = r6.limit
            if (r1 >= r2) goto L_0x021e
            if (r3 >= r9) goto L_0x021e
            byte[] r2 = r6.encoded
            int r4 = r1 + 1
            r6.pos = r4
            byte r1 = r2[r1]
            r13 = r1 & 255(0xff, float:3.57E-43)
            r14 = 10
            if (r0 == 0) goto L_0x00bf
            if (r13 == r14) goto L_0x00bf
            org.apache.james.mime4j.codec.DecodeMonitor r0 = r6.monitor
            java.lang.String r1 = "Found CR without LF"
            java.lang.String r2 = "Leaving it as is"
            boolean r0 = r0.warn(r1, r2)
            if (r0 != 0) goto L_0x00b9
            r1 = 13
            r5 = 0
            r0 = r16
            r2 = r8
            r4 = r9
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            r3 = r0
            goto L_0x00d6
        L_0x00b9:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r1)
            throw r0
        L_0x00bf:
            if (r0 != 0) goto L_0x00d6
            if (r13 != r14) goto L_0x00d6
            org.apache.james.mime4j.codec.DecodeMonitor r0 = r6.monitor
            java.lang.String r1 = "Found LF without CR"
            java.lang.String r2 = "Translating to CRLF"
            boolean r0 = r0.warn(r1, r2)
            if (r0 != 0) goto L_0x00d0
            goto L_0x00d6
        L_0x00d0:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r1)
            throw r0
        L_0x00d6:
            r15 = 13
            if (r13 != r15) goto L_0x00dc
            r0 = r11
            goto L_0x0088
        L_0x00dc:
            r0 = 61
            if (r13 != r14) goto L_0x0120
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.blanks
            int r1 = r1.length()
            if (r1 != 0) goto L_0x00fc
            r1 = 13
            r13 = 0
            r5 = 0
            r0 = r16
            r2 = r8
            r4 = r9
            int r3 = r0.transfer(r1, r2, r3, r4, r5)
            r1 = 10
            r5 = r13
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            goto L_0x0119
        L_0x00fc:
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.blanks
            byte r1 = r1.byteAt(r10)
            if (r1 == r0) goto L_0x0118
            r1 = 13
            r13 = 0
            r5 = 0
            r0 = r16
            r2 = r8
            r4 = r9
            int r3 = r0.transfer(r1, r2, r3, r4, r5)
            r1 = 10
            r5 = r13
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            goto L_0x0119
        L_0x0118:
            r0 = r3
        L_0x0119:
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.blanks
            r1.clear()
            goto L_0x0086
        L_0x0120:
            if (r13 != r0) goto L_0x0201
            int r1 = r6.limit
            int r2 = r6.pos
            int r1 = r1 - r2
            r4 = 2
            if (r1 >= r4) goto L_0x0132
            if (r12 != 0) goto L_0x0132
            int r2 = r2 + -1
            r6.pos = r2
            goto L_0x021e
        L_0x0132:
            int r5 = r16.getnext()
            if (r5 != r0) goto L_0x0185
            r13 = 1
            r0 = r16
            r1 = r5
            r2 = r8
            r4 = r9
            r9 = r5
            r5 = r13
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            int r1 = r6.peek(r10)
            int r2 = r6.peek(r11)
            if (r1 == r14) goto L_0x015e
            if (r1 != r15) goto L_0x0153
            if (r2 != r14) goto L_0x0153
            goto L_0x015e
        L_0x0153:
            org.apache.james.mime4j.codec.DecodeMonitor r1 = r6.monitor
            java.lang.String r2 = "Unexpected == encountered"
            java.lang.String r3 = "=="
            r1.warn(r2, r3)
            goto L_0x020d
        L_0x015e:
            org.apache.james.mime4j.codec.DecodeMonitor r3 = r6.monitor
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "== 0x"
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = " 0x"
            r4.append(r1)
            r4.append(r2)
            java.lang.String r1 = r4.toString()
            java.lang.String r2 = "Unexpected ==EOL encountered"
            r3.warn(r2, r1)
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.blanks
            r1.append(r9)
            goto L_0x020d
        L_0x0185:
            r9 = r5
            char r0 = (char) r9
            boolean r1 = java.lang.Character.isWhitespace(r0)
            if (r1 == 0) goto L_0x01a6
            r1 = -1
            r5 = 1
            r0 = r16
            r2 = r8
            r15 = 1
            r4 = r15
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            if (r9 == r14) goto L_0x020d
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.blanks
            r1.append(r13)
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.blanks
            r1.append(r9)
            goto L_0x020d
        L_0x01a6:
            int r13 = r16.getnext()
            int r1 = r6.convert(r9)
            int r2 = r6.convert(r13)
            if (r1 < 0) goto L_0x01c6
            if (r2 >= 0) goto L_0x01b7
            goto L_0x01c6
        L_0x01b7:
            int r0 = r1 << 4
            r1 = r0 | r2
            r5 = 1
            r0 = r16
            r2 = r8
            r9 = 1
            r4 = r9
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            goto L_0x020d
        L_0x01c6:
            org.apache.james.mime4j.codec.DecodeMonitor r1 = r6.monitor
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "leaving ="
            r2.append(r4)
            r2.append(r0)
            char r0 = (char) r13
            r2.append(r0)
            java.lang.String r0 = " as is"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.String r2 = "Malformed encoded value encountered"
            r1.warn(r2, r0)
            r1 = 61
            r5 = 1
            r0 = r16
            r2 = r8
            r14 = 1
            r4 = r14
            int r3 = r0.transfer(r1, r2, r3, r4, r5)
            r15 = 0
            r5 = 0
            r1 = r9
            int r3 = r0.transfer(r1, r2, r3, r4, r5)
            r1 = r13
            r5 = r15
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            goto L_0x020d
        L_0x0201:
            boolean r0 = java.lang.Character.isWhitespace(r13)
            if (r0 == 0) goto L_0x0210
            org.apache.james.mime4j.util.ByteArrayBuffer r0 = r6.blanks
            r0.append(r13)
            r0 = r3
        L_0x020d:
            r9 = 1
            goto L_0x0086
        L_0x0210:
            r1 = r13 & 255(0xff, float:3.57E-43)
            r5 = 1
            r0 = r16
            r2 = r8
            r9 = 1
            r4 = r9
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            goto L_0x0086
        L_0x021e:
            r0 = r3
            r1 = r12
            goto L_0x0035
        L_0x0222:
            r0 = r11
        L_0x0223:
            if (r0 != r2) goto L_0x0226
            return r2
        L_0x0226:
            if (r0 != r11) goto L_0x0008
            byte[] r0 = r6.singleByte
            byte r0 = r0[r10]
            r0 = r0 & 255(0xff, float:3.57E-43)
            return r0
        L_0x022f:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r7)
            throw r0
        L_0x0235:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.james.mime4j.codec.QuotedPrintableInputStream.read():int");
    }

    protected QuotedPrintableInputStream(int i, InputStream inputStream, DecodeMonitor decodeMonitor) {
        this.singleByte = new byte[1];
        this.pos = 0;
        this.limit = 0;
        this.f86in = inputStream;
        this.encoded = new byte[i];
        this.decodedBuf = new ByteArrayBuffer(512);
        this.blanks = new ByteArrayBuffer(512);
        this.closed = false;
        this.monitor = decodeMonitor;
    }

    public QuotedPrintableInputStream(InputStream inputStream) {
        this(2048, inputStream, DecodeMonitor.SILENT);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00df, code lost:
        if (r12 != 10) goto L_0x0123;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e7, code lost:
        if (r6.blanks.length() != 0) goto L_0x00fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00e9, code lost:
        r0 = r17;
        r2 = r18;
        r4 = r8;
        r0 = transfer(10, r2, transfer(13, r2, r3, r4, false), r4, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0104, code lost:
        if (r6.blanks.byteAt(0) == 61) goto L_0x011b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0106, code lost:
        r0 = r17;
        r2 = r18;
        r4 = r8;
        r0 = transfer(10, r2, transfer(13, r2, r3, r4, false), r4, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x011b, code lost:
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x011c, code lost:
        r6.blanks.clear();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0123, code lost:
        if (r12 != 61) goto L_0x020a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0125, code lost:
        r1 = r6.limit;
        r2 = r6.pos;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x012b, code lost:
        if ((r1 - r2) >= 2) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x012d, code lost:
        if (r13 != false) goto L_0x0135;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x012f, code lost:
        r6.pos = r2 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0135, code lost:
        r5 = getnext();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0139, code lost:
        if (r5 != 61) goto L_0x018c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x013b, code lost:
        r16 = r5;
        r0 = transfer(r5, r18, r3, r8, true);
        r1 = peek(0);
        r2 = peek(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0151, code lost:
        if (r1 == 10) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0153, code lost:
        if (r1 != 13) goto L_0x0158;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0155, code lost:
        if (r2 != 10) goto L_0x0158;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0158, code lost:
        r6.monitor.warn("Unexpected == encountered", "==");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0163, code lost:
        r6.monitor.warn("Unexpected ==EOL encountered", "== 0x" + r1 + " 0x" + r2);
        r6.blanks.append(r16);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x018c, code lost:
        r15 = r5;
        r0 = (char) r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0192, code lost:
        if (java.lang.Character.isWhitespace(r0) == false) goto L_0x01ad;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0194, code lost:
        r0 = transfer(-1, r18, r3, r8, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x019f, code lost:
        if (r15 == 10) goto L_0x0086;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01a1, code lost:
        r6.blanks.append(r12);
        r6.blanks.append(r15);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01ad, code lost:
        r12 = getnext();
        r1 = convert(r15);
        r2 = convert(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01b9, code lost:
        if (r1 < 0) goto L_0x01ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01bb, code lost:
        if (r2 >= 0) goto L_0x01be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01be, code lost:
        r0 = transfer((r1 << 4) | r2, r18, r3, r8, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01ce, code lost:
        r6.monitor.warn("Malformed encoded value encountered", "leaving =" + r0 + ((char) r12) + " as is");
        r0 = r17;
        r2 = r18;
        r4 = r8;
        r0 = transfer(r12, r2, transfer(r15, r2, transfer(61, r2, r3, r4, true), r4, false), r4, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x020e, code lost:
        if (java.lang.Character.isWhitespace(r12) == false) goto L_0x0218;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0210, code lost:
        r6.blanks.append(r12);
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0218, code lost:
        r0 = transfer(r12 & 255, r18, r3, r8, true);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(byte[] r18, int r19, int r20) throws java.io.IOException {
        /*
            r17 = this;
            r6 = r17
            r7 = r19
            boolean r0 = r6.closed
            if (r0 != 0) goto L_0x022d
            int r8 = r7 + r20
            org.apache.james.mime4j.util.ByteArrayBuffer r0 = r6.decodedBuf
            int r0 = r0.length()
            r9 = 0
            if (r0 <= 0) goto L_0x0031
            org.apache.james.mime4j.util.ByteArrayBuffer r0 = r6.decodedBuf
            int r0 = r0.length()
            int r1 = r8 - r7
            int r0 = java.lang.Math.min(r0, r1)
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.decodedBuf
            byte[] r1 = r1.buffer()
            r10 = r18
            java.lang.System.arraycopy(r1, r9, r10, r7, r0)
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.decodedBuf
            r1.remove(r9, r0)
            int r0 = r0 + r7
            goto L_0x0034
        L_0x0031:
            r10 = r18
            r0 = r7
        L_0x0034:
            r1 = r9
        L_0x0035:
            if (r0 >= r8) goto L_0x022a
            int r2 = r6.limit
            int r3 = r6.pos
            int r4 = r2 - r3
            r5 = 3
            r11 = 1
            r12 = -1
            if (r4 >= r5) goto L_0x0074
            if (r3 >= r2) goto L_0x0053
            byte[] r1 = r6.encoded
            java.lang.System.arraycopy(r1, r3, r1, r9, r4)
            int r1 = r6.limit
            int r2 = r6.pos
            int r1 = r1 - r2
            r6.limit = r1
            r6.pos = r9
            goto L_0x0057
        L_0x0053:
            r6.limit = r9
            r6.pos = r9
        L_0x0057:
            byte[] r1 = r6.encoded
            int r2 = r1.length
            int r3 = r6.limit
            int r2 = r2 - r3
            if (r2 <= 0) goto L_0x006d
            java.io.InputStream r4 = r6.f86in
            int r1 = r4.read(r1, r3, r2)
            if (r1 <= 0) goto L_0x006e
            int r2 = r6.limit
            int r2 = r2 + r1
            r6.limit = r2
            goto L_0x006e
        L_0x006d:
            r1 = r9
        L_0x006e:
            if (r1 != r12) goto L_0x0072
            r13 = r11
            goto L_0x0075
        L_0x0072:
            r13 = r9
            goto L_0x0075
        L_0x0074:
            r13 = r1
        L_0x0075:
            int r1 = r6.limit
            int r2 = r6.pos
            int r1 = r1 - r2
            if (r1 != 0) goto L_0x0086
            if (r13 == 0) goto L_0x0086
            if (r0 != r7) goto L_0x0082
            goto L_0x022c
        L_0x0082:
            int r12 = r0 - r7
            goto L_0x022c
        L_0x0086:
            r3 = r0
            r0 = r9
        L_0x0088:
            int r1 = r6.pos
            int r2 = r6.limit
            if (r1 >= r2) goto L_0x0226
            if (r3 >= r8) goto L_0x0226
            byte[] r2 = r6.encoded
            int r4 = r1 + 1
            r6.pos = r4
            byte r1 = r2[r1]
            r12 = r1 & 255(0xff, float:3.57E-43)
            r14 = 10
            if (r0 == 0) goto L_0x00c0
            if (r12 == r14) goto L_0x00c0
            org.apache.james.mime4j.codec.DecodeMonitor r0 = r6.monitor
            java.lang.String r1 = "Found CR without LF"
            java.lang.String r2 = "Leaving it as is"
            boolean r0 = r0.warn(r1, r2)
            if (r0 != 0) goto L_0x00ba
            r1 = 13
            r5 = 0
            r0 = r17
            r2 = r18
            r4 = r8
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            r3 = r0
            goto L_0x00d7
        L_0x00ba:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r1)
            throw r0
        L_0x00c0:
            if (r0 != 0) goto L_0x00d7
            if (r12 != r14) goto L_0x00d7
            org.apache.james.mime4j.codec.DecodeMonitor r0 = r6.monitor
            java.lang.String r1 = "Found LF without CR"
            java.lang.String r2 = "Translating to CRLF"
            boolean r0 = r0.warn(r1, r2)
            if (r0 != 0) goto L_0x00d1
            goto L_0x00d7
        L_0x00d1:
            java.io.IOException r0 = new java.io.IOException
            r0.<init>(r1)
            throw r0
        L_0x00d7:
            r15 = 13
            if (r12 != r15) goto L_0x00dd
            r0 = r11
            goto L_0x0088
        L_0x00dd:
            r0 = 61
            if (r12 != r14) goto L_0x0123
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.blanks
            int r1 = r1.length()
            if (r1 != 0) goto L_0x00fe
            r1 = 13
            r12 = 0
            r5 = 0
            r0 = r17
            r2 = r18
            r4 = r8
            int r3 = r0.transfer(r1, r2, r3, r4, r5)
            r1 = 10
            r5 = r12
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            goto L_0x011c
        L_0x00fe:
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.blanks
            byte r1 = r1.byteAt(r9)
            if (r1 == r0) goto L_0x011b
            r1 = 13
            r12 = 0
            r5 = 0
            r0 = r17
            r2 = r18
            r4 = r8
            int r3 = r0.transfer(r1, r2, r3, r4, r5)
            r1 = 10
            r5 = r12
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            goto L_0x011c
        L_0x011b:
            r0 = r3
        L_0x011c:
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.blanks
            r1.clear()
            goto L_0x0086
        L_0x0123:
            if (r12 != r0) goto L_0x020a
            int r1 = r6.limit
            int r2 = r6.pos
            int r1 = r1 - r2
            r4 = 2
            if (r1 >= r4) goto L_0x0135
            if (r13 != 0) goto L_0x0135
            int r2 = r2 + -1
            r6.pos = r2
            goto L_0x0226
        L_0x0135:
            int r5 = r17.getnext()
            if (r5 != r0) goto L_0x018c
            r12 = 1
            r0 = r17
            r1 = r5
            r2 = r18
            r4 = r8
            r16 = r5
            r5 = r12
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            int r1 = r6.peek(r9)
            int r2 = r6.peek(r11)
            if (r1 == r14) goto L_0x0163
            if (r1 != r15) goto L_0x0158
            if (r2 != r14) goto L_0x0158
            goto L_0x0163
        L_0x0158:
            org.apache.james.mime4j.codec.DecodeMonitor r1 = r6.monitor
            java.lang.String r2 = "Unexpected == encountered"
            java.lang.String r3 = "=="
            r1.warn(r2, r3)
            goto L_0x0086
        L_0x0163:
            org.apache.james.mime4j.codec.DecodeMonitor r3 = r6.monitor
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "== 0x"
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = " 0x"
            r4.append(r1)
            r4.append(r2)
            java.lang.String r1 = r4.toString()
            java.lang.String r2 = "Unexpected ==EOL encountered"
            r3.warn(r2, r1)
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.blanks
            r15 = r16
            r1.append(r15)
            goto L_0x0086
        L_0x018c:
            r15 = r5
            char r0 = (char) r15
            boolean r1 = java.lang.Character.isWhitespace(r0)
            if (r1 == 0) goto L_0x01ad
            r1 = -1
            r5 = 1
            r0 = r17
            r2 = r18
            r4 = r8
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            if (r15 == r14) goto L_0x0086
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.blanks
            r1.append(r12)
            org.apache.james.mime4j.util.ByteArrayBuffer r1 = r6.blanks
            r1.append(r15)
            goto L_0x0086
        L_0x01ad:
            int r12 = r17.getnext()
            int r1 = r6.convert(r15)
            int r2 = r6.convert(r12)
            if (r1 < 0) goto L_0x01ce
            if (r2 >= 0) goto L_0x01be
            goto L_0x01ce
        L_0x01be:
            int r0 = r1 << 4
            r1 = r0 | r2
            r5 = 1
            r0 = r17
            r2 = r18
            r4 = r8
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            goto L_0x0086
        L_0x01ce:
            org.apache.james.mime4j.codec.DecodeMonitor r1 = r6.monitor
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r4 = "leaving ="
            r2.append(r4)
            r2.append(r0)
            char r0 = (char) r12
            r2.append(r0)
            java.lang.String r0 = " as is"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.String r2 = "Malformed encoded value encountered"
            r1.warn(r2, r0)
            r1 = 61
            r5 = 1
            r0 = r17
            r2 = r18
            r4 = r8
            int r3 = r0.transfer(r1, r2, r3, r4, r5)
            r14 = 0
            r5 = 0
            r1 = r15
            int r3 = r0.transfer(r1, r2, r3, r4, r5)
            r1 = r12
            r5 = r14
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            goto L_0x0086
        L_0x020a:
            boolean r0 = java.lang.Character.isWhitespace(r12)
            if (r0 == 0) goto L_0x0218
            org.apache.james.mime4j.util.ByteArrayBuffer r0 = r6.blanks
            r0.append(r12)
            r0 = r3
            goto L_0x0086
        L_0x0218:
            r1 = r12 & 255(0xff, float:3.57E-43)
            r5 = 1
            r0 = r17
            r2 = r18
            r4 = r8
            int r0 = r0.transfer(r1, r2, r3, r4, r5)
            goto L_0x0086
        L_0x0226:
            r0 = r3
            r1 = r13
            goto L_0x0035
        L_0x022a:
            int r12 = r8 - r7
        L_0x022c:
            return r12
        L_0x022d:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Stream has been closed"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.james.mime4j.codec.QuotedPrintableInputStream.read(byte[], int, int):int");
    }
}
