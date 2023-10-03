package p000;

import java.io.InputStream;

/* renamed from: ikw */
/* compiled from: PG */
final class ikw extends InputStream {

    /* renamed from: a */
    private ikv f14413a;

    /* renamed from: b */
    private ihs f14414b;

    /* renamed from: c */
    private int f14415c;

    /* renamed from: d */
    private int f14416d;

    /* renamed from: e */
    private int f14417e;

    /* renamed from: f */
    private int f14418f;

    /* renamed from: g */
    private final /* synthetic */ ikx f14419g;

    public ikw(ikx ikx) {
        this.f14419g = ikx;
        m13875a();
    }

    public final int available() {
        return this.f14419g.f14421d - (this.f14417e + this.f14416d);
    }

    public final boolean markSupported() {
        return true;
    }

    /* renamed from: b */
    private final void m13876b() {
        int i;
        if (this.f14414b != null && this.f14416d == (i = this.f14415c)) {
            this.f14417e += i;
            this.f14416d = 0;
            if (this.f14413a.hasNext()) {
                ihs a = this.f14413a.next();
                this.f14414b = a;
                this.f14415c = a.mo8597a();
                return;
            }
            this.f14414b = null;
            this.f14415c = 0;
        }
    }

    /* renamed from: a */
    private final void m13875a() {
        ikv ikv = new ikv(this.f14419g);
        this.f14413a = ikv;
        ihs a = ikv.next();
        this.f14414b = a;
        this.f14415c = a.mo8597a();
        this.f14416d = 0;
        this.f14417e = 0;
    }

    public final void mark(int i) {
        this.f14418f = this.f14417e + this.f14416d;
    }

    public final int read() {
        m13876b();
        ihs ihs = this.f14414b;
        if (ihs == null) {
            return -1;
        }
        int i = this.f14416d;
        this.f14416d = i + 1;
        return ihs.mo8596a(i) & 255;
    }

    public final int read(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw null;
        } else if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        } else {
            int a = m13874a(bArr, i, i2);
            if (a == 0) {
                return -1;
            }
            return a;
        }
    }

    /* renamed from: a */
    private final int m13874a(byte[] bArr, int i, int i2) {
        int i3 = i2;
        while (i3 > 0) {
            m13876b();
            if (this.f14414b == null) {
                break;
            }
            int min = Math.min(this.f14415c - this.f14416d, i3);
            if (bArr != null) {
                this.f14414b.mo8620b(bArr, this.f14416d, i, min);
                i += min;
            }
            this.f14416d += min;
            i3 -= min;
        }
        return i2 - i3;
    }

    public final synchronized void reset() {
        m13875a();
        m13874a((byte[]) null, 0, this.f14418f);
    }

    public final long skip(long j) {
        if (j >= 0) {
            if (j > 2147483647L) {
                j = 2147483647L;
            }
            return (long) m13874a((byte[]) null, 0, (int) j);
        }
        throw new IndexOutOfBoundsException();
    }
}
