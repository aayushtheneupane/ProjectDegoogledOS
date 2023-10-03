package p000;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* renamed from: bbb */
/* compiled from: PG */
final class bbb implements aqx {

    /* renamed from: a */
    private final ByteBuffer f1987a = ByteBuffer.allocate(4);

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1498a(byte[] bArr, Object obj, MessageDigest messageDigest) {
        Integer num = (Integer) obj;
        if (num != null) {
            messageDigest.update(bArr);
            synchronized (this.f1987a) {
                this.f1987a.position(0);
                messageDigest.update(this.f1987a.putInt(num.intValue()).array());
            }
        }
    }
}
