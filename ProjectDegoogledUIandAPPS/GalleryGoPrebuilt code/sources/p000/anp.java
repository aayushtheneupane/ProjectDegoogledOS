package p000;

import java.io.OutputStream;

/* renamed from: anp */
/* compiled from: PG */
public final class anp extends OutputStream {

    /* renamed from: a */
    public int f1202a = 0;

    /* renamed from: b */
    private final OutputStream f1203b;

    public anp(OutputStream outputStream) {
        this.f1203b = outputStream;
    }

    public final void write(int i) {
        this.f1203b.write(i);
        this.f1202a++;
    }

    public final void write(byte[] bArr) {
        this.f1203b.write(bArr);
        this.f1202a += bArr.length;
    }

    public final void write(byte[] bArr, int i, int i2) {
        this.f1203b.write(bArr, i, i2);
        this.f1202a += i2;
    }
}
