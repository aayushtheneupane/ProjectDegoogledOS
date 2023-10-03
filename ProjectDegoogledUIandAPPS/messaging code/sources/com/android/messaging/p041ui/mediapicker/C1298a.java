package com.android.messaging.p041ui.mediapicker;

import com.google.common.base.C1508E;

/* renamed from: com.android.messaging.ui.mediapicker.a */
public class C1298a {

    /* renamed from: QH */
    private volatile int f2054QH;

    /* renamed from: Ia */
    public void mo7767Ia(int i) {
        C1508E.checkArgument((i >= 0 && i <= 100) || i == -1);
        this.f2054QH = i;
    }

    /* renamed from: jj */
    public int mo7768jj() {
        return this.f2054QH;
    }
}
