package p000;

import android.content.Context;
import android.graphics.Bitmap;

/* renamed from: azl */
/* compiled from: PG */
public abstract class azl implements ard {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Bitmap mo1740a(auk auk, Bitmap bitmap, int i, int i2);

    /* renamed from: a */
    public final aua mo1497a(Context context, aua aua, int i, int i2) {
        if (bfp.m2431a(i, i2)) {
            auk auk = aow.m1346a(context).f1289b;
            Bitmap bitmap = (Bitmap) aua.mo1605b();
            if (i == Integer.MIN_VALUE) {
                i = bitmap.getWidth();
            }
            if (i2 == Integer.MIN_VALUE) {
                i2 = bitmap.getHeight();
            }
            Bitmap a = mo1740a(auk, bitmap, i, i2);
            return !bitmap.equals(a) ? azk.m1961a(a, auk) : aua;
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append("Cannot apply transformation on width: ");
        sb.append(i);
        sb.append(" or height: ");
        sb.append(i2);
        sb.append(" less than or equal to zero and not Target.SIZE_ORIGINAL");
        throw new IllegalArgumentException(sb.toString());
    }
}
