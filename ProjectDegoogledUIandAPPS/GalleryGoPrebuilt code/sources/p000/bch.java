package p000;

import java.nio.ByteBuffer;

/* renamed from: bch */
/* compiled from: PG */
public final class bch implements bci {
    /* renamed from: a */
    public final aua mo1806a(aua aua, aqz aqz) {
        byte[] bArr;
        ByteBuffer b = ((bbt) aua.mo1605b()).mo1784b();
        int i = bfd.f2200a;
        bfc bfc = null;
        if (!b.isReadOnly() && b.hasArray()) {
            bfc = new bfc(b.array(), b.arrayOffset(), b.limit());
        }
        if (bfc != null && bfc.f2197a == 0 && bfc.f2198b == bfc.f2199c.length) {
            bArr = b.array();
        } else {
            ByteBuffer asReadOnlyBuffer = b.asReadOnlyBuffer();
            byte[] bArr2 = new byte[asReadOnlyBuffer.limit()];
            asReadOnlyBuffer.position(0);
            asReadOnlyBuffer.get(bArr2);
            bArr = bArr2;
        }
        return new bbi(bArr);
    }
}
