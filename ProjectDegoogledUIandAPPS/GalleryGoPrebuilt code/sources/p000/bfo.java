package p000;

import android.graphics.Bitmap;

/* renamed from: bfo */
/* compiled from: PG */
final /* synthetic */ class bfo {

    /* renamed from: a */
    public static final /* synthetic */ int[] f2218a;

    static {
        int[] iArr = new int[Bitmap.Config.values().length];
        f2218a = iArr;
        try {
            iArr[Bitmap.Config.ALPHA_8.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f2218a[Bitmap.Config.RGB_565.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f2218a[Bitmap.Config.ARGB_4444.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f2218a[Bitmap.Config.RGBA_F16.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f2218a[Bitmap.Config.ARGB_8888.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
    }
}
