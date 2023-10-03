package p000;

import java.io.FilterOutputStream;
import java.io.OutputStream;

/* renamed from: fyk */
/* compiled from: PG */
public class fyk extends FilterOutputStream {
    public fyk(OutputStream outputStream) {
        super(outputStream);
    }

    public void write(byte[] bArr) {
        this.out.write(bArr);
    }

    public void write(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
    }
}
