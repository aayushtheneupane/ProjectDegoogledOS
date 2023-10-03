package p000;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* renamed from: bba */
/* compiled from: PG */
final class bba implements aqx {

    /* renamed from: a */
    private final ByteBuffer f1986a = ByteBuffer.allocate(8);

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo1498a(byte[] bArr, Object obj, MessageDigest messageDigest) {
        Long l = (Long) obj;
        messageDigest.update(bArr);
        synchronized (this.f1986a) {
            this.f1986a.position(0);
            messageDigest.update(this.f1986a.putLong(l.longValue()).array());
        }
    }
}
