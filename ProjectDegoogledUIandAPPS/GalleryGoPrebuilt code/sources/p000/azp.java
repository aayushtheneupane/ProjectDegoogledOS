package p000;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* renamed from: azp */
/* compiled from: PG */
final class azp implements azs {

    /* renamed from: a */
    private final ByteBuffer f1912a;

    public azp(ByteBuffer byteBuffer) {
        this.f1912a = byteBuffer;
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
    }

    /* renamed from: b */
    public final int mo1743b() {
        return (mo1742a() << 8) | mo1742a();
    }

    /* renamed from: a */
    public final short mo1742a() {
        if (this.f1912a.remaining() > 0) {
            return (short) (this.f1912a.get() & 255);
        }
        throw new azr();
    }

    /* renamed from: a */
    public final long mo1741a(long j) {
        int min = (int) Math.min((long) this.f1912a.remaining(), j);
        ByteBuffer byteBuffer = this.f1912a;
        byteBuffer.position(byteBuffer.position() + min);
        return (long) min;
    }
}
