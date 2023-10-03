package p000;

import android.graphics.Bitmap;
import java.security.MessageDigest;

/* renamed from: azo */
/* compiled from: PG */
public final class azo extends azl {

    /* renamed from: b */
    private static final byte[] f1911b = "com.bumptech.glide.load.resource.bitmap.CenterInside".getBytes(f1466a);

    public final int hashCode() {
        return -670243078;
    }

    public final boolean equals(Object obj) {
        return obj instanceof azo;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Bitmap mo1740a(auk auk, Bitmap bitmap, int i, int i2) {
        return bax.m2076c(auk, bitmap, i, i2);
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        messageDigest.update(f1911b);
    }
}
