package p000;

import android.graphics.Bitmap;
import java.security.MessageDigest;

/* renamed from: azn */
/* compiled from: PG */
public final class azn extends azl {

    /* renamed from: b */
    private static final byte[] f1910b = "com.bumptech.glide.load.resource.bitmap.CenterCrop".getBytes(f1466a);

    public final int hashCode() {
        return -599754482;
    }

    public final boolean equals(Object obj) {
        return obj instanceof azn;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Bitmap mo1740a(auk auk, Bitmap bitmap, int i, int i2) {
        return bax.m2069a(auk, bitmap, i, i2);
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        messageDigest.update(f1910b);
    }
}
