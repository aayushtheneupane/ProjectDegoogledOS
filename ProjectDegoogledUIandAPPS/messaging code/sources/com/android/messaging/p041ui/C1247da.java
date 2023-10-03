package com.android.messaging.p041ui;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;

/* renamed from: com.android.messaging.ui.da */
public class C1247da extends InsetDrawable {

    /* renamed from: Fc */
    private final int f1968Fc;

    /* renamed from: Gc */
    private final int f1969Gc;

    private C1247da(Drawable drawable, int i, int i2, int i3, int i4, int i5, int i6) {
        super(drawable, i, i2, i3, i4);
        this.f1968Fc = i5;
        this.f1969Gc = i6;
    }

    /* renamed from: a */
    public static C1247da m3175a(Drawable drawable, int i, int i2) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int i3 = 0;
        int i4 = (intrinsicWidth < 0 || intrinsicWidth > i) ? 0 : (i - intrinsicWidth) / 2;
        if (intrinsicHeight >= 0 && intrinsicHeight <= i2) {
            i3 = (i2 - intrinsicHeight) / 2;
        }
        int i5 = i3;
        return new C1247da(drawable, i4, i5, i4, i5, i, i2);
    }

    public int getIntrinsicHeight() {
        return this.f1969Gc;
    }

    public int getIntrinsicWidth() {
        return this.f1968Fc;
    }
}
