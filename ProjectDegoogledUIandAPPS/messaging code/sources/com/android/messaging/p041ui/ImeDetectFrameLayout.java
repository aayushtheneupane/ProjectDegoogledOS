package com.android.messaging.p041ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import com.android.messaging.util.C1417V;
import com.android.messaging.util.C1430e;

/* renamed from: com.android.messaging.ui.ImeDetectFrameLayout */
public class ImeDetectFrameLayout extends FrameLayout {
    public ImeDetectFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int measuredHeight = getMeasuredHeight();
        super.onMeasure(i, i2);
        if (Log.isLoggable("MessagingApp", 2)) {
            C1430e.m3628v("MessagingApp", "ImeDetectFrameLayout measuredHeight: " + measuredHeight + " getMeasuredHeight(): " + getMeasuredHeight());
        }
        if (measuredHeight != getMeasuredHeight() && (getContext() instanceof C1417V)) {
            ((C1417V) getContext()).mo6914g(i2);
        }
    }
}
