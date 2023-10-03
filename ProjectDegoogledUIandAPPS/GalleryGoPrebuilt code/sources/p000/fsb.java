package p000;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: fsb */
/* compiled from: PG */
final class fsb extends FilterInputStream {

    /* renamed from: a */
    public int f10345a = 0;

    /* renamed from: b */
    public final ByteBuffer f10346b;

    /* renamed from: c */
    private final byte[] f10347c;

    protected fsb(InputStream inputStream) {
        super(inputStream);
        byte[] bArr = new byte[8];
        this.f10347c = bArr;
        this.f10346b = ByteBuffer.wrap(bArr);
    }

    public final int read() {
        int i;
        int read = this.in.read();
        int i2 = this.f10345a;
        if (read >= 0) {
            i = 1;
        } else {
            i = 0;
        }
        this.f10345a = i2 + i;
        return read;
    }

    public final int read(byte[] bArr) {
        int read = this.in.read(bArr);
        this.f10345a += Math.max(read, 0);
        return read;
    }

    public final int read(byte[] bArr, int i, int i2) {
        int read = this.in.read(bArr, i, i2);
        this.f10345a += Math.max(read, 0);
        return read;
    }

    /* renamed from: c */
    public final int mo6096c() {
        mo6094a(this.f10347c, 4);
        this.f10346b.rewind();
        return this.f10346b.getInt();
    }

    /* renamed from: a */
    public final void mo6094a(byte[] bArr, int i) {
        if (read(bArr, 0, i) != i) {
            throw new EOFException();
        }
    }

    /* renamed from: a */
    public final short mo6092a() {
        mo6094a(this.f10347c, 2);
        this.f10346b.rewind();
        return this.f10346b.getShort();
    }

    /* renamed from: d */
    public final long mo6097d() {
        return ((long) mo6096c()) & 4294967295L;
    }

    /* renamed from: b */
    public final int mo6095b() {
        return (char) mo6092a();
    }

    /* renamed from: a */
    public final void mo6093a(ByteOrder byteOrder) {
        this.f10346b.order(byteOrder);
    }

    public final long skip(long j) {
        long skip = this.in.skip(j);
        this.f10345a = (int) (((long) this.f10345a) + skip);
        return skip;
    }
}
