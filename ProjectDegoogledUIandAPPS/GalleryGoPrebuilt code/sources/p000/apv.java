package p000;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/* renamed from: apv */
/* compiled from: PG */
final class apv extends ByteArrayOutputStream {

    /* renamed from: a */
    private final /* synthetic */ apw f1394a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public apv(apw apw, int i) {
        super(i);
        this.f1394a = apw;
    }

    public final String toString() {
        try {
            return new String(this.buf, 0, (this.count <= 0 || this.buf[this.count + -1] != 13) ? this.count : this.count - 1, this.f1394a.f1395a.name());
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }
}
