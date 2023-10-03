package p000;

import android.graphics.Bitmap;
import java.security.MessageDigest;

/* renamed from: bah */
/* compiled from: PG */
public final class bah extends azl {

    /* renamed from: b */
    private static final byte[] f1946b = "com.bumptech.glide.load.resource.bitmap.FitCenter".getBytes(f1466a);

    public final int hashCode() {
        return 1572326941;
    }

    public final boolean equals(Object obj) {
        return obj instanceof bah;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Bitmap mo1740a(auk auk, Bitmap bitmap, int i, int i2) {
        return bax.m2074b(auk, bitmap, i, i2);
    }

    /* renamed from: a */
    public final void mo1494a(MessageDigest messageDigest) {
        messageDigest.update(f1946b);
    }
}
