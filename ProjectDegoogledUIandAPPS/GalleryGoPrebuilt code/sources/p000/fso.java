package p000;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: fso */
/* compiled from: PG */
final class fso extends FilterOutputStream {

    /* renamed from: a */
    private final ByteBuffer f10537a = ByteBuffer.allocate(4);

    public fso(OutputStream outputStream) {
        super(outputStream);
    }

    /* renamed from: a */
    public final void mo6155a(ByteOrder byteOrder) {
        this.f10537a.order(byteOrder);
    }

    public final void write(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
    }

    /* renamed from: a */
    public final void mo6154a(int i) {
        this.f10537a.rewind();
        this.f10537a.putInt(i);
        this.out.write(this.f10537a.array());
    }

    /* renamed from: a */
    public final void mo6156a(short s) {
        this.f10537a.rewind();
        this.f10537a.putShort(s);
        this.out.write(this.f10537a.array(), 0, 2);
    }
}
