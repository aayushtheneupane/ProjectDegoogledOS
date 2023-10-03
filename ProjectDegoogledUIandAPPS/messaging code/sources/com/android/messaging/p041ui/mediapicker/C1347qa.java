package com.android.messaging.p041ui.mediapicker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/* renamed from: com.android.messaging.ui.mediapicker.qa */
public class C1347qa extends GridView {
    public C1347qa(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: Eb */
    public boolean mo7926Eb() {
        if (getAdapter() == null || getAdapter().getCount() == 0 || getChildCount() == 0) {
            return false;
        }
        if (getFirstVisiblePosition() != 0 || getChildAt(0).getTop() < 0) {
            return true;
        }
        return false;
    }
}
