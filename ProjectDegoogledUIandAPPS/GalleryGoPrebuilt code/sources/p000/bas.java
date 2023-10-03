package p000;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* renamed from: bas */
/* compiled from: PG */
public final class bas extends azl {

    /* renamed from: b */
    private static final byte[] f1973b = "com.bumptech.glide.load.resource.bitmap.RoundedCorners".getBytes(f1466a);

    /* renamed from: c */
    private final int f1974c;

    public bas(int i) {
        boolean z;
        if (i > 0) {
            z = true;
        } else {
            z = false;
        }
        cns.m4637a(z, "roundingRadius must be greater than 0.");
        this.f1974c = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof bas) || this.f1974c != ((bas) obj).f1974c) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return bfp.m2434b(-569625254, bfp.m2433b(this.f1974c));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Bitmap mo1740a(auk auk, Bitmap bitmap, int i, int i2) {
        return bax.m2068a(auk, bitmap, this.f1974c);
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        messageDigest.update(f1973b);
        messageDigest.update(ByteBuffer.allocate(4).putInt(this.f1974c).array());
    }
}
