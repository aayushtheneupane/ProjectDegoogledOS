package p000;

import java.io.OutputStream;

/* renamed from: arg */
/* compiled from: PG */
public final class arg extends OutputStream {

    /* renamed from: a */
    private final OutputStream f1482a;

    /* renamed from: b */
    private byte[] f1483b;

    /* renamed from: c */
    private final aui f1484c;

    /* renamed from: d */
    private int f1485d;

    public arg(OutputStream outputStream, aui aui) {
        this.f1482a = outputStream;
        this.f1484c = aui;
        this.f1483b = (byte[]) aui.mo1634a(65536, byte[].class);
    }

    /* JADX INFO: finally extract failed */
    public final void close() {
        try {
            flush();
            this.f1482a.close();
            byte[] bArr = this.f1483b;
            if (bArr != null) {
                this.f1484c.mo1638a((Object) bArr);
                this.f1483b = null;
            }
        } catch (Throwable th) {
            this.f1482a.close();
            throw th;
        }
    }

    public final void flush() {
        m1491a();
        this.f1482a.flush();
    }

    /* renamed from: a */
    private final void m1491a() {
        int i = this.f1485d;
        if (i > 0) {
            this.f1482a.write(this.f1483b, 0, i);
            this.f1485d = 0;
        }
    }

    /* renamed from: b */
    private final void m1492b() {
        if (this.f1485d == this.f1483b.length) {
            m1491a();
        }
    }

    public final void write(int i) {
        byte[] bArr = this.f1483b;
        int i2 = this.f1485d;
        this.f1485d = i2 + 1;
        bArr[i2] = (byte) i;
        m1492b();
    }

    public final void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    public final void write(byte[] bArr, int i, int i2) {
        int i3 = 0;
        do {
            int i4 = i2 - i3;
            int i5 = i + i3;
            int i6 = this.f1485d;
            if (i6 == 0 && i4 >= this.f1483b.length) {
                this.f1482a.write(bArr, i5, i4);
                return;
            }
            int min = Math.min(i4, this.f1483b.length - i6);
            System.arraycopy(bArr, i5, this.f1483b, this.f1485d, min);
            this.f1485d += min;
            i3 += min;
            m1492b();
        } while (i3 < i2);
    }
}
