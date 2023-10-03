package p000;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* renamed from: bar */
/* compiled from: PG */
public final class bar extends azl {

    /* renamed from: b */
    private static final byte[] f1971b = "com.bumptech.glide.load.resource.bitmap.Rotate".getBytes(f1466a);

    /* renamed from: c */
    private final int f1972c;

    public bar(int i) {
        this.f1972c = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof bar) || this.f1972c != ((bar) obj).f1972c) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return bfp.m2434b(-950519196, bfp.m2433b(this.f1972c));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Bitmap mo1740a(auk auk, Bitmap bitmap, int i, int i2) {
        return bax.m2067a(bitmap, this.f1972c);
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        messageDigest.update(f1971b);
        messageDigest.update(ByteBuffer.allocate(4).putInt(this.f1972c).array());
    }
}
