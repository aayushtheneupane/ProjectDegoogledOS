package com.android.messaging.p041ui;

import android.view.View;

/* renamed from: com.android.messaging.ui.S */
class C1065S {
    public final int endX;
    public final int endY;
    public final int startX;
    public final int startY;

    private C1065S(int i, int i2, int i3, int i4) {
        this.startX = i;
        this.startY = i2;
        this.endX = i3;
        this.endY = i4;
    }

    /* renamed from: A */
    public static C1065S m2641A(int i, int i2) {
        return new C1065S(i, i2, i, i2);
    }

    /* renamed from: B */
    public static C1065S m2642B(int i, int i2) {
        return new C1065S(i, i2, i + 1, i2);
    }

    /* renamed from: z */
    public static C1065S m2643z(int i, int i2) {
        return new C1065S(i, i2, i + 1, i2 + 1);
    }

    /* renamed from: x */
    public int mo7077x(int i, int i2) {
        return View.MeasureSpec.makeMeasureSpec((((this.endY - this.startY) + 1) * i) - (i2 * 2), 1073741824);
    }

    /* renamed from: y */
    public int mo7078y(int i, int i2) {
        return View.MeasureSpec.makeMeasureSpec((((this.endX - this.startX) + 1) * i) - (i2 * 2), 1073741824);
    }
}
