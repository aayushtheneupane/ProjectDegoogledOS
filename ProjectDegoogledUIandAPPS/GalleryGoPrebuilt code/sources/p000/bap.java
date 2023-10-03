package p000;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: bap */
/* compiled from: PG */
public final class bap extends FilterInputStream {

    /* renamed from: a */
    private volatile byte[] f1963a;

    /* renamed from: b */
    private int f1964b;

    /* renamed from: c */
    private int f1965c;

    /* renamed from: d */
    private int f1966d = -1;

    /* renamed from: e */
    private int f1967e;

    /* renamed from: f */
    private final aui f1968f;

    public bap(InputStream inputStream, aui aui) {
        super(inputStream);
        this.f1968f = aui;
        this.f1963a = (byte[]) aui.mo1634a(65536, byte[].class);
    }

    public final boolean markSupported() {
        return true;
    }

    public final synchronized int available() {
        InputStream inputStream;
        inputStream = this.in;
        if (this.f1963a == null || inputStream == null) {
            throw m2052c();
        }
        return (this.f1964b - this.f1967e) + inputStream.available();
    }

    public final void close() {
        if (this.f1963a != null) {
            this.f1968f.mo1638a((Object) this.f1963a);
            this.f1963a = null;
        }
        InputStream inputStream = this.in;
        this.in = null;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    /* renamed from: a */
    private final int m2051a(InputStream inputStream, byte[] bArr) {
        int length;
        int i = this.f1966d;
        if (i != -1) {
            int i2 = this.f1967e;
            int i3 = this.f1965c;
            if (i2 - i < i3) {
                if (i == 0 && i3 > (length = bArr.length) && this.f1964b == length) {
                    int i4 = length + length;
                    if (i4 <= i3) {
                        i3 = i4;
                    }
                    byte[] bArr2 = (byte[]) this.f1968f.mo1634a(i3, byte[].class);
                    System.arraycopy(bArr, 0, bArr2, 0, length);
                    this.f1963a = bArr2;
                    this.f1968f.mo1638a((Object) bArr);
                    bArr = bArr2;
                } else if (i > 0) {
                    System.arraycopy(bArr, i, bArr, 0, bArr.length - i);
                }
                int i5 = this.f1967e - this.f1966d;
                this.f1967e = i5;
                this.f1966d = 0;
                this.f1964b = 0;
                int read = inputStream.read(bArr, i5, bArr.length - i5);
                this.f1964b = read > 0 ? this.f1967e + read : this.f1967e;
                return read;
            }
        }
        int read2 = inputStream.read(bArr);
        if (read2 > 0) {
            this.f1966d = -1;
            this.f1967e = 0;
            this.f1964b = read2;
        }
        return read2;
    }

    /* renamed from: a */
    public final synchronized void mo1760a() {
        this.f1965c = this.f1963a.length;
    }

    public final synchronized void mark(int i) {
        this.f1965c = Math.max(this.f1965c, i);
        this.f1966d = this.f1967e;
    }

    public final synchronized int read() {
        byte[] bArr = this.f1963a;
        InputStream inputStream = this.in;
        if (bArr == null || inputStream == null) {
            throw m2052c();
        } else if (this.f1967e >= this.f1964b && m2051a(inputStream, bArr) == -1) {
            return -1;
        } else {
            if (bArr != this.f1963a) {
                bArr = this.f1963a;
                if (bArr == null) {
                    throw m2052c();
                }
            }
            int i = this.f1964b;
            int i2 = this.f1967e;
            if (i - i2 <= 0) {
                return -1;
            }
            this.f1967e = i2 + 1;
            return bArr[i2] & 255;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0040, code lost:
        return r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x007f, code lost:
        return r3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0071 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized int read(byte[] r6, int r7, int r8) {
        /*
            r5 = this;
            monitor-enter(r5)
            byte[] r0 = r5.f1963a     // Catch:{ all -> 0x008d }
            if (r0 == 0) goto L_0x0088
            if (r8 == 0) goto L_0x0085
            java.io.InputStream r1 = r5.in     // Catch:{ all -> 0x008d }
            if (r1 == 0) goto L_0x0080
            int r2 = r5.f1967e     // Catch:{ all -> 0x008d }
            int r3 = r5.f1964b     // Catch:{ all -> 0x008d }
            if (r2 < r3) goto L_0x0013
            r2 = r8
            goto L_0x002d
        L_0x0013:
            int r3 = r3 - r2
            if (r3 >= r8) goto L_0x0017
            goto L_0x0018
        L_0x0017:
            r3 = r8
        L_0x0018:
            java.lang.System.arraycopy(r0, r2, r6, r7, r3)     // Catch:{ all -> 0x008d }
            int r2 = r5.f1967e     // Catch:{ all -> 0x008d }
            int r2 = r2 + r3
            r5.f1967e = r2     // Catch:{ all -> 0x008d }
            if (r3 == r8) goto L_0x007e
            int r2 = r1.available()     // Catch:{ all -> 0x008d }
            if (r2 == 0) goto L_0x007e
            int r7 = r7 + r3
            int r2 = r8 - r3
        L_0x002d:
            int r3 = r5.f1966d     // Catch:{ all -> 0x008d }
            r4 = -1
            if (r3 != r4) goto L_0x0044
            int r3 = r0.length     // Catch:{ all -> 0x008d }
            if (r2 >= r3) goto L_0x0036
            goto L_0x0044
        L_0x0036:
            int r3 = r1.read(r6, r7, r2)     // Catch:{ all -> 0x008d }
            if (r3 != r4) goto L_0x0043
            if (r2 == r8) goto L_0x0041
        L_0x003e:
            int r8 = r8 - r2
        L_0x003f:
            monitor-exit(r5)
            return r8
        L_0x0041:
            monitor-exit(r5)
            return r4
        L_0x0043:
            goto L_0x0065
        L_0x0044:
            int r3 = r5.m2051a(r1, r0)     // Catch:{ all -> 0x008d }
            if (r3 == r4) goto L_0x0077
            byte[] r3 = r5.f1963a     // Catch:{ all -> 0x008d }
            if (r0 != r3) goto L_0x004f
            goto L_0x0053
        L_0x004f:
            byte[] r0 = r5.f1963a     // Catch:{ all -> 0x008d }
            if (r0 == 0) goto L_0x0072
        L_0x0053:
            int r3 = r5.f1964b     // Catch:{ all -> 0x008d }
            int r4 = r5.f1967e     // Catch:{ all -> 0x008d }
            int r3 = r3 - r4
            if (r3 >= r2) goto L_0x005b
            goto L_0x005c
        L_0x005b:
            r3 = r2
        L_0x005c:
            java.lang.System.arraycopy(r0, r4, r6, r7, r3)     // Catch:{ all -> 0x008d }
            int r4 = r5.f1967e     // Catch:{ all -> 0x008d }
            int r4 = r4 + r3
            r5.f1967e = r4     // Catch:{ all -> 0x008d }
            goto L_0x0043
        L_0x0065:
            int r2 = r2 - r3
            if (r2 == 0) goto L_0x0071
            int r4 = r1.available()     // Catch:{ all -> 0x008d }
            if (r4 == 0) goto L_0x003e
            int r7 = r7 + r3
            goto L_0x002d
        L_0x0071:
            goto L_0x003f
        L_0x0072:
            java.io.IOException r6 = m2052c()     // Catch:{ all -> 0x008d }
            throw r6     // Catch:{ all -> 0x008d }
        L_0x0077:
            if (r2 == r8) goto L_0x007c
            int r8 = r8 - r2
            monitor-exit(r5)
            return r8
        L_0x007c:
            monitor-exit(r5)
            return r4
        L_0x007e:
            monitor-exit(r5)
            return r3
        L_0x0080:
            java.io.IOException r6 = m2052c()     // Catch:{ all -> 0x008d }
            throw r6     // Catch:{ all -> 0x008d }
        L_0x0085:
            r6 = 0
            monitor-exit(r5)
            return r6
        L_0x0088:
            java.io.IOException r6 = m2052c()     // Catch:{ all -> 0x008d }
            throw r6     // Catch:{ all -> 0x008d }
        L_0x008d:
            r6 = move-exception
            monitor-exit(r5)
            goto L_0x0091
        L_0x0090:
            throw r6
        L_0x0091:
            goto L_0x0090
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bap.read(byte[], int, int):int");
    }

    /* renamed from: b */
    public final synchronized void mo1762b() {
        if (this.f1963a != null) {
            this.f1968f.mo1638a((Object) this.f1963a);
            this.f1963a = null;
        }
    }

    public final synchronized void reset() {
        if (this.f1963a != null) {
            int i = this.f1966d;
            if (i != -1) {
                this.f1967e = i;
            } else {
                int i2 = this.f1967e;
                int i3 = this.f1965c;
                StringBuilder sb = new StringBuilder(66);
                sb.append("Mark has been invalidated, pos: ");
                sb.append(i2);
                sb.append(" markLimit: ");
                sb.append(i3);
                throw new bao(sb.toString());
            }
        } else {
            throw new IOException("Stream is closed");
        }
    }

    public final synchronized long skip(long j) {
        if (j < 1) {
            return 0;
        }
        byte[] bArr = this.f1963a;
        if (bArr != null) {
            InputStream inputStream = this.in;
            if (inputStream != null) {
                int i = this.f1964b;
                int i2 = this.f1967e;
                if (((long) (i - i2)) < j) {
                    long j2 = ((long) i) - ((long) i2);
                    this.f1967e = i;
                    if (this.f1966d != -1) {
                        if (j <= ((long) this.f1965c)) {
                            if (m2051a(inputStream, bArr) == -1) {
                                return j2;
                            }
                            int i3 = this.f1964b;
                            int i4 = this.f1967e;
                            if (((long) (i3 - i4)) >= j - j2) {
                                this.f1967e = (int) ((((long) i4) + j) - j2);
                                return j;
                            }
                            this.f1967e = i3;
                            return (j2 + ((long) i3)) - ((long) i4);
                        }
                    }
                    return j2 + inputStream.skip(j - j2);
                }
                this.f1967e = (int) (((long) i2) + j);
                return j;
            }
            throw m2052c();
        }
        throw m2052c();
    }

    /* renamed from: c */
    private static IOException m2052c() {
        throw new IOException("BufferedInputStream is closed");
    }
}
