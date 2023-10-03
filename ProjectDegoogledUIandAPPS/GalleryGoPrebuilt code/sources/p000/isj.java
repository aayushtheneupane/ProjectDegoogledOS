package p000;

import android.widget.ImageView;

/* renamed from: isj */
/* compiled from: PG */
final /* synthetic */ class isj {

    /* renamed from: a */
    public static final /* synthetic */ int[] f15004a;

    static {
        int[] iArr = new int[ImageView.ScaleType.values().length];
        f15004a = iArr;
        try {
            iArr[ImageView.ScaleType.MATRIX.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f15004a[ImageView.ScaleType.FIT_START.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f15004a[ImageView.ScaleType.FIT_END.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f15004a[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f15004a[ImageView.ScaleType.FIT_XY.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
    }
}
