package p000;

import java.io.FilterInputStream;
import java.io.InputStream;

/* renamed from: fyj */
/* compiled from: PG */
public class fyj extends FilterInputStream {
    public fyj(InputStream inputStream) {
        super(inputStream);
    }

    public int read(byte[] bArr) {
        return this.in.read(bArr);
    }
}
