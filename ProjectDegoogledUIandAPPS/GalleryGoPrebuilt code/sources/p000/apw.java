package p000;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/* renamed from: apw */
/* compiled from: PG */
final class apw implements Closeable {

    /* renamed from: a */
    public final Charset f1395a;

    /* renamed from: b */
    public int f1396b;

    /* renamed from: c */
    private final InputStream f1397c;

    /* renamed from: d */
    private byte[] f1398d;

    /* renamed from: e */
    private int f1399e;

    public apw(InputStream inputStream, Charset charset) {
        if (charset == null) {
            throw null;
        } else if (charset.equals(apx.f1400a)) {
            this.f1397c = inputStream;
            this.f1395a = charset;
            this.f1398d = new byte[8192];
        } else {
            throw new IllegalArgumentException("Unsupported encoding");
        }
    }

    public final void close() {
        synchronized (this.f1397c) {
            if (this.f1398d != null) {
                this.f1398d = null;
                this.f1397c.close();
            }
        }
    }

    /* renamed from: b */
    private final void m1430b() {
        InputStream inputStream = this.f1397c;
        byte[] bArr = this.f1398d;
        int read = inputStream.read(bArr, 0, bArr.length);
        if (read != -1) {
            this.f1399e = 0;
            this.f1396b = read;
            return;
        }
        throw new EOFException();
    }

    /* renamed from: a */
    public final String mo1471a() {
        int i;
        byte[] bArr;
        int i2;
        synchronized (this.f1397c) {
            if (this.f1398d != null) {
                if (this.f1399e >= this.f1396b) {
                    m1430b();
                }
                int i3 = this.f1399e;
                while (true) {
                    int i4 = this.f1396b;
                    if (i3 != i4) {
                        byte[] bArr2 = this.f1398d;
                        if (bArr2[i3] != 10) {
                            i3++;
                        } else {
                            int i5 = this.f1399e;
                            if (i3 != i5) {
                                i2 = i3 - 1;
                                if (bArr2[i2] == 13) {
                                    String str = new String(bArr2, i5, i2 - i5, this.f1395a.name());
                                    this.f1399e = i3 + 1;
                                    return str;
                                }
                            }
                            i2 = i3;
                            String str2 = new String(bArr2, i5, i2 - i5, this.f1395a.name());
                            this.f1399e = i3 + 1;
                            return str2;
                        }
                    } else {
                        apv apv = new apv(this, (i4 - this.f1399e) + 80);
                        loop1:
                        while (true) {
                            byte[] bArr3 = this.f1398d;
                            int i6 = this.f1399e;
                            apv.write(bArr3, i6, this.f1396b - i6);
                            this.f1396b = -1;
                            m1430b();
                            i = this.f1399e;
                            while (true) {
                                if (i != this.f1396b) {
                                    bArr = this.f1398d;
                                    if (bArr[i] == 10) {
                                        break loop1;
                                    }
                                    i++;
                                }
                            }
                        }
                        int i7 = this.f1399e;
                        if (i != i7) {
                            apv.write(bArr, i7, i - i7);
                        }
                        this.f1399e = i + 1;
                        String byteArrayOutputStream = apv.toString();
                        return byteArrayOutputStream;
                    }
                }
            } else {
                throw new IOException("LineReader is closed");
            }
        }
    }
}
