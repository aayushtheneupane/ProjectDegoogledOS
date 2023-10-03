package p000;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: hzd */
/* compiled from: PG */
abstract class hzd extends hzf {
    public hzd() {
        ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo8285b(byte[] bArr, int i) {
        throw null;
    }

    /* renamed from: a */
    public final hzk mo8283a(byte[] bArr) {
        ife.m12898e((Object) bArr);
        mo8285b(bArr, bArr.length);
        return this;
    }

    /* renamed from: a */
    public final hzk mo8284a(byte[] bArr, int i) {
        ife.m12874b(0, i, bArr.length);
        mo8285b(bArr, i);
        return this;
    }
}
