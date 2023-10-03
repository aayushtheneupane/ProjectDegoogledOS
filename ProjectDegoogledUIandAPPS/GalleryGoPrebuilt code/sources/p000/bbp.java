package p000;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Queue;

/* renamed from: bbp */
/* compiled from: PG */
final class bbp {

    /* renamed from: a */
    private final Queue f1997a = bfp.m2429a(0);

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized aqc mo1778a(ByteBuffer byteBuffer) {
        aqc aqc;
        aqc = (aqc) this.f1997a.poll();
        if (aqc == null) {
            aqc = new aqc();
        }
        aqc.f1427b = null;
        Arrays.fill(aqc.f1426a, (byte) 0);
        aqc.f1428c = new aqb();
        aqc.f1429d = 0;
        aqc.f1427b = byteBuffer.asReadOnlyBuffer();
        aqc.f1427b.position(0);
        aqc.f1427b.order(ByteOrder.LITTLE_ENDIAN);
        return aqc;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final synchronized void mo1779a(aqc aqc) {
        aqc.f1427b = null;
        aqc.f1428c = null;
        this.f1997a.offer(aqc);
    }
}
