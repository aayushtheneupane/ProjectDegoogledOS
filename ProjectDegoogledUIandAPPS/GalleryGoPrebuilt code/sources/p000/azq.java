package p000;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: azq */
/* compiled from: PG */
final class azq {

    /* renamed from: a */
    public final ByteBuffer f1913a;

    public azq(byte[] bArr, int i) {
        this.f1913a = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final short mo1746b(int i) {
        if (m1978a(i, 2)) {
            return this.f1913a.getShort(i);
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final int mo1745a(int i) {
        if (m1978a(i, 4)) {
            return this.f1913a.getInt(i);
        }
        return -1;
    }

    /* renamed from: a */
    private final boolean m1978a(int i, int i2) {
        return this.f1913a.remaining() - i >= i2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final int mo1744a() {
        return this.f1913a.remaining();
    }
}
