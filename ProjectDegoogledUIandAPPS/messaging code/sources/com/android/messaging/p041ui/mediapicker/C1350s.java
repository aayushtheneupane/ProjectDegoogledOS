package com.android.messaging.p041ui.mediapicker;

import android.hardware.Camera;
import java.util.Comparator;

/* renamed from: com.android.messaging.ui.mediapicker.s */
class C1350s implements Comparator {

    /* renamed from: TH */
    private final float f2145TH;

    /* renamed from: UH */
    private final int f2146UH;
    private final int mMaxHeight;
    private final int mMaxWidth;

    public C1350s(int i, int i2, float f, int i3) {
        this.mMaxWidth = i;
        this.mMaxHeight = i2;
        this.f2145TH = f;
        this.f2146UH = i3;
    }

    public int compare(Object obj, Object obj2) {
        Camera.Size size = (Camera.Size) obj;
        Camera.Size size2 = (Camera.Size) obj2;
        boolean z = false;
        boolean z2 = size.width <= this.mMaxWidth && size.height <= this.mMaxHeight;
        if (size2.width <= this.mMaxWidth && size2.height <= this.mMaxHeight) {
            z = true;
        }
        if (z2 == z) {
            float f = ((float) size.width) / ((float) size.height);
            float f2 = ((float) size2.width) / ((float) size2.height);
            float abs = Math.abs(f - this.f2145TH);
            float abs2 = Math.abs(f2 - this.f2145TH);
            if (abs == abs2) {
                return Math.abs((size.width * size.height) - this.f2146UH) - Math.abs((size2.width * size2.height) - this.f2146UH);
            }
            if (abs - abs2 < 0.0f) {
                return -1;
            }
        } else if (size.width <= this.mMaxWidth) {
            return -1;
        }
        return 1;
    }
}
