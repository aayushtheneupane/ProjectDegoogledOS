package p000;

import android.graphics.Bitmap;

/* renamed from: auw */
/* compiled from: PG */
final /* synthetic */ class auw {

    /* renamed from: a */
    public static final /* synthetic */ int[] f1739a;

    static {
        int[] iArr = new int[Bitmap.Config.values().length];
        f1739a = iArr;
        try {
            iArr[Bitmap.Config.ARGB_8888.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f1739a[Bitmap.Config.RGB_565.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f1739a[Bitmap.Config.ARGB_4444.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f1739a[Bitmap.Config.ALPHA_8.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
    }
}
